package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


//public class NeighbourFragment extends Fragment implements MyNeighbourRecyclerViewAdapter.OnItemClickListener {
    public class NeighbourFragment extends Fragment {


    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
//    private List<Neighbour> mNeighboursFavorite;
    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter mNeighbourRecyclerViewAdapter;
    private boolean isFavorite;
    public static final String IS_FAVORITE = "position";


    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(boolean isFavorite) {
        NeighbourFragment fragment = new NeighbourFragment();
//        Nommer le fragment PAGE_COURANTE position et qui aide a déterminer sur quelle page nous sommes. (favorites ou neighbours)
        Bundle argumentsPage = new Bundle();
//        Stocker position en key et newInstance retourne la page favorite ou neighbours
        argumentsPage.putBoolean(IS_FAVORITE, isFavorite);
//        System.out.println("POSITION ::" + position);
//        Paramètrer l'argument de la page
        fragment.setArguments(argumentsPage);
//        Retourner le fragment selon la page sélectionnée
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
//      Récupérer la page courante pour déterminer l'affiche soit tous les neighbours soit les favoris à l'aide de la clé PAGE_COURANTE qui est égale à position.
        isFavorite = getArguments().getBoolean(IS_FAVORITE);
//        Charger la liste suivant la page sélectionné
        initList();
        return view;
    }

    /**
     * Init the List of neighbours
     * utilisation de la méthode de services DummyNeighbourApiService getNeighbourFavorite
     * pour la generation de liste de favoris qui se met à jour en fonction des ajouts réalisés
     */
    private void initList() {
// Charger la liste en fonction du choix de l'utilisateur pour afficher les différents voisins ou soit ses favoris.
        if (isFavorite){
            System.out.println("PAGE_COURANTE ::" + getArguments().getString(IS_FAVORITE));
            mNeighbours = mApiService.getNeighboursFavorite();
//            mNeighbourRecyclerViewAdapter = new MyNeighbourRecyclerViewAdapter(mApiService.getNeighboursFavorite());
//           mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighboursFavorite));
        } else {
            System.out.println("PAGE_COURANTE ::" + getArguments().getString(IS_FAVORITE));
            mNeighbours = mApiService.getNeighbours();
//            mNeighbourRecyclerViewAdapter = new MyNeighbourRecyclerViewAdapter(mApiService.getNeighbours());
//            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
        }
//        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
//        mNeighbourRecyclerViewAdapter.setOnItemClickListener(this);
//        mRecyclerView.setAdapter(mNeighbourRecyclerViewAdapter);
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
//        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
//        initList();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

//    @Override
//    public void onItemClick(int position) {
//        Intent intent = new Intent(getActivity(), InfoNeighbourActivity.class);
//        intent.putExtra("Neighbours", mNeighbours.get(position));
//        startActivity(intent);
//    }

}
