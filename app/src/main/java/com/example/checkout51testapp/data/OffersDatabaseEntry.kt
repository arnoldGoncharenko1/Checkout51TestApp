package com.example.checkout51testapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * DB entry for a model, used in combination with the in-build Room DB to store all local offers.
 */
@Entity(
    tableName = "OffersTable",
    indices = [Index("batchId")]
)
class OffersDatabaseEntry(
    @ColumnInfo(name = "batchId") val batchId: Int = 0,
    @ColumnInfo(name = "offerId") val offerId: String = "",
    @ColumnInfo(name = "offerName") val offerName: String = "",
    @ColumnInfo(name = "offerImageUrl") val offerImageUrl: String = "",
    @ColumnInfo(name = "offerCashback") val offerCashback: Double = 0.0)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    override fun toString() = offerName
}
