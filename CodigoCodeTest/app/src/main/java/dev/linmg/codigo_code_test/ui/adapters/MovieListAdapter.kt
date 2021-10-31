package dev.linmg.codigo_code_test.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.linmg.codigo_code_test.data.entity.Movie
import dev.linmg.codigo_code_test.data.entity.PopularMovieEntityWithMovieItem
import dev.linmg.codigo_code_test.data.entity.UpcomingMovieEntityWithMovieItem
import dev.linmg.codigo_code_test.databinding.ItemMovieBinding
import javax.inject.Inject

class MovieListAdapter @Inject constructor(): PagingDataAdapter<PopularMovieEntityWithMovieItem,MovieListAdapter.MovieViewHolder>(MovieComparator) {
    var movieClickListener:MovieClickListener?=null
    override fun onBindViewHolder(holder: MovieListAdapter.MovieViewHolder, position: Int) {
            getItem(position)?.let { holder.bind(it) }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListAdapter.MovieViewHolder =
        MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))



    inner class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                movieClickListener?.onMovieClicked(
                    binding,
                    getItem(absoluteAdapterPosition) as PopularMovieEntityWithMovieItem
                )
            }
        }

        fun bind(item:PopularMovieEntityWithMovieItem) = with(binding){
            movie=item
        }

    }
    object MovieComparator : DiffUtil.ItemCallback<PopularMovieEntityWithMovieItem>() {
        override fun areItemsTheSame(oldItem: PopularMovieEntityWithMovieItem, newItem: PopularMovieEntityWithMovieItem) =
            oldItem.movie?.id == newItem.movie?.id

        override fun areContentsTheSame(oldItem: PopularMovieEntityWithMovieItem, newItem: PopularMovieEntityWithMovieItem) =
            oldItem == newItem
    }

    interface MovieClickListener {
        fun onMovieClicked(binding: ItemMovieBinding, movie:PopularMovieEntityWithMovieItem)
    }
}