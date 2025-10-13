package it.unibo.sampleapp.model.level.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Player;
import it.unibo.sampleapp.model.level.api.Level;

/**
 * implementation of the Level interface.
 */
public class LevelImpl implements Level {

    private final List<GameObject> objects;
    private final List<Player> players;
    private final int width;
    private final int height;

    /**
     * costructor of the levelImpl.
     *
     * @param objects is a ArrayList whit all the element of the list
     * @param players is a ArrayList whit the 2 player (it will be change whit 2 different player)
     * @param width is the width of the level
     * @param height is the height of the level
     */
    public LevelImpl(final List<GameObject> objects, final List<Player> players, final int width, final int height) {
        this.objects = new ArrayList<>(objects);
        this.players = new ArrayList<>(players);
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getGameObjects() {
        return new ArrayList<>(this.objects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(this.players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.height;
    }

}
