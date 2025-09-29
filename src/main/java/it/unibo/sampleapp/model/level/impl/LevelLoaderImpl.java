package it.unibo.sampleapp.model.level.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import it.unibo.sampleapp.model.level.api.Level;
import it.unibo.sampleapp.model.level.api.LevelLoader;
import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Player;
import it.unibo.sampleapp.model.object.impl.PlatformImpl;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * implementation of the LevelLoader.
 */
public class LevelLoaderImpl implements LevelLoader {

    private static final int TILE_SIZE = 64;

    /**
     * {@inheritDoc}
     */
    @Override
    public Level loadLevel(final String fileName) {
        final List<GameObject> objects = new ArrayList<>();
        final List<Player> players = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(LevelLoaderImpl.class.
        getResourceAsStream("/level/" + fileName), StandardCharsets.UTF_8))) {
            int row = 0;
            int cols = 0;
            String line = br.readLine();
            while (line != null) {
                cols = line.length();
                for (int col = 0; col < line.length(); col++) {
                    final char c = line.charAt(col);
                    final int x = col * TILE_SIZE;
                    final int y = row * TILE_SIZE;
                    switch (c) {
                        case 'P' -> objects.add(new PlatformImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE));
                        default -> { }
                    }
                }
                line = br.readLine();
                row++;
            }
            return new LevelImpl(objects, players, cols * TILE_SIZE, TILE_SIZE * row);
        } catch (final IOException e) {
            throw new IllegalStateException("Error upload the date", e);
        }
    }

}
