package meirlen.cleanarch.ui.board

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.example.domain.interactor.GetBoardsUseCase
import com.example.gateway.entity.Board
import meirlen.cleanarch.base.vo.Resource


open class BoardViewModel(private val getBoardsUseCase: GetBoardsUseCase) : ViewModel() {


    private val uiData = MutableLiveData<Resource<List<Board>>>()

    fun getBoards(): MutableLiveData<Resource<List<Board>>> {
        uiData.value = Resource.loading(null)

        getBoardsUseCase.execute(
            {
                uiData.value = Resource.success(it)
            },
            {
                uiData.value = Resource.error(it.message.toString(), null)

            }
        )
        return uiData
    }


}

