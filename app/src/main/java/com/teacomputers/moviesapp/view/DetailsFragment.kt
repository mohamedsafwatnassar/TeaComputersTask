package com.teacomputers.moviesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.teacomputers.moviesapp.BuildConfig
import com.teacomputers.moviesapp.databinding.FragmentDetailsBinding
import com.teacomputers.moviesapp.viewmodel.TrendingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: TrendingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val moviePosterUrl = BuildConfig.imageUrl + viewModel.selectedTrending.poster_path

        Glide.with(requireContext())
            .load(moviePosterUrl)
            .into(binding.image)

        if (viewModel.selectedTrending.title == null) {
            binding.title.text = viewModel.selectedTrending.name
        } else binding.title.text = viewModel.selectedTrending.title
    }
}
