package meirlen.cleanarch.ui.board

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.example.gateway.entity.Board
import com.nhaarman.mockito_kotlin.verify
import kotlinx.android.synthetic.main.board_list_fragment.*
import kotlinx.android.synthetic.main.fragment_blog.*
import kotlinx.android.synthetic.main.view_lce_loading.*
import meirlen.cleanarch.HomeActivity
import meirlen.cleanarch.TestApplication
import meirlen.cleanarch.base.vo.Resource
import meirlen.cleanarch.ui.board.list.BoardsAdapter
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.core.Koin
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
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

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        StandAloneContext.loadKoinModules(module {
            viewModel {
                mViewModel
            }
        })

        Mockito.`when`(mViewModel.getBoards()).thenReturn(listLiveData)
        fragment = BoardsFragment()
        startFragment(fragment)
        context = RuntimeEnvironment.application.baseContext
    }

    @Test
    fun shouldAddLinearLayoutManagerOnCreate() {
        assertNotNull(fragment.mRecyclerView.layoutManager)
        assertTrue(fragment.mRecyclerView.layoutManager is LinearLayoutManager)
        val manager = fragment.mRecyclerView.layoutManager as LinearLayoutManager
        assertEquals(OrientationHelper.VERTICAL, manager.orientation)
    }


}