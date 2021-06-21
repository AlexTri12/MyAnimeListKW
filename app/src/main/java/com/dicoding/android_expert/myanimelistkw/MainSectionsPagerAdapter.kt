package com.dicoding.android_expert.myanimelistkw

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.android_expert.myanimelistkw.movie.MovieFragment
import com.dicoding.android_expert.myanimelistkw.tvshow.TVShowFragment

class MainSectionsPagerAdapter(
    private val mContext: Context,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.title_movie, R.string.title_tv_show)
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MovieFragment(MovieFragment.HOME_ACTIVITY)
            1 -> TVShowFragment(TVShowFragment.HOME_ACTIVITY)
            else -> Fragment()
        }
    }
}