package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
//import com.openclassrooms.entrevoisins.service.NeighbourDao;
//import com.openclassrooms.entrevoisins.service.NeighbourRoomDatabase;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Julien HAMMER - Apprenti Java with openclassrooms on .
 */
public class InfoNeighbourActivity extends AppCompatActivity {
    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;
    private ImageView mNeighbourAvatar;
    private FloatingActionButton mNeighbourFavoriteStates;
    private TextView mNeighbourName;
    private TextView mNeighbourNameImage;
    private TextView mNeighbourAddress;
    private TextView mNeighbourPhoneNumber;
    private TextView mNeighbourLink;
    private FloatingActionButton mBackwardButton;
    private static final String BUNDLE_ABOUT_ME = "A propos de moi";
    private TextView mNeighbourAboutMe;
    public static final String FACEBOOK_LINK = "www.facebook.fr/";
    private boolean mNeighbourIsFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_info);
        mApiService = DI.getNeighbourApiService();
        System.out.println("Neighbour element ::" + mNeighbourIsFavorite);

        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // Puis on récupère le voisin
        mNeighbour = i.getParcelableExtra("Neighbours");
        mNeighbourAvatar = findViewById(R.id.item_avatar_user_image);
        mNeighbourFavoriteStates = findViewById(R.id.item_add_favorite_user_button);

        mNeighbourNameImage = findViewById(R.id.item_name_user_with_image_text);
        mNeighbourName = findViewById(R.id.item_name_user_text);
        mNeighbourAddress = findViewById(R.id.item_address_user_text);
        mNeighbourPhoneNumber = findViewById(R.id.item_phone_user_text);
        mNeighbourLink = findViewById(R.id.item_link_user_text);
        mNeighbourAboutMe = findViewById(R.id.item_about_content_user_text);
        mBackwardButton = findViewById(R.id.item_backward_button);

        // viewbinding => findViewById
        mNeighbourName.setText(mNeighbour.getName());
        Glide.with(this).load(mNeighbour.getAvatarUrl()).
                placeholder(R.drawable.ic_account).centerCrop().into(mNeighbourAvatar);
        mNeighbourNameImage.setText(mNeighbour.getName());
        mNeighbourAddress.setText(mNeighbour.getAddress());
        mNeighbourPhoneNumber.setText(mNeighbour.getPhoneNumber());
        mNeighbourLink.setText(FACEBOOK_LINK + mNeighbour.getName());
        mNeighbourAboutMe.setText(mNeighbour.getAboutMe());
//        mBackwardButton.setBackgroundResource(R.drawable.ic_baseline_arrow_back_8);
//        mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_border_8_black);
        mNeighbourIsFavorite = mNeighbour.getIsFavorite();
        if (mNeighbourIsFavorite) {
            mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_8_yellow);
        } else {
            mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_border_8_black);
        }
        mNeighbourFavoriteStates.hide();
        mNeighbourFavoriteStates.show();
//        System.out.println(mNeighbourOnOff);
//        if (!mNeighbourOnOff) {
//            mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_border_8_black);
//            System.out.println("AddFavorite::false");
//        }else {
//            mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_8_yellow);
//            System.out.println("AddFavorite::true");
//        }



        // Gestion des favoris à faire.
        mNeighbourFavoriteStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Here's the favorite states", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                mApiService.setNeighbourFavorite(mNeighbour);
                mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_8_yellow);
                System.out.println("AddFavorite::true");
                mNeighbourIsFavorite = mNeighbour.getIsFavorite();
                System.out.println(mNeighbourIsFavorite);
                if (!mNeighbourIsFavorite) {
                    mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_border_8_black);
                }else {
                    mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_8_yellow);
                }
                mNeighbourFavoriteStates.hide();
                mNeighbourFavoriteStates.show();
            }
        });
        // Retour en arrière sur la dernière fenêtre active
        mBackwardButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's the Backward button", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
            }
        });

        final TextView aboutMeTextView = (TextView) findViewById(R.id.item_profil_user_text);
        aboutMeTextView.setText(R.string.activity_neighbour_info_about_me);



    }

}
