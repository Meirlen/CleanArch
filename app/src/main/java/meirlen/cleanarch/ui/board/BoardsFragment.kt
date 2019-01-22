package meirlen.cleanarch.ui.board

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import com.example.domain.exception.Failure
import meirlen.cleanarch.R
import meirlen.cleanarch.base.ui.BaseFragment
import meirlen.cleanarch.base.vo.Status
import meirlen.cleanarch.utill.interfaces.ItemClickListener
import com.example.gateway.entity.Board
import meirlen.cleanarch.ui.board.list.BoardsAdapter
import meirlen.cleanarch.utill.ext.failure
import meirlen.cleanarch.utill.ext.observe
import kotlinx.android.synthetic.main.board_list_fragment.*
import kotlinx.android.synthetic.main.view_lce_loading.*
import org.koin.android.viewmodel.ext.android.viewModel

class BoardsFragment : BaseFragment<List<Board>>(), ItemClickListener<Board> {


    val TAG = javaClass.simpleName
    private val model: BoardViewModel by viewModel()
    private lateinit var mAdapter: BoardsAdapter

    companion object {
        fun newInstance(): BoardsFragment {
            return BoardsFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = BoardsAdapter(this)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mAdapter

        model.getBoards()
        model.uiData.observe(this, Observer(this@BoardsFragment::renderList))
        model.failure.observe(this, Observer(this@BoardsFragment::handleFailure))

    }

    private fun renderList(movies: List<Board>?) {
        mAdapter.setData(movies as ArrayList<Board>)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(getString(R.string.failure_network_connection))
            is Failure.ServerError -> renderFailure(getString(R.string.failure_server_error))
        }
    }

    private fun renderFailure(message: String) {
        toast(message)

    }

    override fun onItemClick(dataObject: Board) {
        router.showColumns(context, dataObject.id)
    }

    override fun onResponse(response: List<Board>) {
    }

    override fun getContentView(): Int {
        return R.layout.board_list_fragment
    }

}