package com.example.checkout51testapp.data

import kotlinx.coroutines.flow.Flow
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Offers repo that deals with storing, retrieving, and querying the room database. Used in combination with the [OffersViewModel] to grab data
 * for the front end.
 */
@Singleton
class OffersRepository @Inject constructor(private val offersDao: OffersDao)  {
    suspend fun initializeOffers(extractedOffers: OffersModel?) {
        extractedOffers?.offersList?.forEach { offer ->
            offersDao.insertOffer(
                OffersDatabaseEntry(
                    extractedOffers.batchId,
                    offer.offerId,
                    offer.offerName,
                    offer.offerImageUrl,
                    offer.offerCashBack
                )
            )
        }
    }

    fun getOffers() : Flow<List<OffersDatabaseEntry>> = offersDao.getOffers()

    fun getOffersSortedByName() : Flow<List<OffersDatabaseEntry>> = offersDao.getOffersSortedByName()

    fun getOffersSortedByCashback() : Flow<List<OffersDatabaseEntry>> = offersDao.getOffersSortedByCashBack()

    fun isDatabaseEmpty() : Boolean {
        //TODO: Convert this into using coroutines.
        val entriesCount = AtomicInteger()
        val t = Thread {
            val num: Int = offersDao.getCount()
            entriesCount.set(num)
        }
        t.priority = 10
        t.start()
        t.join()

        return entriesCount.get() == 0
    }
}