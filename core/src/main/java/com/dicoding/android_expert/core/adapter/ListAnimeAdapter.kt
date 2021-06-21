package com.dicoding.android_expert.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.android_expert.core.R
import com.dicoding.android_expert.core.databinding.ItemsAnimeListBinding
import com.dicoding.android_expert.core.domain.model.ListAnime

class ListAnimeAdapter
    : RecyclerView.Adapter<ListAnimeAdapter.ListAnimeViewHolder>() {

    private var animeList = ArrayList<ListAnime>()
    var onItemClick: ((ListAnime) -> Unit)? = null

    fun setAnimeList(data: List<ListAnime>?) {
        if (data == null) return

        animeList.clear()
        animeList.addAll(data)
        notifyDataSetChanged()
    }

    inner class ListAnimeViewHolder(private val binding: ItemsAnimeListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: ListAnime) {
            with(binding) {
                tvRank.text = itemView.resources.getString(R.string.text_rank, anime.rank.toString())
                tvName.text = anime.title
                tvScores.text = itemView.resources.getString(R.string.text_score, anime.score.toString())
                tvReleaseTime.text = itemView.resources.getString(R.string.text_release, anime.startDate)

                if (anime.type == "Movie") {
                    tvEpisodes.visibility = View.GONE
                } else {
                    tvEpisodes.text = itemView.resources.getString(R.string.text_number_episode, anime.episodes.toString())
                }

                Glide.with(itemView.context)
                    .load(anime.imageUrl)
                    .into(imgPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(animeList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAnimeViewHolder {
        val binding = ItemsAnimeListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListAnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAnimeViewHolder, position: Int) {
        holder.bind(animeList[position])
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}