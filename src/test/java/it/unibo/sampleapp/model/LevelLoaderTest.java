package it.unibo.sampleapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.sampleapp.model.level.api.Level;
import it.unibo.sampleapp.model.level.impl.LevelLoaderImpl;
import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Player;

class LevelLoaderTest {
    
    private static final String BASE_LEVEL = "level2.txt";
    private static final String OBJECT_LEVEL = "level2Objects.txt";
    private static final LevelLoaderImpl load = new LevelLoaderImpl();

    @Test
    void testVisibility() {
       final InputStream base = LevelLoaderImpl.class.getResourceAsStream("/level/" + BASE_LEVEL);
       final InputStream obj = LevelLoaderImpl.class.getResourceAsStream("/level/" + OBJECT_LEVEL);
       assertNotNull(obj);
       assertNotNull(base);
    }

    @Test
    void testLoadBase() {
        final Level level = load.loadLevel(BASE_LEVEL);
        final List<GameObject> objects = level.getGameObjects();
        final List<Player> players = level.getPlayers();

        assertNotNull(level);
        assertFalse(objects.isEmpty(), "Expected platforms to be loaded");
        assertEquals(0, players.size());
        assertTrue(level.getWidth() > 0 && level.getHeight() > 0);
    }

    @Test
    void testLoadObjects() {
        final Level level = load.loadLevelWithObjects(BASE_LEVEL, OBJECT_LEVEL);
        final List<GameObject> objects = level.getGameObjects();
        final List<Player> players = level.getPlayers();

        assertNotNull(level);
        assertFalse(objects.isEmpty(), "Exptected objects to be loaded");
        assertFalse(players.isEmpty(), "Exptected players to be loaded");
    }

}
