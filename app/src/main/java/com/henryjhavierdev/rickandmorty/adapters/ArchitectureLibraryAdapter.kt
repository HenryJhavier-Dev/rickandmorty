package com.henryjhavierdev.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.databinding.ViewGridArchitectureItemBinding

class ArchitectureLibraryAdapter(private val dataSet:  ArrayList<String>)
    : RecyclerView.Adapter<ArchitectureLibraryAdapter.ViewHolder>() {

    private val architectureLibraryList: MutableList<String> = mutableListOf()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_grid_architecture_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ViewGridArchitectureItemBinding.bind(view)

        fun bind ( mediaItem : String){
            with(binding){

                tvAboutArchitecture.text = mediaItem
            }
        }
    }


}