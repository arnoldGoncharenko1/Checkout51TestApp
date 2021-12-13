package com.example.checkout51testapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.checkout51testapp.R
import com.example.checkout51testapp.data.OffersDatabaseEntry
import com.example.checkout51testapp.databinding.OfferViewBinding


class OffersAdapter : ListAdapter<OffersDatabaseEntry, OffersAdapter.OfferViewHolder>(OfferDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.offer_view,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class OfferViewHolder(private val binding: OfferViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OffersDatabaseEntry) {
            with(binding) {
                offer = item
                Glide.with(binding.root)
                    .load(item.offerImageUrl)
                    .into(binding.offerImage)
                executePendingBindings()
            }
        }
    }
}

private class OfferDiffCallback : DiffUtil.ItemCallback<OffersDatabaseEntry>() {

    override fun areItemsTheSame(oldItem: OffersDatabaseEntry, newItem: OffersDatabaseEntry): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OffersDatabaseEntry, newItem: OffersDatabaseEntry): Boolean {
        return oldItem.equals(newItem)
    }
}