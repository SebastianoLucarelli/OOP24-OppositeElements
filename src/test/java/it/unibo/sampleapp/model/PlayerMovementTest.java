package it.unibo.sampleapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.sampleapp.model.object.impl.Fireboy;
import it.unibo.sampleapp.model.object.impl.Watergirl;

/**
 * Test class for verifying the movement of the players.
 */
class PlayerMovementTest {

    private static final double DELTA = 0.1;
    private static final double FIREBOY_START_X = 100;
    private static final double FIREBOY_START_Y = 100;
    private static final double WATERGIRL_START_X = 200;
    private static final double WATERGIRL_START_Y = 100;
    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 50;

    private Fireboy fireboy;
    private Watergirl watergirl;


    /**
     * Initializes a Fireboy and Watergirl before each test.
     */
    @BeforeEach
    void setPlayer() {
        fireboy = new Fireboy(FIREBOY_START_X, FIREBOY_START_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        watergirl = new Watergirl(WATERGIRL_START_X, WATERGIRL_START_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    /**
     * Test that Fireboy moves correctly to the right.
     * The direction must be "right" and the X position must increase.
     */
    @Test
    void fireboyMoveRightTest() {
        fireboy.input(false, true, false);
        fireboy.updatePlayer(DELTA);
        assertEquals("right", fireboy.getDirection());
        assertTrue(fireboy.getPosition().getX() > FIREBOY_START_X);
    }

    /**
     * Test that Fireboy moves correctly to the left.
     * The direction must be "left" and the X position must decrease.
     */
    @Test
    void fireboyMoveLeftTest() {
        fireboy.input(true, false, false);
        fireboy.updatePlayer(DELTA);
        assertEquals("left", fireboy.getDirection());
        assertTrue(fireboy.getPosition().getX() < FIREBOY_START_X);
    }

    /**
     * Test that Fireboy can jump qhen on the floor.
     * After the jump, the direction must be "front" and vertical speed negative.
     */
    @Test
    void fireboyJumpTest() {
        fireboy.setOnFloor(true);
        fireboy.input(false, false, true);
        fireboy.updatePlayer(DELTA);
        assertEquals("front", fireboy.getDirection());
        assertTrue(fireboy.getSpeedY() < 0);
    }

    /**
     * Test that Watergirl moves correctly to the right.
     * The direction must be "right" and the X position must increase.
     */
    @Test
    void watergirlMoveRightrTest() {
        watergirl.input(false, true, false);
        watergirl.updatePlayer(DELTA);
        assertEquals("right", watergirl.getDirection());
        assertTrue(watergirl.getPosition().getX() > WATERGIRL_START_X);
    }

    /**
     * Test that Watergirl moves correctly to the left.
     * The direction must be "left" and the X position must decrease.
     */
    @Test
    void watergirlMoveLeftTest() {
        watergirl.input(true, false, false);
        watergirl.updatePlayer(DELTA);
        assertEquals("left", watergirl.getDirection());
        assertTrue(watergirl.getPosition().getX() < WATERGIRL_START_X);
    }

    /**
     * Test that Watergirl can jump when on the floor.
     * After the jump, the direction must be "front" and vertical speed negative.
     */
    @Test
    void watergirlJumpTest() {
        watergirl.setOnFloor(true);
        watergirl.input(false, false, true);
        watergirl.updatePlayer(DELTA);
        assertEquals("front", watergirl.getDirection());
        assertTrue(watergirl.getSpeedY() < 0);
    }
}
