package meirlen.cleanarch.ui.board.adapter

import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.example.gateway.entity.Board
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.verify
import kotlinx.android.synthetic.main.item_card.view.*
import meirlen.cleanarch.R
import meirlen.cleanarch.TestApplication
import meirlen.cleanarch.base.constants.Constant
import meirlen.cleanarch.utill.TestUtil
import meirlen.cleanarch.utill.interfaces.ItemClickListener
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.junit.Assert.assertEquals
import org.mockito.ArgumentCaptor
import org.mockito.Captor


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], manifest = Config.NONE, application = TestApplication::class)
class BoardsAdapterTest {

    private lateinit var boardsAdapter: BoardsAdapter
    private lateinit var boardViewHolder: BoardsAdapter.BoardViewHolder
    @Mock
    private lateinit var itemClickListener: ItemClickListener<Board>

    @Captor
    lateinit var captorBoard: ArgumentCaptor<Board>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        boardsAdapter = BoardsAdapter(itemClickListener)
    }

    @Test
    fun shouldCorrectGetItemCount() {
        val items = 5
        boardsAdapter.setData(TestUtil.newList(TestUtil.newBoard(),items) as ArrayList<Board>)
        assertEquals(boardsAdapter.itemCount, items)
    }

    @Test
    fun shouldNotifySetChangedAfterSetData() {
        boardsAdapter.setData(TestUtil.newList(TestUtil.newBoard()) as ArrayList<Board>)
        verify(boardsAdapter).notifyDataSetChanged()
    }

    @Test
    fun shouldOnBindViewHolder() {
        boardsAdapter.setData(TestUtil.newList(TestUtil.newBoard()) as ArrayList<Board>)
        val inflater = RuntimeEnvironment.application.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = inflater.inflate(R.layout.item_board, null, false)
        boardViewHolder = BoardsAdapter.BoardViewHolder(listItemView)
        boardsAdapter.onBindViewHolder(boardViewHolder, 0)
        assertEquals(TestUtil.DEFAULT_FIRST_NAME, boardViewHolder.itemView.txtTitle.text.toString())

        boardViewHolder.itemView.performClick()
        verify(itemClickListener).onItemClick(capture(captorBoard))
    }

    @Test
    fun shouldClearAdapter() {
        boardsAdapter.setData(TestUtil.newList(TestUtil.newBoard()) as ArrayList<Board>)
        assertEquals(boardsAdapter.itemCount,Constant.DEFAULT_PER_PAGE_COUNT)
        boardsAdapter.clearAdapter()
        assertEquals(0,boardsAdapter.itemCount)
    }
}