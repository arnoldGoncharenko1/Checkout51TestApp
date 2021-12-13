package com.example.checkout51testapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.checkout51testapp.utils.testOffer
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.*
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith

/**
 * Test class for testing the offers DAO.
 */
@RunWith(AndroidJUnit4::class)
class OffersDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var offersDao: OffersDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        offersDao = database.offerDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun databaseWith1Item_isDatabaseEmpty_false() = runBlocking {
        offersDao.insertOffer(testOffer)
        val isDatabaseEmpty = offersDao.getCount()
        assertTrue(isDatabaseEmpty == 1)
    }

    @Test
    fun databaseWith0Item_isDatabaseEmpty_true() = runBlocking {
        val isDatabaseEmpty = offersDao.getCount()
        assertTrue(isDatabaseEmpty == 0)
    }

    @Test
    fun insertOffer_insertSuccess() = runBlocking {
        val result = offersDao.insertOffer(testOffer)
        assertTrue(result == 1L)
    }
}