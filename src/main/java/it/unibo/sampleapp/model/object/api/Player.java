package it.unibo.sampleapp.model.object.api;

import it.unibo.sampleapp.utils.api.Position;

/**
 * Interface that defines the two players.
 */
public interface Player {

    /**
     * Process controller commands.
     *
     * @param left if the player has move to left
     * @param right if the player has move to right
     * @param jump if the player has to jump
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
}
