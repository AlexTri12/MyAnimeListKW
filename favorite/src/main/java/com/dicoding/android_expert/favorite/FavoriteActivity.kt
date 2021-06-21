package com.dicoding.android_expert.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.android_expert.favorite.databinding.ActivityFavoriteBinding
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.elevation = 0f
        supportActionBar?.title = resources.getString(R.string.title_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = FavoriteSectionsPagerAdapter(this, supportFragmentManager)
        binding.favoriteViewPager.adapter = sectionsPagerAdapter
        binding.favoriteTabLayout.setupWithViewPager(binding.favoriteViewPager)
    }
}