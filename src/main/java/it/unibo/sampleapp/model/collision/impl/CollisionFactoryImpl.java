package it.unibo.sampleapp.model.collision.impl;

import it.unibo.sampleapp.model.collision.api.BoundaryType;
import it.unibo.sampleapp.model.collision.api.CollisionFactory;
import it.unibo.sampleapp.model.collision.api.Collisions;
import it.unibo.sampleapp.model.object.api.Button;
import it.unibo.sampleapp.model.object.api.Door;
import it.unibo.sampleapp.model.object.api.Fan;
import it.unibo.sampleapp.model.object.api.Gem;
import it.unibo.sampleapp.model.object.api.Hazard;
import it.unibo.sampleapp.model.object.api.Lever;
import it.unibo.sampleapp.model.object.api.MovableIPlatform;
import it.unibo.sampleapp.model.object.api.Platform;
import it.unibo.sampleapp.model.object.api.Player;

/**
 * Class that implements CollisionFactory.
 */
public class CollisionFactoryImpl implements CollisionFactory {

    /**
     * Handles the gem collection if the gem is not collected yet.
     */
    @Override
    public Collisions hitGems(final Player player, final Gem gem) {
        return game -> {
            if (!gem.isCollected() && gem.getType().name().equals(player.getType().name())) {
                gem.collect();
                game.collectGems();
                game.removeObject(gem);
            }
        };
    }

    /**
     * Ends the game if the hazard is dangerous for the player.
     */
    @Override
    public Collisions hitHazard(final Player player, final Hazard hazard) {
        return game -> {
            if (!hazard.safeForPlayer(player)) {
                game.gameOver();
                System.out.println("Collisione con " + hazard.getType() + " per " + player.getType());
            }
            System.out.println("Collisione con " + hazard.getType() + " per " + player.getType());
        };
    }

    /**
     * Sets the door true if the player is in front of it.
     */
    @Override
    public Collisions doorUnlockedCollision(final Player player, final Door door) {
        return game -> {
            if (door.getType().name().equals(player.getType().name())) {
                player.setAtDoor(true);
            } else {
                player.setAtDoor(false);
            }
            game.checkLevelWin();
        };
    }

    /**
     * Activates the platform if the button is pressed.
     */
    @Override
    public Collisions buttonClickedCollision(final Player player, final Button button) {
        return game -> {
            final MovableIPlatform platform = button.getLinkedPlatform();
            if (platform != null) {
                button.press();
                platform.active();
            }
        };
    }

    /**
     * Toggles lever and activate or deactivate the linked movable platform.
     */
    @Override
    public Collisions leverDisplacementCollision(final Player player, final Lever lever) {
        return game -> {
            lever.toggle();
            final MovableIPlatform platform = lever.getLinkedPlatform();
            if (platform != null) {
                if (lever.isActive()) {
                    platform.active();
                } else {
                    platform.deactive();
                }
            }
        };
    }

    /**
     * Handles the fan collision.
     */
    @Override
    public Collisions playerOnFan(final Player player, final Fan fan) {
        return game -> {

        };
    }

    /**
     * Handles the platform collision.
     */
    @Override
    public Collisions platformCollisions(final Player player, final Platform platform) {
        return game -> {

        };
    }

    /**
     * Handles the movablePlatform collision.
     */
    @Override
    public Collisions movablePlatformCollision(final Player player, final MovableIPlatform movablePlatform) {
        return game -> {
            final double playerBottom = player.getPosition().getY() + player.getHeight();
            final double platformTop = movablePlatform.getPosition().getY();
            if(player.getPosition().getY() < platformTop && playerBottom > platformTop && player.getSpeedX() >= Double.MAX_VALUE) {
                player.landOn(platformTop - player.getHeight());
                player.setOnFloor(true);
            }
        };
    }

    /**
     * Handles the boundary collisions.
     */
    @Override
    public Collisions boundaryCollisions(final Player player, final BoundaryType boundary) {
        return game -> {

        };
    }

    /**
     * Hnadles the button collisions when the button is released.
     */
    @Override
    public Collisions buttonReleasedCollision(Button button) {
        return game -> {
            button.release();
            final MovableIPlatform mPlatform = button.getLinkedPlatform();
            if (mPlatform != null && !button.isPressed()) {
                mPlatform.deactive();
            }
        };
    }
}
