package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoriteList = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        favoriteList.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void setNeighbourFavorite(Neighbour neighbour) {
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i) == neighbour){
                if (!neighbour.getIsFavorite()){
                    neighbour.setIsFavorite(true);
                    neighbours.get(i).setIsFavorite(true);
                } else {
                    neighbour.setIsFavorite(false);
                    neighbours.get(i).setIsFavorite(false);
                }
            }
        }
    }

    @Override
    public List<Neighbour> getNeighbourFavorite(Neighbour neighbour) {
        // TODO
        // récupérer voisins qui sont ajouté en favoris.
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbour.getIsFavorite()){
                favoriteList.add(neighbour);
            }
        }
        return favoriteList;
    }
}
