package com.example.checkout51testapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.beust.klaxon.Klaxon
import com.example.checkout51testapp.data.OffersDatabaseEntry
import com.example.checkout51testapp.data.OffersModel
import com.example.checkout51testapp.data.OffersRepository
import com.example.checkout51testapp.util.OFFERS_JSON_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model that acts as an in between any view and the offers repository.
 */
@HiltViewModel
class OffersViewModel @Inject constructor(private val repository: OffersRepository) : ViewModel() {
    fun getOffers(context: Context) : LiveData<List<OffersDatabaseEntry>> {
        var databaseEmpty = false
        viewModelScope.launch {
            databaseEmpty = repository.isDatabaseEmpty()
        }

        if (databaseEmpty) {
            val extractedOffers = Klaxon().parse<OffersModel>(context.assets.open(OFFERS_JSON_NAME))

            viewModelScope.launch {
                repository.initializeOffers(extractedOffers)
            }
        }

        return repository.getOffers().asLiveData()
    }

    fun getOffersSortedByName() : LiveData<List<OffersDatabaseEntry>> = repository.getOffersSortedByName().asLiveData()

    fun getOffersSortedByCashback() : LiveData<List<OffersDatabaseEntry>> = repository.getOffersSortedByCashback().asLiveData()
}