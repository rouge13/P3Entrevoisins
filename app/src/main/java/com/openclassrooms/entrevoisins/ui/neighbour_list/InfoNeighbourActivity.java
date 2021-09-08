package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Julien HAMMER - Apprenti Java with openclassrooms on .
 */
public class InfoNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.item_avatar_user_image)
    public ImageView mNeighbourAvatar;
    @BindView(R.id.item_name_user_with_image_text)
    public TextView mNeighbourNameImage;
    @BindView(R.id.item_name_user_text)
    public TextView mNeighbourName;
    @BindView(R.id.item_address_user_text)
    public TextView mNeighbourAddress;
    @BindView(R.id.item_phone_user_text)
    public TextView mNeighbourPhoneNumber;
    @BindView(R.id.item_link_user_text)
    public TextView mNeighbourLink;
    @BindView(R.id.item_about_content_user_text)
    public TextView mNeighbourAboutMe;
    @BindView(R.id.item_backward_button)
    public ImageView mBackwardButton;
    @BindView(R.id.item_profil_user_text)
    public TextView mAboutMeTextView;
    @BindView(R.id.item_add_favorite_user_button)
    public FloatingActionButton mNeighbourFavoriteStates;
    @BindDrawable(R.drawable.ic_baseline_star_border_24_black)
    public Drawable mFavoriteFalse;
    @BindDrawable(R.drawable.ic_baseline_star_24_yellow)
    public Drawable mFavoriteTrue;

    public NeighbourApiService mApiService;
    public Neighbour mNeighbour;
//    public static final String BUNDLE_ABOUT_ME = "A propos de moi";
    public static final String FACEBOOK_LINK = "www.facebook.fr/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_info);
        mApiService = DI.getNeighbourApiService();
        ButterKnife.bind(this);
        getNeighbour();
        // A mettre en commentaire pour test sur le floating action button
        configureFloatingActionButtonBackward();
        configureFavoritesActionButtonStates();
        // Gestion des favoris à faire.
        mNeighbourFavoriteStates.setOnClickListener(view -> {
            if (mNeighbour != null) {
                if (!mApiService.getNeighboursFavorite().contains(mNeighbour)) {
                    mApiService.setNeighbourFavorite(mNeighbour);
                    mApiService.addFavoriteInList(mNeighbour);
                    mNeighbourFavoriteStates.setImageDrawable(mFavoriteTrue);
                } else {
                    mApiService.unSetNeighbourFavorite(mNeighbour);
                    mApiService.deleteNeighbourFavorites(mNeighbour);
                    mNeighbourFavoriteStates.setImageDrawable(mFavoriteFalse);
                }
            }
        });
    }
    private void getNeighbour() {
        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();
        // Puis on récupère le voisin
        mNeighbour = i.getParcelableExtra("Neighbours");
        mNeighbourName.setText(mNeighbour.getName());
        mNeighbourNameImage.setText(mNeighbour.getName());
        mNeighbourAddress.setText(mNeighbour.getAddress());
        mNeighbourPhoneNumber.setText(mNeighbour.getPhoneNumber());
        mNeighbourLink.setText(FACEBOOK_LINK + mNeighbour.getName());
        mNeighbourAboutMe.setText(mNeighbour.getAboutMe());
        mAboutMeTextView.setText(R.string.activity_neighbour_info_about_me);
    }
    // Retour en arrière sur la dernière fenêtre active
    private void configureFloatingActionButtonBackward() {
        Glide.with(this).load(mNeighbour.getAvatarUrl()).
                placeholder(R.drawable.ic_account).centerCrop().into(mNeighbourAvatar);
        mBackwardButton.setOnClickListener (view -> {
            Snackbar.make(view, "Here's the Backward button", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            finish();
        });
    }
    // Stylisé le bouton favoris en fonction du status du voisin si c'est favoris ou pas.
    private void configureFavoritesActionButtonStates() {
        mNeighbourFavoriteStates.setImageDrawable(mFavoriteFalse);
        if (mNeighbour != null) {
            if (!mApiService.getNeighboursFavorite().contains(mNeighbour)) {
                mNeighbourFavoriteStates.setImageDrawable(mFavoriteFalse);
            } else {
                mNeighbourFavoriteStates.setImageDrawable(mFavoriteTrue);
            }
        }
    }
}
