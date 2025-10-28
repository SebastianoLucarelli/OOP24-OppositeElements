package it.unibo.sampleapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.sampleapp.model.level.api.Level;
import it.unibo.sampleapp.model.level.impl.LevelLoaderImpl;
import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Player;

/**
 * test for level loader.
 */
class LevelLoaderTest {

    private static final String BASE_LEVEL = "level2.txt";
    private static final String OBJECT_LEVEL = "level2Objects.txt";
    private static final LevelLoaderImpl LOADED_LEVEL = new LevelLoaderImpl();

    /**
     * testing the load of the level base. test with the level 2.
     */
    @Test
    void testLoadBase() {
        final Level level = LOADED_LEVEL.loadLevel(BASE_LEVEL);
        final List<GameObject> objects = level.getGameObjects();
        final List<Player> players = level.getPlayers();

        assertNotNull(level);
        assertFalse(objects.isEmpty(), "Expected platforms to be loaded");
        assertEquals(0, players.size());
        assertTrue(level.getWidth() > 0 && level.getHeight() > 0);
    }

    /**
     * testing the load of the level objects. test with the level 2 objects.
     * test if all the objects are loaded correctly.
     */
    @Test
    void testLoadObjects() {
        final Level level = LOADED_LEVEL.loadLevelWithObjects(BASE_LEVEL, OBJECT_LEVEL);
        final List<GameObject> objects = level.getGameObjects();
        final List<Player> players = level.getPlayers();

        assertNotNull(level);
        assertFalse(objects.isEmpty(), "Exptected objects to be loaded");
        assertFalse(players.isEmpty(), "Exptected players to be loaded");

        final boolean hasPlatform = objects.stream().anyMatch(obj -> obj.getClass().getSimpleName().contains("Platform"));
        final boolean hasHazard = objects.stream().anyMatch(obj -> obj.getClass().getSimpleName().contains("Hazard"));
        final boolean hasButton = objects.stream().anyMatch(obj -> obj.getClass().getSimpleName().contains("Button"));
        final boolean hasLever = objects.stream().anyMatch(obj -> obj.getClass().getSimpleName().contains("Lever"));
        final boolean hasDoor = objects.stream().anyMatch(obj -> obj.getClass().getSimpleName().contains("Door"));
        final boolean hasGem = objects.stream().anyMatch(obj -> obj.getClass().getSimpleName().contains("Gem"));
        final boolean hasFan = objects.stream().anyMatch(obj -> obj.getClass().getSimpleName().contains("Fan"));

        assertTrue(hasPlatform, "Expected at least one platform");
        assertTrue(hasHazard, "Expected at least one hazard");
        assertTrue(hasButton, "Expected at least one button");
        assertTrue(hasLever, "Expected at least one lever");
        assertTrue(hasDoor, "Expected at least one door");
        assertTrue(hasGem, "Expected at least one Gem");
        assertTrue(hasFan, "Expected at least one Fan");

        assertTrue(level.getWidth() > 0 && level.getHeight() > 0);
    }

}
