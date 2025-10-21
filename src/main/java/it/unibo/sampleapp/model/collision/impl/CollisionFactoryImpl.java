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
                game.removePlayer(player);
                game.gameOver();
            }
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
     * Handles the button collisions when the button is released.
     */
    @Override
    public Collisions buttonReleasedCollision(final Button button) {
        return game -> {
            button.release();
            final MovableIPlatform mPlatform = button.getLinkedPlatform();
            if (mPlatform != null && !button.isPressed()) {
                mPlatform.deactive();
            }
        };
    }

    /**
     * Method that activates the lever and the movable platform connected to it.
     */
    @Override
    public Collisions leverDisplacementCollision(final Player player, final Lever lever) {
        return game -> {
            final MovableIPlatform platform = lever.getLinkedPlatform();
            final double playerCenterX = player.getPosition().getX() + player.getWidth() / 2;
            final double leverCenterX = lever.getPosition().getX() + lever.getWidth() / 2;
            final boolean fromLeft = playerCenterX > leverCenterX;

            if (!lever.isActive() && fromLeft) {
                lever.setActive(true);
                lever.setActivedFromLeft(true);
                if (platform != null) {
                    platform.active();
                }
            } else if (lever.isActive() && !fromLeft && lever.getActivedFromLeft()) {
                lever.setActive(false);
                lever.setActivedFromLeft(false);
                if (platform != null) {
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
            final double playerBottom = player.getPosition().getY() + player.getHeight();
            final double playerLeft = player.getPosition().getX();
            final double playerRight = playerLeft + player.getWidth();
            final double fanTop = fan.getPosition().getY();
            final double fanLeft = fan.getPosition().getX();
            final double fanRight = fanLeft + fan.getWidth();

            final boolean turnOnFan = playerBottom >= fanTop
                    && playerBottom <= fanTop + fan.getHeight() * 3
                    && playerRight > fanLeft
                    && playerLeft < fanRight;

            if (turnOnFan) {

                if (!fan.isActive()) {
                    fan.active();
                }

                final double fanPower = -500.0;
                player.setOnFloor(false);

                if (player.getSpeedY() > fanPower) {
                    player.setSpeedY(fanPower);
                }
            } else {
                if (fan.isActive()) {
                    fan.deactive();
                }
            }
        };
    }

    /**
     * Handles the platform collision.
     */
    @Override
    public Collisions platformCollisions(final Player player, final Platform platform) {
        return game -> {
            final double playerBottom = player.getPosition().getY() + player.getHeight();
            final double platformTop = platform.getPosition().getY();
            final double playerTop = player.getPosition().getY();
            final double platformBottom = platform.getPosition().getY() + platform.getHeight();
            final double playerLeft = player.getPosition().getX();
            final double playerRight = player.getPosition().getX() + player.getWidth();
            final double platformLeft = platform.getPosition().getX();
            final double platformRight = platform.getPosition().getX() + platform.getWidth();

            if (playerBottom >= platformTop
                && player.getPosition().getY() < platformTop
                && player.getSpeedY() >= 0
                && playerBottom - platformTop < player.getHeight() * 0.5) {
                    player.landOn(platformTop - player.getHeight());
                    player.setOnFloor(true);
                    return;
            } else {
                    player.setOnFloor(false);
                }

            if (playerTop <= platformBottom
                && playerBottom > platformBottom
                && player.getSpeedY() < 0) {
                    player.stopJump(platformBottom);
                }

            if (playerRight > platformLeft && playerLeft < platformLeft
                && player.getSpeedX() > 0 
                && playerBottom > platformTop + 1 && playerTop < platformBottom - 1) {
                    player.setPositionX(platformLeft - player.getWidth());
                    player.stopHorizontalMovement();
                }

            if (player.getPosition().getX() < platformRight && playerRight > platformRight
                && player.getSpeedX() < 0
                && playerBottom > platformTop + 1 && playerTop < platformBottom - 1) {
                    player.setPositionX(platformRight);
                    player.stopHorizontalMovement();
                }
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
            final double playerTop = player.getPosition().getY();
            final double platformBottom = platformTop + movablePlatform.getHeight();
            final double playerLeft = player.getPosition().getX();
            final double playerRight = player.getPosition().getX() + player.getWidth();
            final double platformLeft = movablePlatform.getPosition().getX();
            final double platformRight = movablePlatform.getPosition().getX() + movablePlatform.getWidth();

            if (playerBottom >= platformTop
                && playerTop < platformTop
                && player.getSpeedY() >= 0
                && playerBottom - platformTop < player.getHeight() * 0.5) {
                    player.landOn(platformTop - player.getHeight());
                    player.setOnFloor(true);

                    if (movablePlatform.getSpeed() != 0) {
                        player.getPosition().setY(player.getPosition().getY() + movablePlatform.getSpeed());
                    }
                    return;
            } else {
                player.setOnFloor(false);
            }

            if (playerTop <= platformBottom
                && playerBottom > platformBottom
                && player.getSpeedY() < 0) {
                    player.stopJump(platformBottom);
                }

            if (playerRight > platformLeft && playerLeft < platformLeft
                && player.getSpeedX() > 0
                && playerBottom > platformTop + 1 && playerTop < platformBottom - 1) {
                    player.setPositionX(platformLeft - player.getWidth());
                    player.stopHorizontalMovement();
                }

            if (player.getPosition().getX() < platformRight && playerRight > platformRight
                && player.getSpeedX() < 0
                && playerBottom > platformTop + 1 && playerTop < platformBottom - 1) {
                    player.setPositionX(platformRight);
                    player.stopHorizontalMovement();
                }
        };
    }

    /**
     * Handles the boundary collisions.
     */
    @Override
    public Collisions boundaryCollisions(final Player player, final BoundaryType boundary) {
        return game -> {
            switch (boundary) {
                case LEFT -> {
                    player.setPositionX(0);
                    player.stopHorizontalMovement();
                }
                case RIGHT -> {
                    player.setPositionX(game.getWidth() - player.getWidth());
                    player.stopHorizontalMovement();
                }
                case TOP -> {
                    player.stopJump(0);
                }
                case BOTTOM -> {
                    player.setPositionY(game.getHeight() - player.getHeight());
                    player.setOnFloor(true);
                }
            }
        };
    }
}
