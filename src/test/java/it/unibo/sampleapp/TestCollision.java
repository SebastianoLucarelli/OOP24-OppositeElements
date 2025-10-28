package it.unibo.sampleapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.sampleapp.model.collision.api.BoundaryType;
import it.unibo.sampleapp.model.collision.api.CollisionFactory;
import it.unibo.sampleapp.model.collision.impl.CollisionFactoryImpl;
import it.unibo.sampleapp.model.game.api.Game;
import it.unibo.sampleapp.model.game.impl.GameImpl;
import it.unibo.sampleapp.model.level.api.Level;
import it.unibo.sampleapp.model.level.impl.LevelImpl;
import it.unibo.sampleapp.model.object.api.Button;
import it.unibo.sampleapp.model.object.api.Door;
import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Gem;
import it.unibo.sampleapp.model.object.api.Hazard;
import it.unibo.sampleapp.model.object.api.Lever;
import it.unibo.sampleapp.model.object.api.MovableIPlatform;
import it.unibo.sampleapp.model.object.api.Platform;
import it.unibo.sampleapp.model.object.api.Player;
import it.unibo.sampleapp.model.object.impl.ButtonImpl;
import it.unibo.sampleapp.model.object.impl.DoorImpl;
import it.unibo.sampleapp.model.object.api.Fan;
import it.unibo.sampleapp.model.object.impl.FanImpl;
import it.unibo.sampleapp.model.object.impl.Fireboy;
import it.unibo.sampleapp.model.object.impl.GemImpl;
import it.unibo.sampleapp.model.object.impl.HazardImpl;
import it.unibo.sampleapp.model.object.impl.LeverImpl;
import it.unibo.sampleapp.model.object.impl.MovablePlatformImpl;
import it.unibo.sampleapp.model.object.impl.PlatformImpl;
import it.unibo.sampleapp.model.object.impl.Watergirl;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Junit test for collision between players and game objects.
 */
class TestCollision {

    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 50;
    private static final int LEVEL_WIDTH = 800;
    private static final int LEVEL_HEIGHT = 600;
    private static final int HAZARD_WIDTH = 50;
    private static final int HAZARD_HEIGHT = 20;
    private static final int PLATFORM_WIDTH = 100;
    private static final int PLATFORM_HEIGHT = 20;
    private static final int GEM_SIZE = 20;
    private static final int BUTTON_WIDTH = 30;
    private static final int BUTTON_HEIGHT = 10;
    private static final int LEVER_WIDTH = 20;
    private static final int LEVER_HEIGHT = 50;
    private static final int DOOR_WIDTH = 40;
    private static final int DOOR_HEIGHT = 80;
    private static final int FAN_WIDTH = 100;
    private static final int FAN_HEIGHT = 20;
    private static final int WATERGIRL_START_X = 200;
    private static final int FIREBOY_NEAR_LEVER_X = 210;
    private static final int FIREBOY_FAR_LEVER_X = 150;
    private static final int FIREBOY_LEVER_Y = 400;
    private static final int FAN_PLAYER_X = 110;
    private static final int FAN_PLAYER_Y = 480;
    private static final int PLAYER_FAR_FAN_X = 400;
    private static final int PLAYER_FAR_FAN_Y = 120;
    private static final int PLATFORM_PLAYER_Y = 310;
    private static final int PLATFORM_PLAYER_X = 120;
    private static final int PLATFORM_PLAYER_TOP_Y = 270;
    private static final int PLATFORM_PLAYER_BOTTOM_Y = 315;
    private static final int PLATFORM_PLAYER_LEFT_X = 90;
    private static final int PLATFORM_PLAYER_RIGHT_X = 210;
    private static final int PLATFORM_SPEED_Y = 50;
    private static final int PLATFORM_SPEED_Y_NEG = -100;
    private static final int PLATFORM_SPEED_X = 100;
    private static final int BOUNDARY_OFFSET = 10;
    private static final int OUT_OF_BOUND = -10;
    private static final int TOLERANCE = 5;

    private CollisionFactory collisionFactory;
    private Game game;
    private Player fireboy;
    private Player watergirl;

    /**
     * Initialized the game environment before each test.
     */
    @BeforeEach
    void setUp() {
        collisionFactory = new CollisionFactoryImpl();
        fireboy = new Fireboy(100, 100, PLAYER_WIDTH, PLAYER_HEIGHT);
        watergirl = new Watergirl(WATERGIRL_START_X, 100, PLAYER_WIDTH, PLAYER_HEIGHT);

        final List<GameObject> objs = new ArrayList<>();
        final List<Player> players = new ArrayList<>();
        players.add(fireboy);
        players.add(watergirl);

        final Level level = new LevelImpl(objs, players, LEVEL_WIDTH, LEVEL_HEIGHT);
        game = new GameImpl(level);
    }

    @Test
    void collisionsWithGemsTest() {
        final Gem fGem = new GemImpl(new PositionImpl(110, 110), GEM_SIZE, GEM_SIZE, Gem.GemType.FIRE);
        final Gem wGem = new GemImpl(new PositionImpl(200, 110), GEM_SIZE, GEM_SIZE, Gem.GemType.WATER);

        collisionFactory.hitGems(watergirl, fGem).manageCollision(game);
        assertFalse(fGem.isCollected());

        collisionFactory.hitGems(fireboy, fGem).manageCollision(game);
        assertTrue(fGem.isCollected());

        collisionFactory.hitGems(fireboy, wGem).manageCollision(game);
        assertFalse(wGem.isCollected());

        collisionFactory.hitGems(watergirl, wGem).manageCollision(game);
        assertTrue(wGem.isCollected());
    }

    @Test
    void hitHazardTest() {
        final Hazard fHazard = new HazardImpl(new PositionImpl(100, 100), HAZARD_WIDTH, HAZARD_HEIGHT, Hazard.HazardType.FIRE);
        final Hazard wHazard = new HazardImpl(new PositionImpl(200, 100), HAZARD_WIDTH, HAZARD_HEIGHT, Hazard.HazardType.WATER);
        final Hazard aHazard = new HazardImpl(new PositionImpl(300, 100), HAZARD_WIDTH, HAZARD_HEIGHT, Hazard.HazardType.ACID);

        collisionFactory.hitHazard(fireboy, fHazard).manageCollision(game);
        assertFalse(game.isGameOver());

        collisionFactory.hitHazard(watergirl, wHazard).manageCollision(game);
        assertFalse(game.isGameOver());

        collisionFactory.hitHazard(fireboy, aHazard).manageCollision(game);
        assertTrue(fireboy.isDead() && game.isGameOver());

        collisionFactory.hitHazard(watergirl, aHazard).manageCollision(game);
        assertTrue(watergirl.isDead() && game.isGameOver());

        collisionFactory.hitHazard(fireboy, wHazard).manageCollision(game);
        assertTrue(fireboy.isDead() && game.isGameOver());

        collisionFactory.hitHazard(watergirl, fHazard).manageCollision(game);
        assertTrue(watergirl.isDead() && game.isGameOver());
    }

    @Test
    void doorUnlockedTest() {
        final Door fDoor = new DoorImpl(new PositionImpl(100, 100), DOOR_WIDTH, DOOR_HEIGHT, Door.DoorType.FIRE);
        final Door wDoor = new DoorImpl(new PositionImpl(200, 100), DOOR_WIDTH, DOOR_HEIGHT, Door.DoorType.WATER);

        collisionFactory.doorUnlockedCollision(fireboy, fDoor).manageCollision(game);
        assertTrue(fireboy.isAtDoor());

        collisionFactory.doorUnlockedCollision(watergirl, wDoor).manageCollision(game);
        assertTrue(watergirl.isAtDoor());

        collisionFactory.doorUnlockedCollision(fireboy, wDoor).manageCollision(game);
        assertFalse(fireboy.isAtDoor());

        collisionFactory.doorUnlockedCollision(watergirl, fDoor).manageCollision(game);
        assertFalse(watergirl.isAtDoor());
    }

    @Test
    void buttonTest() {
        final MovableIPlatform mPlatform = new MovablePlatformImpl(new PositionImpl(100, 400),
        PLATFORM_WIDTH, PLATFORM_HEIGHT, 0, false, 0);
        final Button button = new ButtonImpl(new PositionImpl(120, 450), BUTTON_WIDTH, BUTTON_HEIGHT, mPlatform);

        collisionFactory.buttonClickedCollision(watergirl, button).manageCollision(game);
        assertTrue(button.isPressed() && mPlatform.isActive());

        collisionFactory.buttonReleasedCollision(button).manageCollision(game);
        assertFalse(button.isPressed() && mPlatform.isActive());
    }

    @Test
    void leverTest() {
        final MovableIPlatform mPlatform = new MovablePlatformImpl(new PositionImpl(300, 400),
        PLATFORM_WIDTH, PLATFORM_HEIGHT, 0, false, 0);
        final Lever lever = new LeverImpl(new PositionImpl(200, 400), LEVER_WIDTH, LEVER_HEIGHT, mPlatform);

        fireboy.setPosition(new PositionImpl(FIREBOY_NEAR_LEVER_X, FIREBOY_LEVER_Y));
        collisionFactory.leverDisplacementCollision(fireboy, lever).manageCollision(game);
        assertTrue(lever.isActive() && mPlatform.isActive());

        fireboy.setPosition(new PositionImpl(FIREBOY_FAR_LEVER_X, FIREBOY_LEVER_Y));
        collisionFactory.leverDisplacementCollision(fireboy, lever).manageCollision(game);
        assertFalse(lever.isActive() && mPlatform.isActive());
    }

    @Test
    void fanTest() {
        final Fan fan = new FanImpl(new PositionImpl(100, 500), FAN_WIDTH, FAN_HEIGHT);

        fireboy.setPosition(new PositionImpl(FAN_PLAYER_X, FAN_PLAYER_Y));
        fireboy.setSpeedY(0);
        collisionFactory.playerOnFan(fireboy, fan).manageCollision(game);
        assertTrue(fan.isActive());

        fireboy.setPosition(new PositionImpl(PLAYER_FAR_FAN_X, PLAYER_FAR_FAN_Y));
        collisionFactory.playerOnFan(fireboy, fan).manageCollision(game);
        assertFalse(fan.isActive());
    }

    @Test
    void platformTest() {
        final Platform platform = new PlatformImpl(new PositionImpl(100, 300), PLATFORM_WIDTH, PLATFORM_HEIGHT);

        fireboy.setPosition(new PositionImpl(PLATFORM_PLAYER_X, PLATFORM_PLAYER_TOP_Y)); 
        fireboy.setSpeedY(PLATFORM_SPEED_Y);
        collisionFactory.platformCollisions(fireboy, platform).manageCollision(game);
        assertTrue(fireboy.isOnFloor());
        assertTrue(Math.abs(fireboy.getPosition().getY() - (platform.getPosition().getY() - fireboy.getHeight())) < TOLERANCE);
        assertTrue(fireboy.getSpeedY() <= 0);

        fireboy.setPosition(new PositionImpl(PLATFORM_PLAYER_X, PLATFORM_PLAYER_BOTTOM_Y));
        fireboy.setSpeedY(PLATFORM_SPEED_Y_NEG);
        collisionFactory.platformCollisions(fireboy, platform).manageCollision(game);
        assertFalse(fireboy.isOnFloor());
        assertEquals(0, fireboy.getSpeedY());
        assertTrue(fireboy.getPosition().getY() >= platform.getPosition().getY() + platform.getHeight() - TOLERANCE);

        fireboy.setPosition(new PositionImpl(PLATFORM_PLAYER_LEFT_X, PLATFORM_PLAYER_Y));
        fireboy.setSpeedX(PLATFORM_SPEED_X);
        collisionFactory.platformCollisions(fireboy, platform).manageCollision(game);
        assertTrue(fireboy.getSpeedX() <= 0);

        fireboy.setPosition(new PositionImpl(PLATFORM_PLAYER_RIGHT_X, PLATFORM_PLAYER_Y));
        fireboy.setSpeedX(PLATFORM_SPEED_Y_NEG);
        collisionFactory.platformCollisions(fireboy, platform).manageCollision(game);
        assertTrue(fireboy.getSpeedX() <= 0);
    }

    @Test
    void movablePlatformTest() {
        final MovableIPlatform mPlatform = new MovablePlatformImpl(new PositionImpl(100, 300),
        PLATFORM_WIDTH, PLATFORM_HEIGHT, -2, false, 0);
        mPlatform.move();
        fireboy.setPosition(new PositionImpl(PLATFORM_PLAYER_X, PLATFORM_PLAYER_TOP_Y));
        fireboy.setSpeedY(PLATFORM_SPEED_Y);
        collisionFactory.movablePlatformCollision(fireboy, mPlatform).manageCollision(game);
        assertTrue(fireboy.isOnFloor());

        final double expectedY = mPlatform.getPosition().getY() - fireboy.getHeight();
        assertEquals(expectedY, fireboy.getPosition().getY(), 0.0);
        assertEquals(0, fireboy.getSpeedY(), 0.0);
    }

    @Test
    void boundaryTest() {
        final double widht = game.getWidth();
        final double height = game.getHeight();

        fireboy.setPosition(new PositionImpl(OUT_OF_BOUND, 100));
        collisionFactory.boundaryCollisions(fireboy, BoundaryType.LEFT).manageCollision(game);
        assertEquals(0, fireboy.getPosition().getX());

        fireboy.setPosition(new PositionImpl(widht + BOUNDARY_OFFSET, 100));
        collisionFactory.boundaryCollisions(fireboy, BoundaryType.RIGHT).manageCollision(game);
        assertTrue(fireboy.getPosition().getX() <= widht - fireboy.getWidth());

        fireboy.setPosition(new PositionImpl(100, OUT_OF_BOUND));
        collisionFactory.boundaryCollisions(fireboy, BoundaryType.TOP).manageCollision(game);
        assertEquals(0, fireboy.getPosition().getY());

        fireboy.setPosition(new PositionImpl(100, height + BOUNDARY_OFFSET));
        collisionFactory.boundaryCollisions(fireboy, BoundaryType.BOTTOM).manageCollision(game);
        assertTrue(fireboy.getPosition().getY() <= height - fireboy.getHeight());
    }
}
