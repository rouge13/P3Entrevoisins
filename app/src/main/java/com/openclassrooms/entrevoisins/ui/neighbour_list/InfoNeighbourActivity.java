package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.openclassrooms.entrevoisins.R;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.OnClick;

/**
 * Created by Julien HAMMER - Apprenti Java with openclassrooms on .
 */
public class InfoNeighbourActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mNeighboursAvatar;
    private ImageButton mNeighboursFavoriteStates;
    private TextView mNeighbourName;
    private TextView mNeighbourAddress;
    private TextView mNeighbourPhoneNumber;
    private TextView mNeighbourLink;
    private static final String BUNDLE_ABOUT_ME = "A propos de moi";
    private TextView mNeighbourAboutMe;
    private boolean mFavoritesAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_info);
        mNeighboursAvatar = findViewById(R.id.item_avatar_user_image);
        mNeighboursFavoriteStates = findViewById(R.id.item_add_favorite_user_button);
        mNeighbourName = findViewById(R.id.item_name_user_with_image_text);
        mNeighbourName = findViewById(R.id.item_name_user_text);
        mNeighbourAddress = findViewById(R.id.item_address_user_text);
        mNeighbourPhoneNumber = findViewById(R.id.item_phone_user_text);
        mNeighbourLink = findViewById(R.id.item_link_user_text);
        mNeighbourAboutMe = findViewById(R.id.item_about_content_user_text);
        final TextView aboutMeTextView = (TextView) findViewById(R.id.item_profil_user_text);
        aboutMeTextView.setText(R.string.activity_neighbour_info_about_me);
        // Permet d'afficher si on a  ajouté au favoris ou pas ce voisin
//        if (mFavoritesAdded = false){
//            final ImageButton addedFavoriteImageButton = (ImageButton) findViewById(R.id.item_add_favorite_user_button);
//            addedFavoriteImageButton.setVectorDrawable(R.drawable.ic_star_border_white_24dp);
//        }

    }

    @Override
    public void onClick(View v) {

    }

//    @OnClick(R.id.item_backward_button)
//    void showNeighbourList() {
//
//    }

}
