package meirlen.cleanarch.ui.board

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import com.example.gateway.entity.Board
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.verify
import kotlinx.android.synthetic.main.board_list_fragment.*
import meirlen.cleanarch.TestApplication
import meirlen.cleanarch.base.vo.Resource
import meirlen.cleanarch.routers.MainRouter
import meirlen.cleanarch.ui.board.adapter.BoardsAdapter
import meirlen.cleanarch.utill.TestUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.test.KoinTest
import org.mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil.*

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], manifest = Config.NONE, application = TestApplication::class)
class BoardsFragmentTest : KoinTest {

    private lateinit var fragment: BoardsFragment
    private lateinit var context: Context
    private val listLiveData = MutableLiveData<Resource<List<Board>>>()
    @Mock
    private lateinit var mViewModel: BoardViewModel
    @Mock
    private lateinit var mainRouter: MainRouter
    @Captor
    lateinit var captorContext: ArgumentCaptor<Context>
    @Captor
    lateinit var captorBoard: ArgumentCaptor<String>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        StandAloneContext.loadKoinModules(module {
            viewModel {
                mViewModel
            }
            single {
                mainRouter
            }
        })
        Mockito.`when`(mViewModel.getBoards()).thenReturn(listLiveData)
        fragment = BoardsFragment()
        startFragment(fragment)
        context = RuntimeEnvironment.application.baseContext
    }

    @Test
    fun shouldAddBoardAdapterOnCreate() {
        assertNotNull(fragment.mRecyclerView.adapter)
        assertTrue(fragment.mRecyclerView.adapter is BoardsAdapter)
        val adapter = fragment.mRecyclerView.adapter as BoardsAdapter
        assertEquals(0, adapter.itemCount)
    }

    @Test
    fun shouldAddLinearLayoutManagerOnCreate() {
        assertNotNull(fragment.mRecyclerView.layoutManager)
        assertTrue(fragment.mRecyclerView.layoutManager is LinearLayoutManager)
        val manager = fragment.mRecyclerView.layoutManager as LinearLayoutManager
        assertEquals(OrientationHelper.VERTICAL, manager.orientation)
    }

    @Test
    fun shouldLoading() {
        listLiveData.value = Resource.loading(null)
        assertEquals(View.VISIBLE, fragment.mProgressBar.visibility)
    }

    @Test
    fun shouldSuccessDataLoaded() {
        verify(mViewModel).getBoards()
        val boardsCount = 5
        listLiveData.value = Resource.success(TestUtil.newList(TestUtil.newBoard(), boardsCount))
        assertEquals(boardsCount, fragment.mRecyclerView.adapter!!.itemCount)
    }

    @Test
    fun shouldErrorLoaded() {
        val boardsCount = 0
        listLiveData.value = Resource.error("Error handling", null)
        assertEquals(boardsCount, fragment.mRecyclerView.adapter!!.itemCount)
    }

    @Test
    fun shouldOnItemClick() {
        fragment.onItemClick(TestUtil.newBoard())
        verify(mainRouter).showColumns(captorContext.capture(), capture(captorBoard))
    }
}