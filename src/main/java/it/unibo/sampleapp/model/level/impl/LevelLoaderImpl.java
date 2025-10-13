package it.unibo.sampleapp.model.level.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import it.unibo.sampleapp.model.level.api.Level;
import it.unibo.sampleapp.model.level.api.LevelLoader;
import it.unibo.sampleapp.model.object.api.Door;
import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Hazard;
import it.unibo.sampleapp.model.object.api.Player;
import it.unibo.sampleapp.model.object.impl.ButtonImpl;
import it.unibo.sampleapp.model.object.impl.DoorImpl;
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
 * implementation of the LevelLoader.
 */
public class LevelLoaderImpl implements LevelLoader {

    private static final int TILE_SIZE = 64;
    private static final int DIRECTION = 1;

    /**
     * {@inheritDoc}
     */
    @Override
    public Level loadLevel(final String fileName) {
        final List<GameObject> objects = new ArrayList<>();
        final List<Player> players = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(LevelLoaderImpl.class.
        getResourceAsStream("/level/" + fileName), StandardCharsets.UTF_8))) {
            String line = br.readLine();
            int row = 0;
            int cols = 0;

            while (line != null) {
                cols = line.length();

                for (int col = 0; col < line.length(); col++) {
                    final char c = line.charAt(col);
                    final int x = col * TILE_SIZE;
                    final int y = row * TILE_SIZE;

                    switch (c) {
                        case 'F': 
                            players.add(new Fireboy(x, y, TILE_SIZE, TILE_SIZE));
                            break;
                        case 'W':
                            players.add(new Watergirl(x, y, TILE_SIZE, TILE_SIZE));
                            break;
                        case 'P':
                            objects.add(new PlatformImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE));
                            break;
                        case 'E':
                            objects.add(new DoorImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE, Door.DoorType.FIRE));
                            break;
                        case 'Z':
                            objects.add(new DoorImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE, Door.DoorType.WATER));
                            break;
                        case 'L':
                            objects.add(new LeverImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE));
                            break;
                        case 'B':
                            objects.add(new ButtonImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE));
                            break;
                        case 'V':
                            objects.add(new FanImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE));
                            break;
                        case 'M':
                            objects.add(new MovablePlatformImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE,
                                    4, false, DIRECTION));
                            break;
                        case 'O':
                            objects.add(new MovablePlatformImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE,
                                    4, true, DIRECTION));
                            break;
                        case 'G':
                            objects.add(new GemImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE, GemImpl.GemType.FIRE));
                            break;
                        case 'D':
                            objects.add(new GemImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE, GemImpl.GemType.WATER));
                            break;
                        case 'A':
                            objects.add(new HazardImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE, Hazard.HazardType.ACID));
                            break;
                        case 'X':
                            objects.add(new HazardImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE, Hazard.HazardType.FIRE));
                            break;
                        case 'Y':
                            objects.add(new HazardImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE, Hazard.HazardType.WATER));
                            break;
                        case '.':
                            break;
                        default:
                            break;
                    }
                }
                row++;
                line = br.readLine();
            }

            return new LevelImpl(objects, players, cols * TILE_SIZE, TILE_SIZE * row);
        } catch (final IOException e) {
            throw new IllegalStateException("Error upload the date", e);
        }
    }
}
