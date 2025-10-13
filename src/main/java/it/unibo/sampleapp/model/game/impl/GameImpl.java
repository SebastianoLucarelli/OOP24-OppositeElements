package it.unibo.sampleapp.model.game.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.sampleapp.model.collision.api.CollisionFactory;
import it.unibo.sampleapp.model.collision.impl.CollisionFactoryImpl;
import it.unibo.sampleapp.model.collision.impl.CollisionQueue;
import it.unibo.sampleapp.model.game.GameState;
import it.unibo.sampleapp.model.game.api.Game;
import it.unibo.sampleapp.model.level.api.Level;
import it.unibo.sampleapp.model.object.api.Button;
import it.unibo.sampleapp.model.object.api.Door;
import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Gem;
import it.unibo.sampleapp.model.object.api.Hazard;
import it.unibo.sampleapp.model.object.api.Lever;
import it.unibo.sampleapp.model.object.api.MovableIPlatform;
import it.unibo.sampleapp.model.object.api.Player;

/**
 * Implementation of Game interface.
 */
public class GameImpl implements Game {

    private final List<GameObject> gameObjects;
    private final List<Player> players;

    private final CollisionFactory collisionFactory = new CollisionFactoryImpl();
    private final CollisionQueue collisionQueue = new CollisionQueue();

    private GameState currenState = GameState.PLAYING;

    private final int totalGems;
    private int collectedGems;

    /**
     * Builds a new GameImpl instance from a given level.
     *
     * @param level the given level
     */
    public GameImpl(final Level level) {
        this.gameObjects = new ArrayList<>(level.getGameObjects());
        this.players = new ArrayList<>(level.getPlayers());

        int countGem = 0;
        for (final GameObject o : this.gameObjects) {
            if (o instanceof Gem) {
                countGem++;
            }
        }
        this.totalGems = countGem;
        this.collectedGems = 0;
    }

    /**
     * Updates the game state and process collisions.
     */
    @Override
    public void update(final double deltaTime) {
        if (currenState != GameState.PLAYING) {
            return;
        }

        for (final Player p : players) {
            p.updatePlayer(deltaTime);
            checkCollisions(p);
        }

        collisionQueue.manageCollisions(this);
        checkLevelWin();
    }

    /**
     * Checks collisions beetwen the player and all game objects.
     *
     * @param p the player to check collisions for
     */
    private void checkCollisions(final Player p) {
        p.setAtDoor(false);

        for (final GameObject obj : new ArrayList<>(gameObjects)) {
            if (collidingPlayerObj(p, obj)) {
                if (obj instanceof Gem g) {
                    collisionQueue.addCollision(collisionFactory.hitGems(p, g));
                } else if (obj instanceof Hazard h) {
                    collisionQueue.addCollision(collisionFactory.hitHazard(p, h));
                } else if (obj instanceof Door d) {
                    collisionQueue.addCollision(collisionFactory.doorUnlockedCollision(p, d));
                } else if (obj instanceof Button b) {
                    collisionQueue.addCollision(collisionFactory.buttonClickedCollision(p, b));
                } else if (obj instanceof Lever l) {
                    collisionQueue.addCollision(collisionFactory.leverDisplacementCollision(p, l));
                } else if (obj instanceof MovableIPlatform mp) {
                    collisionQueue.addCollision(collisionFactory.movablePlatformCollision(p, mp));
                }
            } else if (obj instanceof Button b && b.isPressed()) {
                b.setPressed(false);
                if (b.getLinkedPlatform() != null) {
                    b.getLinkedPlatform().deactive();
                }
            }
        }
    }

    /**
     * Check if the player and object are colliding.
     *
     * @param p the player
     * @param obj a game object
     * @return true if they are colliding, false otherwise
     */
    private boolean collidingPlayerObj(final Player p, final GameObject obj) {
        return p.getPosition().getX() < obj.getPosition().getX() + obj.getWidth()
            && p.getPosition().getX() + p.getWidth() > obj.getPosition().getX()
            && p.getPosition().getY() < obj.getPosition().getY() + obj.getHeight()
            && p.getPosition().getY() + p.getHeight() > obj.getPosition().getY();
    }

    /**
     * Increments the number of collected gems.
     */
    @Override
    public void collectGems() {
        this.collectedGems++;
    }

    /**
     * Check if all gems of the given level have been collected.
     */
    @Override
    public boolean areAllGemsCollected() {
        return collectedGems >= totalGems && totalGems > 0;
    }

    /**
     * Removes a game object from the level.
     */
    @Override
    public void removeObject(final GameObject object) {
        this.gameObjects.remove(object);
    }

    /**
     * Check if all players are at the right door to complete the level.
     */
    @Override
    public void checkLevelWin() {
        final boolean allAtDoor = players.stream().allMatch(Player::isAtDoor);
        if (allAtDoor) {
            this.currenState = GameState.LEVEL_COMPLETED;
        }
    }

    /**
     * Sets the game state to Game Over.
     */
    @Override
    public void gameOver() {
        this.currenState = GameState.GAME_OVER;
    }

    /**
     * Return a copy of the current game objects.
     */
    @Override
    public List<GameObject> getGameObjects() {
        return new ArrayList<>(this.gameObjects);
    }

    /**
     * Returns a copy of the current players.
     */
    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(this.players);
    }
}
