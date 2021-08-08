package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoriteListNeighbours = DummyNeighbourGenerator.generateFavorites();
//    public List<Neighbour> favoriteListNeighbours = new ArrayList<>();


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
        favoriteListNeighbours.remove(neighbour);
    }

    public void deleteNeighbourFavorites(Neighbour favoriteNeighbour) {
        favoriteListNeighbours.remove(favoriteNeighbour);
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
                if (!neighbour.getIsFavorite() && !favoriteListNeighbours.contains(neighbours.get(i))){
//                    neighbour.setIsFavorite(true);
                    neighbour.setIsFavorite(true);
                    favoriteListNeighbours.add(neighbour);
                } else {
//                    neighbour.setIsFavorite(false);
                    neighbour.setIsFavorite(false);
//                    favoriteListNeighbours.remove(neighbour);
                    favoriteListNeighbours.remove(neighbour);
                }
            }
        }

    }

    @Override
    public List<Neighbour> getNeighboursFavorite() {
        // TODO
        // récupérer voisins qui sont ajouté en favoris.
//        List<Neighbour> favoriteListNeighbours = new ArrayList<>();
//        for (int i = 0; i < neighbours.size(); i++) {
////            if (neighbours.get(i).getIsFavorite()){
//                if (!favoriteListNeighbours.contains(neighbours.get(i)) && neighbours.get(i).getIsFavorite()){
//                    favoriteListNeighbours.add(neighbours.get(i));
//                }
////                else {
////                    favoriteListNeighbours.remove(neighbours.get(i));
////                }
////            }
//        }
        return favoriteListNeighbours;
    }


    public void addFavoriteInList(Neighbour favoriteNeighbour) {
        favoriteListNeighbours.add(favoriteNeighbour);
    }


}
