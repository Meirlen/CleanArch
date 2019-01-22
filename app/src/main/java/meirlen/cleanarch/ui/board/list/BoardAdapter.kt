package meirlen.cleanarch.ui.board.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import meirlen.cleanarch.R
import com.example.gateway.entity.Board
import kotlinx.android.synthetic.main.item_board.view.*
import meirlen.cleanarch.utill.ext.loadImage
import meirlen.cleanarch.utill.interfaces.ItemClickListener

class BoardsAdapter(private var listener: ItemClickListener<Board>) : RecyclerView.Adapter<BoardsAdapter.MovieViewHolder>() {

    private var mMovieList: ArrayList<Board> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_board, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val board = mMovieList[position]
        holder.itemView.txtTitle!!.text = board.name
        holder.itemView.setOnClickListener { listener.onItemClick(board)}
        holder.itemView.coverImageView.loadImage("https://pp.userapi.com/c841322/v841322681/5f307/sEetuxRTuIg.jpg")
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }

    fun setData(movieList: ArrayList<Board>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        mMovieList.clear()
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = this.itemView
    }

}