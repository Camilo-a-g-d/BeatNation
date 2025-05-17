package com.example.beatnation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.beatnation.R
import com.example.beatnation.adapters.SliderAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.sliderViewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.sliderIndicator)

        val images = listOf(
            R.drawable.slider_1,
            R.drawable.slider_2,
            R.drawable.slider_3,
            R.drawable.slider_4
        )

        val texts = listOf(
            getString(R.string.slide1),
            getString(R.string.slide2),
            getString(R.string.slide3),
            getString(R.string.slide4)
        )

        viewPager.adapter = SliderAdapter(images, texts)
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        // Referencias
        val popBtn = view.findViewById<LinearLayout>(R.id.popBtn)
        val rockBtn = view.findViewById<LinearLayout>(R.id.rockBtn)
        val rapBtn = view.findViewById<LinearLayout>(R.id.rapBtn)
        val edmBtn = view.findViewById<LinearLayout>(R.id.edmBtn)
        val reggaetonBtn = view.findViewById<LinearLayout>(R.id.reggaetonBtn)
        val kpopBtn = view.findViewById<LinearLayout>(R.id.kpopBtn)

        // Navegación con datos
        popBtn.setOnClickListener {
            navigateToGenre("Pop")
        }

        rockBtn.setOnClickListener {
            navigateToGenre("Rock")
        }

        rapBtn.setOnClickListener {
            navigateToGenre("Rap")
        }

        edmBtn.setOnClickListener {
            navigateToGenre("EDM")
        }

        reggaetonBtn.setOnClickListener {
            navigateToGenre("Reggaetón")
        }

        kpopBtn.setOnClickListener {
            navigateToGenre("K-pop")
        }
    }

    private fun navigateToGenre(genre: String) {
        val bundle = bundleOf("genre" to genre)
        findNavController().navigate(R.id.action_home_to_genreDetailFragment, bundle)
    }
}
