package it.unibo.sampleapp.model.level.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.sampleapp.model.level.api.Level;
import it.unibo.sampleapp.model.level.api.LevelLoader;
import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Gem;
import it.unibo.sampleapp.model.object.api.Hazard.HazardType;
import it.unibo.sampleapp.model.object.api.MovableIPlatform;
import it.unibo.sampleapp.model.object.api.Player;
import it.unibo.sampleapp.model.object.api.Door.DoorType;
import it.unibo.sampleapp.model.object.impl.ButtonImpl;
import it.unibo.sampleapp.model.object.impl.DoorImpl;
import it.unibo.sampleapp.model.object.impl.Fireboy;
import it.unibo.sampleapp.model.object.impl.GemImpl;
import it.unibo.sampleapp.model.object.impl.HazardImpl;
import it.unibo.sampleapp.model.object.impl.LeverImpl;
import it.unibo.sampleapp.model.object.impl.MovablePlatformImpl;
import it.unibo.sampleapp.model.object.impl.PlatformImpl;
import it.unibo.sampleapp.model.object.impl.Watergirl;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Implementation of the LevelLoader.
 */
public class LevelLoaderImpl implements LevelLoader {

    private static final int TILE_SIZE = 36;
    private static final int DIRECTION = 1;
    private static final int PLATFORM_WIDTH_INDEX = 4;
    private static final int PLATFORM_HEIGHT_INDEX = 5;
    private static final int PLATFORM_DIRECTION_INDEX = 6;
    private static final int BUTTON_HEIGHT = 10;
    private static final int HAZARD_HEIGHT = TILE_SIZE / 2;
    private static final int HAZARD_WIDTH = TILE_SIZE * 2;

    /**
     * Loads only the base grid from a level file (e.g., platforms).
     *
     * @param fileName the name of the base level file
     * @param dimensions an array to store the dimensions [cols, rows]
     * @return a list of GameObjects representing the static grid
     */
    private List<GameObject> loadBaseGrid(final String fileName, final int[] dimensions) {
        final List<GameObject> objects = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                LevelLoaderImpl.class.getResourceAsStream("/level/" + fileName), StandardCharsets.UTF_8))) {

            String line = br.readLine();
            int row = 0;
            int cols = 0;

            while (line != null) {
                cols = Math.max(cols, line.length());
                for (int col = 0; col < line.length(); col++) {
                    final char c = line.charAt(col);
                    final int x = col * TILE_SIZE;
                    final int y = row * TILE_SIZE;

                    if (c == 'P') {
                        objects.add(new PlatformImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE));
                    }
                }
                row++;
                line = br.readLine();
            }

            dimensions[0] = cols;
            dimensions[1] = row;

        } catch (final IOException e) {
            throw new IllegalStateException("Error loading base grid", e);
        }

        return objects;
    }

    /**
     * Loads the full level including base grid and dynamic objects.
     *
     * @param baseFile the file containing the grid structure
     * @param objectFile the file containing dynamic objects
     * @return the complete Level object
     */
    public Level loadLevelWithObjects(final String baseFile, final String objectFile) {
        final int[] dimensions = new int[2]; // [cols, rows]
        final List<GameObject> objects = loadBaseGrid(baseFile, dimensions);
        final List<Player> players = new ArrayList<>();
        final Map<String, GameObject> objectById = new HashMap<>();

        try (BufferedReader objReader = new BufferedReader(new InputStreamReader(
                LevelLoaderImpl.class.getResourceAsStream("/level/" + objectFile), StandardCharsets.UTF_8))) {

            String objLine = objReader.readLine();
            while (objLine != null) {
                final String[] tokens = objLine.split(" ");
                final String type = tokens[0];

                switch (type) {
                    case "F" -> {
                        final int x = Integer.parseInt(tokens[1]) * TILE_SIZE;
                        final int y = Integer.parseInt(tokens[2]) * TILE_SIZE;
                        players.add(new Fireboy(x, y, TILE_SIZE, TILE_SIZE));
                    }
                    case "W" -> {
                        final int x = Integer.parseInt(tokens[1]) * TILE_SIZE;
                        final int y = Integer.parseInt(tokens[2]) * TILE_SIZE;
                        players.add(new Watergirl(x, y, TILE_SIZE, TILE_SIZE));
                    }
                    case "M" -> {
                        final String id = tokens[1];
                        final int x = Integer.parseInt(tokens[2]) * TILE_SIZE;
                        final int y = Integer.parseInt(tokens[3]) * TILE_SIZE;
                        final int w = Integer.parseInt(tokens[PLATFORM_WIDTH_INDEX]) * TILE_SIZE;
                        final int h = Integer.parseInt(tokens[PLATFORM_HEIGHT_INDEX]) * TILE_SIZE;
                        final boolean horizontal = "horizontal".equals(tokens[PLATFORM_DIRECTION_INDEX]);
                        final MovablePlatformImpl mp = new MovablePlatformImpl(
                            new PositionImpl(x, y), w, h, 4, horizontal, DIRECTION);
                        objectById.put(id, mp);
                        objects.add(mp);
                    }
                    case "B" -> {
                        final int x = Integer.parseInt(tokens[1]) * TILE_SIZE;
                        final int y = Integer.parseInt(tokens[2]) * TILE_SIZE - BUTTON_HEIGHT;
                        final String targetId = tokens[3];
                        final GameObject target = objectById.get(targetId);
                        if (target == null) {
                            throw new IllegalStateException("Missing target: " + targetId);
                        }
                        final MovableIPlatform finaltarget = (MovableIPlatform) target;
                        objects.add(new ButtonImpl(new PositionImpl(x, y), TILE_SIZE, BUTTON_HEIGHT, finaltarget));
                    }
                    case "L" -> {
                        final int x = Integer.parseInt(tokens[1]) * TILE_SIZE;
                        final int y = Integer.parseInt(tokens[2]) * TILE_SIZE;
                        final String targetId = tokens[3];
                        final GameObject target = objectById.get(targetId);
                        if (target == null) {
                            throw new IllegalStateException("Missing target: " + targetId);
                        }
                        final MovableIPlatform finaltarget = (MovableIPlatform) target;
                        objects.add(new LeverImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE, finaltarget));
                    }
                    case "E" -> {
                        final int x = Integer.parseInt(tokens[1]) * TILE_SIZE;
                        final int y = Integer.parseInt(tokens[2]) * TILE_SIZE;
                        objects.add(new DoorImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE * 2, DoorType.FIRE));
                    }
                    case "Z" -> {
                        final int x = Integer.parseInt(tokens[1]) * TILE_SIZE;
                        final int y = Integer.parseInt(tokens[2]) * TILE_SIZE;
                        objects.add(new DoorImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE * 2, DoorType.WATER));
                    }
                    case "A", "X", "Y" -> {
                        final int x = Integer.parseInt(tokens[1]) * TILE_SIZE;
                        final int y = Integer.parseInt(tokens[2]) * TILE_SIZE;
                        final HazardType hazardType = switch (type) {
                            case "A" -> HazardType.ACID;
                            case "X" -> HazardType.FIRE;
                            case "Y" -> HazardType.WATER;
                            default -> throw new IllegalArgumentException("Unknown hazard type: " + type);
                        };
                        objects.add(new HazardImpl(new PositionImpl(x, y), HAZARD_WIDTH, HAZARD_HEIGHT, hazardType));
                    }
                    case "G", "D" -> {
                        final int x = Integer.parseInt(tokens[1]) * TILE_SIZE;
                        final int y = Integer.parseInt(tokens[2]) * TILE_SIZE;
                        objects.add(new GemImpl(new PositionImpl(x, y), TILE_SIZE, TILE_SIZE, 
                        //type.equals("G") ? Gem.GemType.FIRE : Gem.GemType.WATER));
                        "G".equals(type) ? Gem.GemType.FIRE : Gem.GemType.WATER));
                    }
                    default -> throw new IllegalArgumentException("Unknown object type: " + type);
                }

                objLine = objReader.readLine();
            }

        } catch (final IOException e) {
            throw new IllegalStateException("Error loading object level", e);
        }

        return new LevelImpl(objects, players, dimensions[0] * TILE_SIZE, dimensions[1] * TILE_SIZE);
    }

    /**
     * Loads a level using only the base grid file.
     *
     * @param fileName the name of the level file
     * @return the Level object with static platforms only
     */
    @Override
    public Level loadLevel(final String fileName) {
        final int[] dimensions = new int[2];
        final List<GameObject> objects = loadBaseGrid(fileName, dimensions);
        final List<Player> players = new ArrayList<>();
        return new LevelImpl(objects, players, dimensions[0] * TILE_SIZE, dimensions[1] * TILE_SIZE);
    }
}
