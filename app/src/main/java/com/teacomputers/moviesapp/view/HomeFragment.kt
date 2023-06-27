package com.teacomputers.moviesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.teacomputers.domain.dataHandler.DataState
import com.teacomputers.domain.entity.ResultModel
import com.teacomputers.moviesapp.R
import com.teacomputers.moviesapp.adapter.MovieAdapter
import com.teacomputers.moviesapp.common.hideLoading
import com.teacomputers.moviesapp.common.showLoading
import com.teacomputers.moviesapp.databinding.FragmentHomeBinding
import com.teacomputers.moviesapp.viewmodel.TrendingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: TrendingViewModel by activityViewModels()
    private lateinit var movieAdapter: MovieAdapter

    private val onItemClickCallback: (item: ResultModel) -> Unit = { item ->
        viewModel.selectedTrending = item
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getTrendingMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
        setBtnListener()
        subscribeData()
    }

    private fun subscribeData() {
        viewModel.liveTrendingData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    showLoading()
                }
                is DataState.Success -> {
                    initRecycler(it.data?.results ?: ArrayList())
                    hideLoading()
                }
                is DataState.Error -> {
                    hideLoading()
                }
                else -> {
                    hideLoading()
                }
            }
        }
    }

    private fun initRecycler(results: ArrayList<ResultModel>) {
        binding.rcMovie.apply {
            setHasFixedSize(true)
            movieAdapter = MovieAdapter(results, onItemClickCallback)
            adapter = movieAdapter
        }
    }

    private fun setBtnListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                movieAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun initTabLayout() {
        binding.tabs.addTab(binding.tabs.newTab().setText("Movie"))
        binding.tabs.addTab(binding.tabs.newTab().setText("Series"))
        binding.tabs.tabGravity = TabLayout.GRAVITY_FILL

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> viewModel.getTrendingMovies()
                    1 -> viewModel.getSeries()
                    else -> viewModel.getTrendingMovies()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}
