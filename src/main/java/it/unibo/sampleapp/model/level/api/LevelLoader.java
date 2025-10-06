package it.unibo.sampleapp.model.level.api;

/**
 * intarface for loading levels.
 */
@FunctionalInterface
public interface LevelLoader {

    /**
     * Loads a level from a resources file.
     *
     * @param fileName the name of the file in resources\level
     * @return the loaded level
     */
    Level loadLevel(String fileName);

}
