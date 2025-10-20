package it.unibo.sampleapp.model.object.api;

import it.unibo.sampleapp.utils.api.Position;

/**
 * Interface that defines the two players.
 */
public interface Player {

    /**
     * Process controller commands.
     *
     * @param left  if the player has move to left
     * @param right if the player has move to right
     * @param jump  if the player has to jump
     */
    void input(boolean left, boolean right, boolean jump);

    /**
     * Update player logic.
     *
     * @param deltaTime time since last update
     */
    void updatePlayer(double deltaTime);

    /**
     * @return player's current position
     */
    Position getPosition();

    /**
     * @return player width
     */
    int getWidth();

    /**
     * @return player height
     */
    int getHeight();

    /**
     * @return the type of this player
     */
    PlayerType getType();

    /**
     * @return true if the player is currently at the door
     */
    boolean isAtDoor();

    /**
     * Sets whether the player is currently at the door.
     *
     * @param atDoor true if the player is in contact with the door
     */
    void setAtDoor(boolean atDoor);

    /**
     * @return the current direction
     */
    String getDirection();

    /**
     * @return the horizontal speed
     */
    double getSpeedX();

    /**
     * Stops vertical movement.
     *
     * @param newY the position where the player should stand
     */
    void landOn(double newY);

    /**
     * Sets whether the player is currently on the floor.
     *
     * @param onFloor if the player is touching the ground
     */
    void setOnFloor(boolean onFloor);

    /**
     * @return the vertical speed
     */
    double getSpeedY();

    /**
     * Stops the jump when the player hits the platform.
     *
     * @param newY the position where the player should stand
     */
    void stopJump(double newY);

    /**
     * Sets a new position.
     *
     * @param newPos the new position
     */
    void setPosition(Position newPos);

    /**
     * Sets a new x position.
     *
     * @param x the position where the player should stand
     */
    void setPositionX(double x);

    /**
     * Sets a new y position.
     *
     * @param y the position where the player should stand
     */
    void setPositionY(double y);

    /**
     * Stop the horizontal movement of the player.
     */
    void stopHorizontalMovement();

}
