package com.dicoding.android_expert.myanimelistkw.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.android_expert.core.adapter.ListAnimeAdapter
import com.dicoding.android_expert.core.vo.Resource
import com.dicoding.android_expert.myanimelistkw.databinding.FragmentMovieBinding
import com.dicoding.android_expert.myanimelistkw.detail.DetailActivity
import com.dicoding.android_expert.myanimelistkw.tvshow.TVShowFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment(private val fromActivity: String) : Fragment() {

    companion object {
        const val HOME_ACTIVITY = "home_activity"
        const val FAVORITE_ACTIVITY = "favorite_activity"
    }

    private val viewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private lateinit var listAnimeAdapter: ListAnimeAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            listAnimeAdapter = ListAnimeAdapter()
            listAnimeAdapter.onItemClick = { item ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID, item.id)
                startActivity(intent)
            }

            if (fromActivity == TVShowFragment.HOME_ACTIVITY) {
                getDataForMainActivity()
            } else if (fromActivity == TVShowFragment.FAVORITE_ACTIVITY) {
                getDataForFavoriteActivity()
            }

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = listAnimeAdapter
            }
        }
    }

    private fun getDataForMainActivity() {
        viewModel.movieList.observe(viewLifecycleOwner, { animeList ->
            if (animeList != null) {
                when(animeList) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvDatasetEmpty.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvDatasetEmpty.visibility = View.GONE
                        listAnimeAdapter.setAnimeList(animeList.data)
                        binding.rvMovie.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvDatasetEmpty.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun getDataForFavoriteActivity() {
        viewModel.favoriteMovieList.observe(viewLifecycleOwner, { animeList ->
            if (animeList.isNullOrEmpty()) {
                binding.tvDatasetEmpty.visibility = View.GONE
                binding.tvDatasetEmpty.visibility = View.VISIBLE
            } else {
                binding.tvDatasetEmpty.visibility = View.GONE
                binding.tvDatasetEmpty.visibility = View.GONE
                listAnimeAdapter.setAnimeList(animeList)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}