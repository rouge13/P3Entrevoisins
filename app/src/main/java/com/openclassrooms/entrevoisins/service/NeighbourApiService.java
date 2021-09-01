package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);
    void deleteNeighbourFavorites(Neighbour favoriteNeighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    // Add a neighbour in the favorite (true) in the state
    void setNeighbourFavorite(Neighbour neighbour);

    // cancel the neighbour in the favorite in the state
    void unSetNeighbourFavorite(Neighbour neighbour);

    // Add neighbour in the favorite list
    void addFavoriteInList(Neighbour favoriteNeighbour);

    // Get all the neighbours
    List<Neighbour> getNeighboursFavorite();
}
