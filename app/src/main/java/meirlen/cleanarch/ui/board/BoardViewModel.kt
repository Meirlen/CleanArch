package meirlen.cleanarch.ui.board

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.example.domain.interactor.GetBoardsUseCase
import com.example.gateway.entity.Board
import meirlen.cleanarch.base.vo.Resource


open class BoardViewModel(private val getBoardsUseCase: GetBoardsUseCase) : ViewModel() {


    val uiData = MutableLiveData<Resource<List<Board>>>()

    fun getBoards() {
        getBoardsUseCase.execute(
            {
                uiData.value = Resource.success(it)
            },
            {
                uiData.value = Resource.error(it.message.toString(), null)

            }
        )
    }


}

