package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityAndInfoNeighbourActivityTest {

    // Ceci est constant
    private static int ITEMS_COUNT = 12;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
//    public void listNeighbourActivityAndInfoNeighbourActivityTest() throws Exception {
    public void listNeighbourActivityAndInfoNeighbourActivityTest() {
        onView(AllOf.allOf(withId(R.id.list_neighbours), isDisplayed()))
    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.item_add_favorite_user_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        floatingActionButton.perform(scrollTo(), click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.item_backward_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        floatingActionButton2.perform(scrollTo(), click());

        ViewInteraction tabView = onView(
                allOf(withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        // Vérifier si l'ajout d'un des voisins est bien fait dans la liste des voisins favoris 1 pour le nombre de voisin dans la liste.
        // Départ liste = 0 car aucun voisin dans la liste au démarrage de l'application.
        onView(AllOf.allOf(withId(R.id.list_neighbours), isDisplayed())).check(matches(hasChildCount(1)));

        onView(AllOf.allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.item_add_favorite_user_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        floatingActionButton3.perform(scrollTo(), click());

        ViewInteraction floatingActionButton4 = onView(
                allOf(withId(R.id.item_backward_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        floatingActionButton4.perform(scrollTo(), click());

        // Vérifier si l'ajout d'un des voisins est bien fait dans la liste des voisins favoris 0 pour le nombre de voisin dans la liste.
        // Départ liste = 1 car il y avait 1 voisin avant l'annulation de ce favoris.
        onView(AllOf.allOf(withId(R.id.list_neighbours), isDisplayed())).check(matches(hasChildCount(0)));

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("My neighbours"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView2.perform(click());

        onView(AllOf.allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        ViewInteraction floatingActionButton5 = onView(
                allOf(withId(R.id.item_add_favorite_user_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        floatingActionButton5.perform(scrollTo(), click());

        ViewInteraction floatingActionButton6 = onView(
                allOf(withId(R.id.item_backward_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        floatingActionButton6.perform(scrollTo(), click());

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView3.perform(click());

        onView(AllOf.allOf(withId(R.id.list_neighbours), isDisplayed())).check(matches(hasChildCount(1)));

        ViewInteraction tabView4 = onView(
                allOf(withContentDescription("My neighbours"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView4.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.item_list_delete_button),
                        childAtPosition(
                                allOf(withId(R.id.fragment_neighbour_layout),
                                        childAtPosition(
                                                withId(R.id.list_neighbours),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction tabView5 = onView(
                allOf(withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView5.perform(click());

        onView(AllOf.allOf(withId(R.id.list_neighbours), isDisplayed())).check(matches(hasChildCount(0)));

        ViewInteraction tabView6 = onView(
                allOf(withContentDescription("My neighbours"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView6.perform(click());

        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount((ITEMS_COUNT - 1)));


    }
//
//
//    public void deleteNeighbourActivityTest() {
//        onView(ViewMatchers.withId(R.id.list_neighbours)).check(matches(hasChildCount(1)));
//        onView(ViewMatchers.withId(R.id.list_neighbours))
//                .check(matches(hasMinimumChildCount(1)));
//
//    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
