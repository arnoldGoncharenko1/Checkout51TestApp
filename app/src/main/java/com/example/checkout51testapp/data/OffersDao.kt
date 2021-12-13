package com.example.checkout51testapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Offers data access object that assets the user with querying the in-build Room database that stores all the offers the user might have.
 */
@Dao
interface OffersDao {
    @Query("SELECT * FROM OffersTable")
    fun getOffers(): Flow<List<OffersDatabaseEntry>>

    @Query("SELECT * FROM OffersTable ORDER BY offerName")
    fun getOffersSortedByName(): Flow<List<OffersDatabaseEntry>>

    @Query("SELECT * FROM OffersTable ORDER BY offerCashback")
    fun getOffersSortedByCashBack(): Flow<List<OffersDatabaseEntry>>

    @Query("SELECT COUNT(*) FROM OffersTable")
    fun getCount(): Int

    @Insert
    suspend fun insertOffer(offer: OffersDatabaseEntry): Long
}