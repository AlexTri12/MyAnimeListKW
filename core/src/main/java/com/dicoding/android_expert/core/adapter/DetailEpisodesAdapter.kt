package com.dicoding.android_expert.core.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.android_expert.core.R
import com.dicoding.android_expert.core.databinding.ItemsEpisodesBinding

class DetailEpisodesAdapter
    : RecyclerView.Adapter<DetailEpisodesAdapter.EpisodesViewHolder>() {

    private var numberEpisodes = 0
    private var link = ""

    fun setDataForRecyclerView(numberEpisodes: Int, link: String) {
        this.numberEpisodes = numberEpisodes
        this.link = link

        notifyDataSetChanged()
    }

    inner class EpisodesViewHolder(private val binding: ItemsEpisodesBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Int) {
            val trueEpisode = episode + 1

            with(binding) {
                btnWatchTvShowEpisode.text = itemView.resources.getString(R.string.text_episode, trueEpisode.toString())
                btnWatchTvShowEpisode.setOnClickListener {
                    val intentWeb = Intent(Intent.ACTION_VIEW)
                    intentWeb.data = Uri.parse("$link-episode-$trueEpisode")
                    itemView.context.startActivity(intentWeb)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val binding = ItemsEpisodesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EpisodesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return numberEpisodes
    }
}