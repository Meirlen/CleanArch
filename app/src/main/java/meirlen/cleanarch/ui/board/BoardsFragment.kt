package meirlen.cleanarch.ui.board

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.domain.exception.Failure
import meirlen.cleanarch.R
import meirlen.cleanarch.base.ui.BaseFragment
import meirlen.cleanarch.utill.interfaces.ItemClickListener
import com.example.gateway.entity.Board
import meirlen.cleanarch.ui.board.list.BoardsAdapter
import kotlinx.android.synthetic.main.board_list_fragment.*
import meirlen.cleanarch.base.vo.Resource
import meirlen.cleanarch.base.vo.Status
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class BoardsFragment : BaseFragment<List<Board>>(), ItemClickListener<Board> {


    val TAG = javaClass.simpleName
    val mViewModel: BoardViewModel by inject()
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

        mViewModel.getBoards().observe(this, Observer {
            when (it?.status) {
                Status.LOADING -> {
                    displayProgress()
                }
                Status.SUCCESS -> {
                    Log.d(TAG, "--> Success! | loaded ${it.data?.size ?: 0} records.")
                    displayNormal()
                    //mAdapter.setData(it.data as ArrayList<Board>)
                }
                Status.ERROR -> {
                    toast("Error: ${it.message}")
                }
            }
        })
    }


    private fun displayNormal() {
        mProgressBar.visibility = View.GONE
    }

    private fun displayProgress() {
        mProgressBar.visibility = View.VISIBLE
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