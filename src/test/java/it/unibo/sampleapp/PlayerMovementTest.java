package it.unibo.sampleapp;

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
    private static final double TOLERANCE = 0.1;
    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 50;
    private static final int SCREEN_HEIGHT = 540;
    private static final int SPEED = 200;

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

    @Test
    void fireboyMoveRightTest() {
        fireboy.input(false, true, false);
        fireboy.updatePlayer(DELTA);
        assertEquals("right", fireboy.getDirection());
        assertTrue(fireboy.getPosition().getX() > FIREBOY_START_X);
    }

    @Test
    void fireboyMoveLeftTest() {
        fireboy.input(true, false, false);
        fireboy.updatePlayer(DELTA);
        assertEquals("left", fireboy.getDirection());
        assertTrue(fireboy.getPosition().getX() < FIREBOY_START_X);
    }

    @Test
    void fireboyJumpTest() {
        fireboy.setOnFloor(true);
        fireboy.input(false, false, true);
        fireboy.updatePlayer(DELTA);
        assertEquals("front", fireboy.getDirection());
        assertTrue(fireboy.getSpeedY() < 0);
    }

    @Test
    void watergirlMoveRightrTest() {
        watergirl.input(false, true, false);
        watergirl.updatePlayer(DELTA);
        assertEquals("right", watergirl.getDirection());
        assertTrue(watergirl.getPosition().getX() > WATERGIRL_START_X);
    }

    @Test
    void watergirlMoveLeftTest() {
        watergirl.input(true, false, false);
        watergirl.updatePlayer(DELTA);
        assertEquals("left", watergirl.getDirection());
        assertTrue(watergirl.getPosition().getX() < WATERGIRL_START_X);
    }

    @Test
    void watergirlJumpTest() {
        watergirl.setOnFloor(true);
        watergirl.input(false, false, true);
        watergirl.updatePlayer(DELTA);
        assertEquals("front", watergirl.getDirection());
        assertTrue(watergirl.getSpeedY() < 0);
    }

    @Test
    void cannotJumpInAirTest() {
        fireboy.setOnFloor(false);
        watergirl.setOnFloor(false);
        final double startSpeedYg = watergirl.getSpeedY();
        final double startSpeedYf = fireboy.getSpeedY();
        fireboy.input(false, false, true);
        watergirl.input(false, false, true);
        fireboy.updatePlayer(DELTA);
        watergirl.updatePlayer(DELTA);
        assertTrue(fireboy.getSpeedY() > startSpeedYf);
        assertTrue(watergirl.getSpeedY() > startSpeedYg);
    }

    @Test
    void stopAtFloorTest() {
        final double heightY = SCREEN_HEIGHT - PLAYER_HEIGHT;
        watergirl.setPositionY(heightY - 10);
        fireboy.setPositionY(heightY - 10);
        fireboy.setSpeedY(SPEED);
        watergirl.setSpeedY(SPEED);
        fireboy.updatePlayer(DELTA);
        watergirl.updatePlayer(DELTA);

        assertTrue(fireboy.isOnFloor());
        assertEquals(heightY, fireboy.getPosition().getY(), TOLERANCE);
        assertEquals(0, fireboy.getSpeedY(), TOLERANCE);
        assertTrue(watergirl.isOnFloor());
        assertEquals(heightY, watergirl.getPosition().getY(), TOLERANCE);
        assertEquals(0, watergirl.getSpeedY(), TOLERANCE);
    }
}
