package com.teacomputers.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.teacomputers.domain.entity.ResultModel
import com.teacomputers.moviesapp.BuildConfig
import com.teacomputers.moviesapp.databinding.ItemTrendingBinding
import java.util.*

class MovieAdapter(
    private val nowPlayingList: ArrayList<ResultModel>,
    private val onItemClickCallback: (movie: ResultModel) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), Filterable {

    private var trendingList: ArrayList<ResultModel> = nowPlayingList

    private lateinit var binding: ItemTrendingBinding

    class MovieViewHolder(itemView: ItemTrendingBinding) : RecyclerView.ViewHolder(itemView.root) {
        val imgMovie = itemView.imgMovie
        val txtTitle = itemView.txtTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = ItemTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = trendingList[position]

        bindData(holder, item)
    }

    private fun bindData(holder: MovieViewHolder, item: ResultModel) {
        holder.txtTitle.text = getName(item)

        val moviePosterUrl = BuildConfig.imageUrl + item.poster_path

        Glide.with(holder.itemView.context)
            .load(moviePosterUrl)
            .into(holder.imgMovie)

        holder.itemView.setOnClickListener {
            onItemClickCallback.invoke(item)
        }
    }

    private fun getName(item: ResultModel): String {
        val text = if (item.title == null) {
            item.name
        } else {
            item.title
        }

        return text
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                trendingList = if (charSearch.isEmpty()) {
                    nowPlayingList
                } else {
                    val resultList = ArrayList<ResultModel>()
                    for (row in nowPlayingList) {
                        if (row.title.lowercase(Locale.ROOT)
                            .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = trendingList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                trendingList = results?.values as ArrayList<ResultModel>
                notifyDataSetChanged()
            }
        }
    }
}
