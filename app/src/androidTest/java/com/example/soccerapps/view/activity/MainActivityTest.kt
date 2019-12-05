package com.example.soccerapps.view.activity


import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.soccerapps.R
import com.example.soccerapps.view.util.EspressoIdlingResource
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    //Add Team
    @Test
    fun testAddTeam() {
        onView(withId(R.id.main_container)).check(matches(isDisplayed()))
        onView(withId(R.id.homes)).perform(click())
        onView(withId(R.id.league_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.league_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                4
            )
        )
        onView(withId(R.id.league_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                4,
                click()
            )
        )
        onView(withId(R.id.detail_list)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_list)).perform(click())
        onView(withId(R.id.sub_bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.team)).perform(click())
        onView(withId(R.id.team_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.team_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )
        onView(withId(R.id.team_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                5,
                click()
            )
        )
        onView(withId(R.id.add_favorite_team)).check(matches(isDisplayed()))
        onView(withId(R.id.add_favorite_team)).perform(click())
        onView(withText(R.string.toast_add)).inRoot(withDecorView(not(activityRule.activity.window.decorView)))
            .check(
                matches(isDisplayed())
            )
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBack()

        //Remove
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.viewpager_favorite)).perform(swipeLeft())
        onView(withId(R.id.viewpager_favorite)).perform(swipeLeft())
        onView(withId(R.id.team_recycler_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.team_recycler_favorite)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
        onView(withId(R.id.team_recycler_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.add_favorite_team)).check(matches(isDisplayed()))
        onView(withId(R.id.add_favorite_team)).perform(click())
        onView(withText(R.string.toast_remove)).inRoot(withDecorView(not(activityRule.activity.window.decorView)))
            .check(
                matches(isDisplayed())
            )
    }

    //Search team
    @Test
    fun testSearchTeam() {
        onView(withId(R.id.main_container)).check(matches(isDisplayed()))
        onView(withId(R.id.homes)).perform(click())
        onView(withId(R.id.league_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.league_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                3
            )
        )
        onView(withId(R.id.league_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        onView(withId(R.id.detail_list)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_list)).perform(click())
        onView(withId(R.id.sub_bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.team)).perform(click())
        onView(withId(R.id.search_team)).check(matches(isDisplayed()))
        onView(withId(R.id.search_team)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Napoli"),
            pressImeActionButton()
        )
        onView(withId(R.id.recycler_search_team)).check(matches(isDisplayed()))
        onView(withId(R.id.search_team_list)).check(matches(isDisplayed()))
        onView(withId(R.id.search_team_list)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Genoa"),
            pressImeActionButton()
        )
        pressBack()
    }

    //Add event by team
    @Test
    fun testAddLastEvent() {
        onView(withId(R.id.main_container)).check(matches(isDisplayed()))
        onView(withId(R.id.homes)).perform(click())
        onView(withId(R.id.league_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.league_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                4
            )
        )
        onView(withId(R.id.league_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                4,
                click()
            )
        )
        onView(withId(R.id.detail_list)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_list)).perform(click())
        onView(withId(R.id.sub_bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.standing_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.standing_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                4
            )
        )
        onView(withId(R.id.standing_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                4,
                click()
            )
        )
        onView(withId(R.id.last_team_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.last_team_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                2
            )
        )
        onView(withId(R.id.last_team_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.add_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_favorite)).perform(click())
        onView(withText(R.string.toast_add)).inRoot(withDecorView(not(activityRule.activity.window.decorView)))
            .check(
                matches(isDisplayed())
            )
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBack()

        //Remove
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.last_recycler_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.last_recycler_favorite)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
        onView(withId(R.id.last_recycler_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.add_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_favorite)).perform(click())
        onView(withText(R.string.toast_remove)).inRoot(withDecorView(not(activityRule.activity.window.decorView)))
            .check(
                matches(isDisplayed())
            )
    }

    //Search event
    @Test
    fun testSearchEvent() {
        onView(withId(R.id.main_container)).check(matches(isDisplayed()))
        onView(withId(R.id.homes)).perform(click())
        onView(withId(R.id.league_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.league_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                3
            )
        )
        onView(withId(R.id.league_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        onView(withId(R.id.detail_list)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_list)).perform(click())
        onView(withId(R.id.sub_bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.event)).perform(click())
        onView(withId(R.id.search)).check(matches(isDisplayed()))
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Everton"),
            pressImeActionButton()
        )
        onView(withId(R.id.recycler_search)).check(matches(isDisplayed()))
        onView(withId(R.id.search_list)).check(matches(isDisplayed()))
        onView(withId(R.id.search_list)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Genoa"),
            pressImeActionButton()
        )
        pressBack()
    }

    //Add Event by League
    @Test
    fun testAddNextEvent() {
        onView(withId(R.id.main_container)).check(matches(isDisplayed()))
        onView(withId(R.id.homes)).perform(click())
        onView(withId(R.id.league_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.league_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                4
            )
        )
        onView(withId(R.id.league_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                4,
                click()
            )
        )
        onView(withId(R.id.detail_list)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_list)).perform(click())
        onView(withId(R.id.sub_bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.event)).perform(click())
        onView(withId(R.id.viewpager_event)).perform(swipeLeft())
        onView(withId(R.id.next_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                2
            )
        )
        onView(withId(R.id.next_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.add_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_favorite)).perform(click())
        onView(withText(R.string.toast_add)).inRoot(withDecorView(not(activityRule.activity.window.decorView)))
            .check(
                matches(isDisplayed())
            )
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBack()

        //Remove
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.viewpager_favorite)).perform(swipeLeft())
        onView(withId(R.id.next_recycler_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.next_recycler_favorite)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
        onView(withId(R.id.next_recycler_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.add_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_favorite)).perform(click())
        onView(withText(R.string.toast_remove)).inRoot(withDecorView(not(activityRule.activity.window.decorView)))
            .check(
                matches(isDisplayed())
            )
    }


    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}