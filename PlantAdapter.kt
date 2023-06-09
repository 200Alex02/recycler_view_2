package com.example.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycler_view.databinding.ItemPlantsBinding

class PlantAdapter(private val onItemClick: (position: Int) -> Unit) :
    ListAdapter<Plant, PlantAdapter.PlantHolder>(DiffCallback()) {

    inner class PlantHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemPlantsBinding.bind(itemView)

        fun bind(plant: Plant, index: Int) = with(binding) {
            namePlant.text = plant.name
            typeOfPlant.text = plant.type
            leafColor.text = plant.color
            Glide.with(itemView.context)
                .load(plant.photo)
                .circleCrop()
                .error(R.drawable.error)
                .placeholder(R.drawable.error)
                .into(imageView)

        }

    }

    /*private fun deleteItem(position: Int) {
        val newList = currentList.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_plants, parent, false)
        return PlantHolder(itemView).apply {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(position)
                }
            }

        }

    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.bind(getItem(position), position)

    }

}

private class DiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.photo == newItem.photo
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }

}
