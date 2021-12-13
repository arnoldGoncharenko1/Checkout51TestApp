package com.example.checkout51testapp.data

import com.beust.klaxon.Json
import java.util.ArrayList

/**
 * offers model that represents how the offers would look like on the JSON file. Used on the [OffersRepository] to parse the JSON file for offers.
 * Contains the smaller [OfferModel].
 */
data class OffersModel(@Json(name = "batch_id") val batchId: Int,
                       @Json(name = "offers") val offersList: ArrayList<OfferModel>)