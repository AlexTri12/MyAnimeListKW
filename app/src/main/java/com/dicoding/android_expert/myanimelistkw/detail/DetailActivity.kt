package com.dicoding.android_expert.myanimelistkw.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.android_expert.core.adapter.DetailEpisodesAdapter
import com.dicoding.android_expert.core.domain.model.DetailAnime
import com.dicoding.android_expert.core.vo.Resource
import com.dicoding.android_expert.myanimelistkw.R
import com.dicoding.android_expert.myanimelistkw.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private var menu: Menu? = null
    private var anime: DetailAnime? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Anime"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_ID, -1)

            viewModel.setAnimeId(id)
            viewModel.detailAnime?.observe(this, { anime ->
                if (anime != null) {
                    when(anime) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.scrollView.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.scrollView.visibility = View.VISIBLE
                            this.anime = anime.data
                            setUpDetailAnime()
                            isFavorite = anime.data?.isFavorite == true
                            changeMenuFavoriteIcon()
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    private fun setUpDetailAnime() {
        if (anime != null) {
            val detailAnime: DetailAnime = anime as DetailAnime
            with(binding) {
                tvName.text = detailAnime.title
                tvGenres.text = resources.getString(R.string.text_genres, detailAnime.genres)
                if (detailAnime.premiered != null) {
                    tvRelease.text = resources.getString(R.string.text_premiered, detailAnime.premiered)
                } else {
                    tvRelease.visibility = View.GONE
                }
                tvPlotSummary.text = detailAnime.synopsis
                tvScores.text = resources.getString(R.string.text_score, detailAnime.score.toString())

                tvOtherName.visibility = View.GONE
                if (detailAnime.otherTitle != null && !detailAnime.otherTitle.equals("null")) {
                    tvOtherName.text = resources.getString(R.string.text_other_name, detailAnime.otherTitle)
                    tvOtherName.visibility = View.VISIBLE
                }

                Glide.with(this@DetailActivity)
                    .load(detailAnime.imageUrl)
                    .apply(RequestOptions.overrideOf(160, 200))
                    .into(imgPoster)

                tvStatus.visibility = View.VISIBLE

                if (detailAnime.type == "TV") {
                    tvEpisodes.visibility = View.VISIBLE
                    rvEpisodes.visibility = View.VISIBLE
                    btnWatchMovie.visibility = View.GONE

                    tvStatus.text = resources.getString(R.string.text_status, detailAnime.status)

                    val adapter = DetailEpisodesAdapter()
                    adapter.setDataForRecyclerView(detailAnime.episodes, detailAnime.url)

                    rvEpisodes.layoutManager = GridLayoutManager(this@DetailActivity, 4)
                    rvEpisodes.setHasFixedSize(true)
                    rvEpisodes.adapter = adapter
                } else if (detailAnime.type == "Movie") {
                    tvEpisodes.visibility = View.GONE
                    rvEpisodes.visibility = View.GONE
                    btnWatchMovie.visibility = View.VISIBLE

                    btnWatchMovie.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(detailAnime.url))
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun changeMenuFavoriteIcon() {
        if (isFavorite) {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
            menu?.getItem(0)?.title = resources.getString(R.string.unfavorite_this)
        } else {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
            menu?.getItem(0)?.title = resources.getString(R.string.favorite_this)
        }
    }

    private fun showToastFavorite() {
        if (isFavorite) {
            Toast.makeText(
                this, "You favorite this anime", Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this, "You un-favorite this anime", Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        changeMenuFavoriteIcon()
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.is_favorited -> {
                isFavorite = !isFavorite
                viewModel.updateFavoriteAnime(anime)
                showToastFavorite()
                changeMenuFavoriteIcon()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}