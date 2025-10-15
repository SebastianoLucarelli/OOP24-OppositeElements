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
     * @return the current aniamtion frame number
     */
    int getFrameNum();

}
