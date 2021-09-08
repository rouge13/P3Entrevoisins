package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {
    private NeighbourApiService service;
    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }
    @Test
    public void getNeighboursFavoriteWithSuccess() {
        List<Neighbour> neighboursFavorite = service.getNeighboursFavorite();
        List<Neighbour> expectedNeighboursFavorite = DummyNeighbourGenerator.DUMMY_FAVORITES_LIST;
        assertThat(neighboursFavorite, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighboursFavorite.toArray()));
    }
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
        assertFalse(service.getNeighboursFavorite().contains(neighbourToDelete));
    }
    @Test
    public void deleteNeighbourFavoritesWithSuccess() {
        Neighbour neighbourFavoriteToDelete = service.getNeighbours().get(0);
        service.deleteNeighbourFavorites(neighbourFavoriteToDelete);
        assertFalse(service.getNeighboursFavorite().contains(neighbourFavoriteToDelete));
    }
    @Test
    public void createNeighbourWithSuccess() {
        Neighbour expectedNeighbour = new Neighbour(100,"", "", "", ""
               , "", false);
        service.createNeighbour(expectedNeighbour);
        assertTrue(service.getNeighbours().contains(expectedNeighbour));
    }
    @Test
    public void addFavoriteNeighbourWithSuccess() {
        service.getNeighboursFavorite().clear();
        Neighbour favoriteNeighbourToAdd = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        service.addFavoriteInList(favoriteNeighbourToAdd);
        assertTrue(service.getNeighboursFavorite().contains(favoriteNeighbourToAdd));
    }
    @Test
    public void setNeighbourFavoriteStatus() {
        Neighbour neighbour = service.getNeighbours().get(0);
        assertFalse(service.getNeighbours().get(0).getIsFavorite());
        service.setNeighbourFavorite(neighbour);
        assertTrue(service.getNeighbours().get(0).getIsFavorite());
    }
    @Test
    public void unSetNeighbourFavorite() {
        Neighbour neighbourFavorite = service.getNeighbours().get(0);
        assertFalse(service.getNeighbours().get(0).getIsFavorite());
        service.unSetNeighbourFavorite(neighbourFavorite);
        assertFalse(service.getNeighbours().get(0).getIsFavorite());
    }
}
