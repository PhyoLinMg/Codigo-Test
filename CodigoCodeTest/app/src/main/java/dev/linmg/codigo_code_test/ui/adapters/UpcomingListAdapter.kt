package dev.linmg.codigo_code_test.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.linmg.codigo_code_test.data.entity.UpcomingMovieEntityWithMovieItem
import dev.linmg.codigo_code_test.databinding.ItemUpcomingMoviesBinding
import javax.inject.Inject

class UpcomingListAdapter @Inject constructor(): PagingDataAdapter<UpcomingMovieEntityWithMovieItem,UpcomingListAdapter.MovieViewHolder>(MovieComparator) {
    var movieClickListener:MovieClickListener?=null
    override fun onBindViewHolder(holder: UpcomingListAdapter.MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingListAdapter.MovieViewHolder =
        MovieViewHolder(
            ItemUpcomingMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false))



    inner class MovieViewHolder(private val binding: ItemUpcomingMoviesBinding): RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                movieClickListener?.onMovieClicked(
                    binding,
                    getItem(absoluteAdapterPosition) as UpcomingMovieEntityWithMovieItem
                )
            }
        }

        fun bind(item:UpcomingMovieEntityWithMovieItem) = with(binding){
            movie=item
        }

    }
    object MovieComparator : DiffUtil.ItemCallback<UpcomingMovieEntityWithMovieItem>() {
        override fun areItemsTheSame(oldItem: UpcomingMovieEntityWithMovieItem, newItem: UpcomingMovieEntityWithMovieItem) =
            oldItem.movie?.id == newItem.movie?.id

        override fun areContentsTheSame(oldItem: UpcomingMovieEntityWithMovieItem, newItem: UpcomingMovieEntityWithMovieItem) =
            oldItem == newItem
    }

    interface MovieClickListener {
        fun onMovieClicked(binding: ItemUpcomingMoviesBinding, movie:UpcomingMovieEntityWithMovieItem)
    }
}