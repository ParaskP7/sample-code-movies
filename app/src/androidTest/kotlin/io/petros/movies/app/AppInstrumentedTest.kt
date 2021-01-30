package io.petros.movies.app

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.core.app.launchActivity
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.petros.movies.test.utils.MOCK_WEB_SERVER_PORT
import io.petros.movies.test.utils.mockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber
import io.petros.movies.feature.movie.details.R as mdR
import io.petros.movies.feature.movies.R as mR
import io.petros.movies.lib.picker.R as pR

@LargeTest
@Suppress("ForbiddenComment")
@RunWith(AndroidJUnit4::class)
class AppInstrumentedTest {

    companion object {

        private const val THREAD_SLEEP_MILLIS = 1000L

        // MOCK RESPONSES

        private const val MOVIES_DIR = "responses/movies"

        private const val NO_FILTER_MOVIES_PAGE_1_FILE = "$MOVIES_DIR/no_filter_movies_page_1.json"
        private const val NO_FILTER_MOVIES_PAGE_2_FILE = "$MOVIES_DIR/no_filter_movies_page_2.json"
        private const val NO_FILTER_MOVIES_PAGE_3_FILE = "$MOVIES_DIR/no_filter_movies_page_3.json"
        private const val NO_FILTER_MOVIES_PAGE_4_FILE = "$MOVIES_DIR/no_filter_movies_page_4.json"
        private const val YEAR_FILTER_MOVIES_PAGE_1_FILE = "$MOVIES_DIR/year_filter_movies_page_1.json"
        private const val YEAR_FILTER_MOVIES_PAGE_2_FILE = "$MOVIES_DIR/year_filter_movies_page_2.json"
        private const val YEAR_FILTER_MOVIES_PAGE_3_FILE = "$MOVIES_DIR/year_filter_movies_page_3.json"
        private const val YEAR_FILTER_MOVIES_PAGE_4_FILE = "$MOVIES_DIR/year_filter_movies_page_4.json"
        private const val YEAR_AND_MONTH_FILTER_MOVIES_PAGE_1_FILE =
            "$MOVIES_DIR/year_and_month_filter_movies_page_1.json"
        private const val YEAR_AND_MONTH_FILTER_MOVIES_PAGE_2_FILE =
            "$MOVIES_DIR/year_and_month_filter_movies_page_2.json"
        private const val YEAR_AND_MONTH_FILTER_MOVIES_PAGE_3_FILE =
            "$MOVIES_DIR/year_and_month_filter_movies_page_3.json"
        private const val YEAR_AND_MONTH_FILTER_MOVIES_PAGE_4_FILE =
            "$MOVIES_DIR/year_and_month_filter_movies_page_4.json"

        // TOOLBAR

        private const val MOVIES_TOOLBAR_FILTER_YEAR = "Year"

        // TODO: Revert to '2020' by specifically selecting this year during testing
        @Suppress("unused") private const val MOVIES_TOOLBAR_FILTER_YEAR_2020 = "2020"
        private const val MOVIES_TOOLBAR_FILTER_YEAR_2021 = "2021"
        private const val MOVIES_TOOLBAR_FILTER_MONTH = "Month"

        // TODO: Revert to 'April' by specifically selecting this month during testing
        @Suppress("unused") private const val MOVIES_TOOLBAR_FILTER_MONTH_APRIL = "April"
        private const val MOVIES_TOOLBAR_FILTER_MONTH_JANUARY = "January"

        // PICKER

        private const val MOVIE_YEAR_PICKER_TITLE = "Select Movie Year"

        // TODO: Revert to '2020' by specifically selecting this year during testing
        @Suppress("unused") private const val MOVIE_YEAR_PICKER_YEAR_2020 = "2020"
        private const val MOVIE_YEAR_PICKER_YEAR_2021 = "2021"
        private const val MOVIE_MONTH_PICKER_TITLE = "Select Movie Month"

        // TODO: Revert to 'Apr' by specifically selecting the month during testing
        @Suppress("unused") private const val MOVIE_MONTH_PICKER_MONTH_APR = "Apr"
        private const val MOVIE_MONTH_PICKER_MONTH_JANUARY = "Jan"
        private const val MOVIE_PICKER_ACTION_OK = "OK"

        // NO FILTER

        private const val SONIC_THE_HEDGEHOG_ITEM_POSITION = 1
        private const val SONIC_THE_HEDGEHOG_ITEM_TITLE = "Sonic the Hedgehog"
        private const val SONIC_THE_HEDGEHOG_ITEM_RELEASE_DATE = "2020 (February)"
        private const val SONIC_THE_HEDGEHOG_ITEM_VOTE = "7.6 ★ (3013)"
        private const val SONIC_THE_HEDGEHOG_ITEM_OVERVIEW =
            "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the " +
                    "world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure " +
                    "comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. " +
                    "Robotnik and his plans for world domination."

        private const val MORTAL_KOMBAT_LEGENDS_ITEM_POSITION = 10
        private const val MORTAL_KOMBAT_LEGENDS_ITEM_TITLE = "Mortal Kombat Legends: Scorpion’s Revenge"
        private const val MORTAL_KOMBAT_LEGENDS_ITEM_RELEASE_DATE = "2020 (April)"
        private const val MORTAL_KOMBAT_LEGENDS_ITEM_VOTE = "8.5 ★ (192)"
        private const val MORTAL_KOMBAT_LEGENDS_ITEM_OVERVIEW =
            "After the vicious slaughter of his family by stone-cold mercenary Sub-Zero, Hanzo Hasashi is exiled to " +
                    "the torturous Netherrealm. There, in exchange for his servitude to the sinister Quan Chi, he’s " +
                    "given a chance to avenge his family – and is resurrected as Scorpion, a lost soul bent on revenge. " +
                    "Back on Earthrealm, Lord Raiden gathers a team of elite warriors – Shaolin monk Liu Kang, Special " +
                    "Forces officer Sonya Blade and action star Johnny Cage – an unlikely band of heroes with one chance " +
                    "to save humanity. To do this, they must defeat Shang Tsung’s horde of Outworld gladiators and reign " +
                    "over the Mortal Kombat tournament."

        // YEAR FILTER

        private const val UNDERWATER_ITEM_POSITION = 1
        private const val UNDERWATER_ITEM_TITLE = "Underwater"
        private const val UNDERWATER_ITEM_RELEASE_DATE = "2020 (January)"
        private const val UNDERWATER_ITEM_VOTE = "6.4 ★ (864)"
        private const val UNDERWATER_ITEM_OVERVIEW =
            "After an earthquake destroys their underwater station, six researchers must navigate two miles along the " +
                    "dangerous, unknown depths of the ocean floor to make it to safety in a race against time."

        private const val TROLLS_WORLD_TOUR_ITEM_POSITION = 10
        private const val TROLLS_WORLD_TOUR_ITEM_TITLE = "Trolls World Tour"
        private const val TROLLS_WORLD_TOUR_ITEM_RELEASE_DATE = "2020 (March)"
        private const val TROLLS_WORLD_TOUR_ITEM_VOTE = "7.7 ★ (397)"
        private const val TROLLS_WORLD_TOUR_ITEM_OVERVIEW =
            "Queen Poppy and Branch make a surprising discovery — there are other Troll worlds beyond their own, and " +
                    "their distinct differences create big clashes between these various tribes. When a mysterious " +
                    "threat puts all of the Trolls across the land in danger, Poppy, Branch, and their band of friends " +
                    "must embark on an epic quest to create harmony among the feuding Trolls to unite them against " +
                    "certain doom."

        // MONTH FILTER

        private const val LOVE_WEDDING_REPEAT_ITEM_POSITION = 1
        private const val LOVE_WEDDING_REPEAT_ITEM_TITLE = "Love Wedding Repeat"
        private const val LOVE_WEDDING_REPEAT_ITEM_RELEASE_DATE = "2020 (April)"
        private const val LOVE_WEDDING_REPEAT_ITEM_VOTE = "5.8 ★ (176)"
        private const val LOVE_WEDDING_REPEAT_ITEM_OVERVIEW = "While trying to make his sister's wedding day go " +
                "smoothly, Jack finds himself juggling an angry ex-girlfriend, an uninvited guest with a secret, a " +
                "misplaced sleep sedative, and the girl that got away in alternate versions of the same day."

        private const val BEHIND_YOU_ITEM_POSITION = 10
        private const val BEHIND_YOU_ITEM_TITLE = "Behind You"
        private const val BEHIND_YOU_ITEM_RELEASE_DATE = "2020 (April)"
        private const val BEHIND_YOU_ITEM_VOTE = "6.0 ★ (8)"
        private const val BEHIND_YOU_ITEM_OVERVIEW = "Two young sisters find that all the mirrors in their estranged " +
                "aunt's house are covered or hidden. When one of them happens upon a mirror in the basement, she " +
                "unknowingly releases a malicious demon."

    }

    @Suppress("LateinitUsage") private lateinit var scenario: ActivityScenario<AppActivity>

    private val server = MockWebServer()

    @Before
    fun setUp() {
        server.start(MOCK_WEB_SERVER_PORT)
        enqueueMockResponses()
        scenario = launchActivity(Intent(getApplicationContext(), AppActivity::class.java))
    }

    @After
    fun cleanUp() {
        if (this::scenario.isInitialized) {
            scenario.close()
        }
    }

    private fun enqueueMockResponses() {
        server.enqueue(mockResponse(NO_FILTER_MOVIES_PAGE_1_FILE))
        server.enqueue(mockResponse(NO_FILTER_MOVIES_PAGE_2_FILE))
        server.enqueue(mockResponse(NO_FILTER_MOVIES_PAGE_3_FILE))
        server.enqueue(mockResponse(NO_FILTER_MOVIES_PAGE_4_FILE))
        server.enqueue(mockResponse(YEAR_FILTER_MOVIES_PAGE_1_FILE))
        server.enqueue(mockResponse(YEAR_FILTER_MOVIES_PAGE_2_FILE))
        server.enqueue(mockResponse(YEAR_FILTER_MOVIES_PAGE_3_FILE))
        server.enqueue(mockResponse(YEAR_FILTER_MOVIES_PAGE_4_FILE))
        server.enqueue(mockResponse(YEAR_AND_MONTH_FILTER_MOVIES_PAGE_1_FILE))
        server.enqueue(mockResponse(YEAR_AND_MONTH_FILTER_MOVIES_PAGE_2_FILE))
        server.enqueue(mockResponse(YEAR_AND_MONTH_FILTER_MOVIES_PAGE_3_FILE))
        server.enqueue(mockResponse(YEAR_AND_MONTH_FILTER_MOVIES_PAGE_4_FILE))
        server.enqueue(mockResponse(NO_FILTER_MOVIES_PAGE_1_FILE))
        server.enqueue(mockResponse(NO_FILTER_MOVIES_PAGE_2_FILE))
        server.enqueue(mockResponse(NO_FILTER_MOVIES_PAGE_3_FILE))
        server.enqueue(mockResponse(NO_FILTER_MOVIES_PAGE_4_FILE))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun app_e2e_test() {
        check_movies_no_filter()
        check_movies_year_picker()
        check_movies_year_filter()
        check_movies_month_picker()
        check_movies_month_filter()
        check_movies_close_icon()
        check_movies_no_filter()
    }

    private fun check_movies_no_filter() {
        Thread.sleep(THREAD_SLEEP_MILLIS)
        check_sonic_the_hedgehog_movie()
        check_mortal_kombat_legends_movie()
        check_sonic_the_hedgehog_movie()
    }

    private fun check_movies_year_picker() {
        check_filter_icon()
        check_year_filter()
        check_year_picker()
    }

    private fun check_movies_year_filter() {
        Thread.sleep(THREAD_SLEEP_MILLIS)
        check_underwater_movie()
        check_trolls_world_tour_movie()
        check_underwater_movie()
    }

    private fun check_movies_month_picker() {
        check_month_filter()
        check_month_picker()
    }

    private fun check_movies_month_filter() {
        Thread.sleep(THREAD_SLEEP_MILLIS)
        check_love_wedding_repeat_movie()
        check_behind_you_movie()
        check_love_wedding_repeat_movie()
    }

    /* TEST CASES */

    private fun check_sonic_the_hedgehog_movie() {
        checkViewMoviesToolbar()
        checkViewMovies(
            position = SONIC_THE_HEDGEHOG_ITEM_POSITION,
            title = SONIC_THE_HEDGEHOG_ITEM_TITLE,
            releaseDate = SONIC_THE_HEDGEHOG_ITEM_RELEASE_DATE,
            vote = SONIC_THE_HEDGEHOG_ITEM_VOTE,
        )
        checkViewMovieDetails(
            title = SONIC_THE_HEDGEHOG_ITEM_TITLE,
            releaseDate = SONIC_THE_HEDGEHOG_ITEM_RELEASE_DATE,
            vote = SONIC_THE_HEDGEHOG_ITEM_VOTE,
            overview = SONIC_THE_HEDGEHOG_ITEM_OVERVIEW,
        )
        pressBack()
    }

    private fun check_mortal_kombat_legends_movie() {
        checkViewMoviesToolbar()
        checkViewMovies(
            position = MORTAL_KOMBAT_LEGENDS_ITEM_POSITION,
            title = MORTAL_KOMBAT_LEGENDS_ITEM_TITLE,
            releaseDate = MORTAL_KOMBAT_LEGENDS_ITEM_RELEASE_DATE,
            vote = MORTAL_KOMBAT_LEGENDS_ITEM_VOTE,
        )
        checkViewMovieDetails(
            title = MORTAL_KOMBAT_LEGENDS_ITEM_TITLE,
            releaseDate = MORTAL_KOMBAT_LEGENDS_ITEM_RELEASE_DATE,
            vote = MORTAL_KOMBAT_LEGENDS_ITEM_VOTE,
            overview = MORTAL_KOMBAT_LEGENDS_ITEM_OVERVIEW,
        )
        pressBack()
    }

    private fun check_filter_icon() {
        checkViewMoviesToolbar(
            withFilterIconClick = true,
        )
    }

    private fun check_year_filter() {
        checkViewMoviesToolbarYear(
            withFilterYearClick = true,
        )
    }

    private fun check_year_picker() {
        checkViewMoviesPicker(
            title = MOVIE_YEAR_PICKER_TITLE,
            year = MOVIE_YEAR_PICKER_YEAR_2021,
            withActionOkClick = true,
        )
    }

    private fun check_underwater_movie() {
        checkViewMoviesToolbarYear2020()
        checkViewMovies(
            position = UNDERWATER_ITEM_POSITION,
            title = UNDERWATER_ITEM_TITLE,
            releaseDate = UNDERWATER_ITEM_RELEASE_DATE,
            vote = UNDERWATER_ITEM_VOTE,
        )
        checkViewMovieDetails(
            title = UNDERWATER_ITEM_TITLE,
            releaseDate = UNDERWATER_ITEM_RELEASE_DATE,
            vote = UNDERWATER_ITEM_VOTE,
            overview = UNDERWATER_ITEM_OVERVIEW,
        )
        pressBack()
    }

    private fun check_trolls_world_tour_movie() {
        checkViewMoviesToolbarYear2020()
        checkViewMovies(
            position = TROLLS_WORLD_TOUR_ITEM_POSITION,
            title = TROLLS_WORLD_TOUR_ITEM_TITLE,
            releaseDate = TROLLS_WORLD_TOUR_ITEM_RELEASE_DATE,
            vote = TROLLS_WORLD_TOUR_ITEM_VOTE,
        )
        checkViewMovieDetails(
            title = TROLLS_WORLD_TOUR_ITEM_TITLE,
            releaseDate = TROLLS_WORLD_TOUR_ITEM_RELEASE_DATE,
            vote = TROLLS_WORLD_TOUR_ITEM_VOTE,
            overview = TROLLS_WORLD_TOUR_ITEM_OVERVIEW,
        )
        pressBack()
    }

    private fun check_month_filter() {
        checkViewMoviesToolbarYear2020(
            withFilterMonthClick = true,
        )
    }

    private fun check_month_picker() {
        checkViewMoviesPicker(
            title = MOVIE_MONTH_PICKER_TITLE,
            month = MOVIE_MONTH_PICKER_MONTH_JANUARY,
            withActionOkClick = true,
        )
    }

    private fun check_love_wedding_repeat_movie() {
        checkViewMoviesToolbarYear2020MonthApril()
        checkViewMovies(
            position = LOVE_WEDDING_REPEAT_ITEM_POSITION,
            title = LOVE_WEDDING_REPEAT_ITEM_TITLE,
            releaseDate = LOVE_WEDDING_REPEAT_ITEM_RELEASE_DATE,
            vote = LOVE_WEDDING_REPEAT_ITEM_VOTE,
        )
        checkViewMovieDetails(
            title = LOVE_WEDDING_REPEAT_ITEM_TITLE,
            releaseDate = LOVE_WEDDING_REPEAT_ITEM_RELEASE_DATE,
            vote = LOVE_WEDDING_REPEAT_ITEM_VOTE,
            overview = LOVE_WEDDING_REPEAT_ITEM_OVERVIEW,
        )
        pressBack()
    }

    private fun check_behind_you_movie() {
        checkViewMoviesToolbarYear2020MonthApril()
        checkViewMovies(
            position = BEHIND_YOU_ITEM_POSITION,
            title = BEHIND_YOU_ITEM_TITLE,
            releaseDate = BEHIND_YOU_ITEM_RELEASE_DATE,
            vote = BEHIND_YOU_ITEM_VOTE,
        )
        checkViewMovieDetails(
            title = BEHIND_YOU_ITEM_TITLE,
            releaseDate = BEHIND_YOU_ITEM_RELEASE_DATE,
            vote = BEHIND_YOU_ITEM_VOTE,
            overview = BEHIND_YOU_ITEM_OVERVIEW,
        )
        pressBack()
    }

    private fun check_movies_close_icon() {
        checkViewMoviesToolbarYear2020MonthApril(
            withCloseIconClick = true,
        )
    }

    /* EXTRA HELPER FUNCTIONS */

    @Suppress("SameParameterValue")
    private fun checkViewMoviesToolbarYear(
        withFilterYearClick: Boolean = false,
    ) {
        checkViewMoviesToolbar(
            filterIconDisplayed = false,
            closeIconDisplayed = true,
            filterYearDisplayed = true,
            withFilterYearClick = withFilterYearClick,
        )
    }

    private fun checkViewMoviesToolbarYear2020(
        withFilterMonthClick: Boolean = false,
    ) {
        checkViewMoviesToolbar(
            filterIconDisplayed = false,
            closeIconDisplayed = true,
            filterYearDisplayed = true,
            filterYear = MOVIES_TOOLBAR_FILTER_YEAR_2021,
            filterMonthDisplayed = true,
            withFilterMonthClick = withFilterMonthClick,
        )
    }

    private fun checkViewMoviesToolbarYear2020MonthApril(
        withCloseIconClick: Boolean = false,
    ) {
        checkViewMoviesToolbar(
            filterIconDisplayed = false,
            closeIconDisplayed = true,
            withCloseIconClick = withCloseIconClick,
            filterYearDisplayed = true,
            filterYear = MOVIES_TOOLBAR_FILTER_YEAR_2021,
            filterMonthDisplayed = true,
            filterMonth = MOVIES_TOOLBAR_FILTER_MONTH_JANUARY,
        )
    }

    /* HELPER FUNCTIONS */

    @Suppress("LongParameterList")
    private fun checkViewMoviesToolbar(
        filterIconDisplayed: Boolean = true,
        withFilterIconClick: Boolean = false,
        closeIconDisplayed: Boolean = false,
        withCloseIconClick: Boolean = false,
        filterYearDisplayed: Boolean = false,
        filterYear: String = MOVIES_TOOLBAR_FILTER_YEAR,
        withFilterYearClick: Boolean = false,
        filterMonthDisplayed: Boolean = false,
        filterMonth: String = MOVIES_TOOLBAR_FILTER_MONTH,
        withFilterMonthClick: Boolean = false,
    ) {
        if (filterIconDisplayed) {
            checkViewMoviesToolbarWithFilterIcon(
                withFilterIconClick,
            )
        }
        if (closeIconDisplayed) {
            checkViewMoviesToolbarWithCloseIcon(
                filterYearDisplayed,
                filterYear,
                filterMonthDisplayed,
                filterMonth,
                withCloseIconClick,
                withFilterYearClick,
                withFilterMonthClick,
            )
        }
    }

    private fun checkViewMoviesToolbarWithFilterIcon(
        withFilterIconClick: Boolean,
    ) {
        onView(withId(mR.id.ivToolbarFilterIcon))
            .check(matches(isDisplayed()))
        onView(withId(mR.id.ivToolbarCloseIcon))
            .check(matches(not(isDisplayed())))
        checkViewMoviesToolbarWithFilterIconDisplays()
        checkViewMoviesToolbarWithFilterIconClicks(
            withFilterIconClick,
        )
    }

    private fun checkViewMoviesToolbarWithFilterIconDisplays() {
        onView(withId(mR.id.tvToolbarFilterYear))
            .check(matches(not(isDisplayed())))
        onView(withId(mR.id.tvToolbarFilterMonth))
            .check(matches(not(isDisplayed())))
    }

    private fun checkViewMoviesToolbarWithFilterIconClicks(
        withFilterIconClick: Boolean,
    ) {
        if (withFilterIconClick) {
            onView(withId(mR.id.ivToolbarFilterIcon))
                .perform(click())
        }
    }

    @Suppress("LongParameterList")
    private fun checkViewMoviesToolbarWithCloseIcon(
        filterYearDisplayed: Boolean,
        filterYear: String,
        filterMonthDisplayed: Boolean,
        filterMonth: String,
        withCloseIconClick: Boolean,
        withFilterYearClick: Boolean,
        withFilterMonthClick: Boolean,
    ) {
        onView(withId(mR.id.ivToolbarFilterIcon))
            .check(matches(not(isDisplayed())))
        onView(withId(mR.id.ivToolbarCloseIcon))
            .check(matches(isDisplayed()))
        checkViewMoviesToolbarWithCloseIconDisplays(
            filterYearDisplayed,
            filterYear,
            filterMonthDisplayed,
            filterMonth,
        )
        checkViewMoviesToolbarWithCloseIconClicks(
            withCloseIconClick,
            withFilterYearClick,
            filterYear,
            withFilterMonthClick,
            filterMonth,
        )
    }

    private fun checkViewMoviesToolbarWithCloseIconDisplays(
        filterYearDisplayed: Boolean,
        filterYear: String,
        filterMonthDisplayed: Boolean,
        filterMonth: String,
    ) {
        if (filterYearDisplayed) {
            onView(allOf(withId(mR.id.tvToolbarFilterYear), withText(filterYear)))
                .check(matches(allOf(isDisplayed(), withText(filterYear))))
        } else {
            onView(withId(mR.id.tvToolbarFilterYear))
                .check(matches(not(isDisplayed())))
        }
        if (filterMonthDisplayed) {
            onView(allOf(withId(mR.id.tvToolbarFilterMonth), withText(filterMonth)))
                .check(matches(allOf(isDisplayed(), withText(filterMonth))))
        } else {
            onView(withId(mR.id.tvToolbarFilterMonth))
                .check(matches(not(isDisplayed())))
        }
    }

    private fun checkViewMoviesToolbarWithCloseIconClicks(
        withCloseIconClick: Boolean,
        withFilterYearClick: Boolean,
        filterYear: String,
        withFilterMonthClick: Boolean,
        filterMonth: String,
    ) {
        if (withCloseIconClick) {
            onView(withId(mR.id.ivToolbarCloseIcon))
                .perform(click())
        }
        if (withFilterYearClick) {
            onView(allOf(withId(mR.id.tvToolbarFilterYear), withText(filterYear)))
                .perform(click())
        }
        if (withFilterMonthClick) {
            onView(allOf(withId(mR.id.tvToolbarFilterMonth), withText(filterMonth)))
                .perform(click())
        }
    }

    private fun checkViewMoviesPicker(
        title: String,
        year: String? = null,
        month: String? = null,
        withActionOkClick: Boolean = false,
    ) {
        onView(allOf(withId(pR.id.title), withText(title)))
            .check(matches(allOf(isDisplayed(), withText(title))))
        year?.let {
            onView(allOf(withId(pR.id.year), withText(it)))
                .check(matches(allOf(isDisplayed(), withText(it))))
        }
        month?.let {
            onView(allOf(withId(pR.id.month), withText(it)))
                .check(matches(allOf(isDisplayed(), withText(it))))
        }
        if (withActionOkClick) {
            onView(withText(MOVIE_PICKER_ACTION_OK))
                .check(matches(allOf(isDisplayed(), withText(MOVIE_PICKER_ACTION_OK))))
                .perform(click())
        }
    }

    @Suppress("SwallowedException")
    private fun checkViewMovies(
        position: Int,
        title: String,
        releaseDate: String,
        vote: String,
    ) {
        onView(withId(mR.id.recyclerView))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(position - 1))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(position + 1))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(allOf(withId(mR.id.tvItemTitle), withText(title)))
            .check(matches(allOf(isDisplayed(), withText(title))))
        try {
            onView(allOf(withId(mR.id.tvItemReleaseDate), withText(releaseDate)))
                .check(matches(allOf(isDisplayed(), withText(releaseDate))))
        } catch (avme: AmbiguousViewMatcherException) {
            Timber.w("This release date matches multiple views in the hierarchy. [Release Date: $releaseDate]")
        }
        onView(allOf(withId(mR.id.tvItemVote), withText(vote)))
            .check(matches(allOf(isDisplayed(), withText(vote))))
        onView(withId(mR.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
    }

    private fun checkViewMovieDetails(
        title: String,
        releaseDate: String,
        vote: String,
        overview: String,
    ) {
        onView(withId(mdR.id.tvTitle))
            .check(matches(allOf(isDisplayed(), withText(title))))
        onView(withId(mdR.id.tvReleaseDate))
            .check(matches(allOf(isDisplayed(), withText(releaseDate))))
        onView(withId(mdR.id.tvVote))
            .check(matches(allOf(isDisplayed(), withText(vote))))
        onView(withId(mdR.id.tvOverview))
            .check(matches(allOf(isDisplayed(), withText(overview))))
    }

}
