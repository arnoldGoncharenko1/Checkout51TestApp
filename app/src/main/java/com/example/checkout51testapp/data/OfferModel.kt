package com.example.checkout51testapp.data

import com.beust.klaxon.Json

/**
 * Individual offer model to represent how the offer would look on the JSON file. Used in combination with [OffersModel] to parse
 * all the offers currently available.
 */
data class OfferModel(@Json(name = "offer_id") val offerId: String,
                      @Json(name = "name") val offerName: String,
                      @Json(name = "image_url") val offerImageUrl: String,
                      @Json(name = "cash_back") val offerCashBack: Double)