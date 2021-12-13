package com.example.checkout51testapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.checkout51testapp.adapters.OffersAdapter
import com.example.checkout51testapp.databinding.FragmentOffersBinding
import com.example.checkout51testapp.viewmodel.OffersViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment that stores the user's offers. Subscribes to the offers view model to retrieve and show the users retrieved from the c51.JSON file.
 */
@AndroidEntryPoint
class OffersFragment : Fragment() {
    private lateinit var binding: FragmentOffersBinding

    private val viewModel: OffersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOffersBinding.inflate(inflater, container, false)
        val adapter = OffersAdapter()
        binding.offerList.adapter = adapter

        binding.sortOffersDefault.setOnClickListener {
            subscribeUi(adapter)
        }
        binding.sortOffersByNameButton.setOnClickListener {
            updateListWithSortedByNameItems(adapter)
        }
        binding.sortOffersByCashBackButton.setOnClickListener {
            updateListWithSortedByCashbackItems(adapter)
        }

        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: OffersAdapter) {
        context?.let {
            viewModel.getOffers(it).observe(viewLifecycleOwner) { result ->
                adapter.submitList(result)
                binding.sortOffersByNameButton.isEnabled = true
                binding.sortOffersByCashBackButton.isEnabled = true
                binding.sortOffersDefault.isEnabled = true
                binding.offerList.smoothScrollToPosition(0)
            }
        }
    }

    private fun updateListWithSortedByNameItems(adapter: OffersAdapter) {
        viewModel.getOffersSortedByName().observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
            binding.offerList.smoothScrollToPosition(0)
        }
    }

    private fun updateListWithSortedByCashbackItems(adapter: OffersAdapter) {
        viewModel.getOffersSortedByCashback().observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
            binding.offerList.smoothScrollToPosition(0)
        }
    }
}