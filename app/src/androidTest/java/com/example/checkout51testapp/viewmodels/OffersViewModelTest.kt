package com.example.checkout51testapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.checkout51testapp.data.AppDatabase
import com.example.checkout51testapp.data.OffersRepository
import com.example.checkout51testapp.utils.MainCoroutineRule
import com.example.checkout51testapp.viewmodel.OffersViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.*
import org.junit.rules.RuleChain
import javax.inject.Inject

/**
 * Test class for testing the offers view model
 */
@HiltAndroidTest
class OffersViewModelTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: OffersViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Inject
    lateinit var offersRepository: OffersRepository

    @Before
    fun setUp() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        viewModel = OffersViewModel(offersRepository)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }
}