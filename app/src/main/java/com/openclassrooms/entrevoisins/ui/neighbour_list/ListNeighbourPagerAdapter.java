package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    // Vérifier en boolean si c'est les voisins ou les favoris voisins
    @Override
    public Fragment getItem(int position) {
             if (position == 1) {
            return NeighbourFragment.newInstance(true);
        }
        return NeighbourFragment.newInstance(false);

    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {return 2;}
}