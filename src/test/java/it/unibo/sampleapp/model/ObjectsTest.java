package it.unibo.sampleapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.sampleapp.model.object.api.Button;
import it.unibo.sampleapp.model.object.api.Hazard;
import it.unibo.sampleapp.model.object.api.Lever;
import it.unibo.sampleapp.model.object.api.MovableIPlatform;
import it.unibo.sampleapp.model.object.api.Player;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.model.object.impl.ButtonImpl;
import it.unibo.sampleapp.model.object.impl.FanImpl;
import it.unibo.sampleapp.model.object.impl.Fireboy;
import it.unibo.sampleapp.model.object.impl.HazardImpl;
import it.unibo.sampleapp.model.object.impl.LeverImpl;
import it.unibo.sampleapp.model.object.impl.MovablePlatformImpl;
import it.unibo.sampleapp.model.object.impl.Watergirl;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Test for the objects.
 */
class ObjectsTest {

    private static final int TILE_SIZE = 36;
    private static final Position START = new PositionImpl(100, 100);
    private static final int WIDTH = TILE_SIZE;
    private static final int HEIGHT = TILE_SIZE;
    private static final int STEP_SIZE = 4;

    /**
     * testing of the movable platform. test the active and the deactive.
     * testing if it move after the active and if it don't move after the deactive.
     */
    @Test
    void testMovablePlatform() {
        final MovableIPlatform platform = new MovablePlatformImpl(START, WIDTH, HEIGHT, STEP_SIZE, true, 1); 
        final Position initial = platform.getPosition();
        assertFalse(platform.isActive());
        platform.active();
        assertTrue(platform.isActive());

        platform.move();
        final Position moved = platform.getPosition();
        assertTrue(moved.getX() > initial.getX(), "Platform should have moved to the right");

        platform.deactive();
        platform.move();
        final Position newPosittion = platform.getPosition();
        assertTrue(newPosittion.getX() < moved.getX(), "Platform should have moved back to left");
    }

    /**
     * testing the button. 
     * verifies that it remains pressed with multiple presses and releases correctly when one or the both are releases.
     */
    @Test
    void testButton() {
        final MovableIPlatform platform = new MovablePlatformImpl(START, WIDTH, HEIGHT, STEP_SIZE, true, 1);
        final Button button = new ButtonImpl(START, WIDTH, HEIGHT, platform);
        assertFalse(button.isPressed());
        assertEquals(platform, button.getLinkedPlatform());

        button.press();
        assertTrue(button.isPressed());

        button.release();
        assertFalse(button.isPressed());

        button.press();
        button.press();
        assertTrue(button.isPressed());
        button.release();
        assertTrue(button.isPressed(), "Still pressed after one release");
        button.release();
        assertFalse(button.isPressed(), "Released after second release");
    }

    /**
     * testing the fan. testing the active and the deactive.
     */
    @Test
    void testFan() {
        final FanImpl fan = new FanImpl(START, WIDTH, HEIGHT);
        assertFalse(fan.isActive());

        fan.active();
        assertTrue(fan.isActive());

        fan.deactive();
        assertFalse(fan.isActive());
    }

    /**
     * testing lever. test the active and the deactive and testing the activeFromLeft. 
     * also verifies the link to the platform.
     */
    @Test
    void testLever() {
        final MovableIPlatform platform = new MovablePlatformImpl(START, WIDTH, HEIGHT, STEP_SIZE, true, 1);
        final Lever lever = new LeverImpl(START, WIDTH, HEIGHT, platform);
        assertFalse(lever.isActive());
        assertFalse(lever.isActivedFromLeft());
        assertEquals(platform, lever.getLinkedPlatform());

        lever.setActive(true);
        lever.setActivedFromLeft(true);
        assertTrue(lever.isActive());
        assertTrue(lever.isActivedFromLeft());

        lever.setActive(false);
        lever.setActivedFromLeft(false);
        assertFalse(lever.isActive());
        assertFalse(lever.isActivedFromLeft());
    }

    /**
     * testing for hazard. testing all the type of the hazard and testing the compatibility with the both players.
     */
    @Test
    void testHazard() {
        final Hazard hazardAcid = new HazardImpl(START, WIDTH, HEIGHT, HazardImpl.HazardType.ACID);
        final Hazard hazardFire = new HazardImpl(START, WIDTH, HEIGHT, HazardImpl.HazardType.FIRE);
        final Hazard hazardWater = new HazardImpl(START, WIDTH, HEIGHT, HazardImpl.HazardType.WATER);
        final Player fireboy = new Fireboy(START.getX(), START.getY(), WIDTH, HEIGHT);
        final Player watergirl = new Watergirl(START.getX(), START.getY(), WIDTH, HEIGHT);

        assertFalse(hazardAcid.safeForPlayer(fireboy));
        assertFalse(hazardAcid.safeForPlayer(watergirl));

        assertTrue(hazardFire.safeForPlayer(fireboy));
        assertFalse(hazardFire.safeForPlayer(watergirl));

        assertFalse(hazardWater.safeForPlayer(fireboy));
        assertTrue(hazardWater.safeForPlayer(watergirl));

        assertEquals(HazardImpl.HazardType.ACID, hazardAcid.getType());
    }
}
