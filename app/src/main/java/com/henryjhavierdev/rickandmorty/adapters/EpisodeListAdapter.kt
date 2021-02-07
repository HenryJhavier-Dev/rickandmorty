package com.henryjhavierdev.rickandmorty.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.domain.Episode
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.databinding.ItemListEpisodeBinding
import com.henryjhavierdev.rickandmorty.utils.bindingInflate


class EpisodeListAdapter(
    private val listener: (Episode) -> Unit
): RecyclerView.Adapter<EpisodeListAdapter.EpisodeListViewHolder>() {

    private val episodeList: MutableList<Episode> = mutableListOf()

    fun updateData(newData: List<Episode>) {
        episodeList.clear()
        episodeList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EpisodeListViewHolder(
            parent.bindingInflate(R.layout.item_list_episode, false),
            listener
        )

    override fun getItemCount() = episodeList.size

    override fun onBindViewHolder(holder: EpisodeListViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }

    class EpisodeListViewHolder(
        private val dataBinding: ItemListEpisodeBinding,
        private val listener: (Episode) -> Unit
    ): RecyclerView.ViewHolder(dataBinding.root) {

        //region Public Methods
        fun bind(item: Episode){
            dataBinding.episode = item
            itemView.setOnClickListener { listener(item) }
        }

    }
}
