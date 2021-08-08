package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    @BindView(R.id.item_add_favorite_user_button)
    public FloatingActionButton mNeighbourFavoriteStates;
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
    public FloatingActionButton mBackwardButton;
    @BindView(R.id.item_profil_user_text)
    public TextView mAboutMeTextView;
    @BindDrawable(R.drawable.ic_baseline_star_border_8_black)
    public Drawable mFavoriteFalse;
    @BindDrawable(R.drawable.ic_baseline_star_8_yellow)
    public Drawable mFavoriteTrue;
    private static final String TAG = InfoNeighbourActivity.class.getSimpleName();
//    mNeighbourFavoriteStates = findViewById(R.id.item_add_favorite_user_button);
//    mNeighbourNameImage = findViewById(R.id.item_name_user_with_image_text);
//    mNeighbourName = findViewById(R.id.item_name_user_text);
//    mNeighbourAddress = findViewById(R.id.item_address_user_text);
//    mNeighbourPhoneNumber = findViewById(R.id.item_phone_user_text);
//    mNeighbourLink = findViewById(R.id.item_link_user_text);
//    mNeighbourAboutMe = findViewById(R.id.item_about_content_user_text);
//    mBackwardButton = findViewById(R.id.item_backward_button);
    public NeighbourApiService mApiService;
    public Neighbour mNeighbour;
//    public static final String BUNDLE_ABOUT_ME = "A propos de moi";
    public static final String FACEBOOK_LINK = "www.facebook.fr/";
//    private boolean mNeighbourIsFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_info);
        mApiService = DI.getNeighbourApiService();
//        System.out.println("Neighbour element ::" + mNeighbour.getIsFavorite());
        ButterKnife.bind(this);
//        Log.d("TestLog","test");
//        Log.e("Error test","test log en erreur");
//        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        getNeighbour();
        configureFloatingActionButtonBackward();
        configureFavoritesActionButtonStates();
        // viewbinding => findViewById
//        mNeighbourName.setText(mNeighbour.getName());
//        Glide.with(this).load(mNeighbour.getAvatarUrl()).
//                placeholder(R.drawable.ic_account).centerCrop().into(mNeighbourAvatar);
//        mNeighbourNameImage.setText(mNeighbour.getName());
//        mNeighbourAddress.setText(mNeighbour.getAddress());
//        mNeighbourPhoneNumber.setText(mNeighbour.getPhoneNumber());
//        mNeighbourLink.setText(FACEBOOK_LINK + mNeighbour.getName());
//        mNeighbourAboutMe.setText(mNeighbour.getAboutMe());
//        final TextView aboutMeTextView = (TextView) findViewById(R.id.item_profil_user_text);
//        mAboutMeTextView.setText(R.string.activity_neighbour_info_about_me);
//        mNeighbourIsFavorite = mNeighbour.getIsFavorite();
//        mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_border_8_black);
//        mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_border_8_black);
////        mNeighbourFavoriteStates.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_border_8_black));
//        if (mNeighbour != null) {
//            if (!mApiService.getNeighboursFavorite().contains(mNeighbour)) {
//                mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_border_8_black);
////                mNeighbourFavoriteStates.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_border_8_black));
//            } else {
//                mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_8_yellow);
////                mNeighbourFavoriteStates.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_8_yellow));
//            }
//        }
////        mNeighbourFavoriteStates.hide();
////        mNeighbourFavoriteStates.show();
//
        // Gestion des favoris à faire.
        mNeighbourFavoriteStates.setOnClickListener(view -> {
//                Snackbar.make(view, "Here's the favorite states", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                mNeighbourIsFavorite = mNeighbour.getIsFavorite();
//                mApiService.setNeighbourFavorite(mNeighbour);
//                mNeighbourFavoriteStates.setImageDrawable(mFavoriteFalse);
            if (mNeighbour != null) {
                if (!mApiService.getNeighboursFavorite().contains(mNeighbour)) {
                    mApiService.setNeighbourFavorite(mNeighbour);
                    mApiService.addFavoriteInList(mNeighbour);
//                    mApiService. favoriteListNeighbours.add(mNeighbour);
                    mNeighbourFavoriteStates.setImageDrawable(mFavoriteTrue);
//                    mNeighbourFavoriteStates.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_8_yellow));
                } else {
                    mApiService.setNeighbourFavorite(mNeighbour);
                    mApiService.deleteNeighbourFavorites(mNeighbour);
                    mNeighbourFavoriteStates.setImageDrawable(mFavoriteFalse);
//                    mNeighbourFavoriteStates.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_border_8_black));
                }
            }

//                mNeighbourFavoriteStates.hide();
//                mNeighbourFavoriteStates.show();

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

    private void configureFavoritesActionButtonStates() {
        mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_border_8_black);
//        mNeighbourFavoriteStates.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_border_8_black));
        if (mNeighbour != null) {
            if (!mApiService.getNeighboursFavorite().contains(mNeighbour)) {
                mNeighbourFavoriteStates.setImageDrawable(mFavoriteFalse);
//                mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_border_8_black);
//                mNeighbourFavoriteStates.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_border_8_black));
            } else {
                mNeighbourFavoriteStates.setImageDrawable(mFavoriteTrue);
//                mNeighbourFavoriteStates.setImageResource(R.drawable.ic_baseline_star_8_yellow);
//                mNeighbourFavoriteStates.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_8_yellow));
            }
        }
//        mNeighbourFavoriteStates.hide();
//        mNeighbourFavoriteStates.show();


    }


}
