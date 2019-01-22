package meirlen.cleanarch.ui.board

import android.arch.lifecycle.MutableLiveData

import com.example.domain.interactor.GetBoardsUseCase
import com.example.domain.interactor.UseCase
import meirlen.cleanarch.base.vo.Resource
import com.example.gateway.entity.Board
import meirlen.cleanarch.base.ui.BaseViewModeli


open class BoardViewModel(private val getBoardsUseCase: GetBoardsUseCase) : BaseViewModeli() {

    val uiData: MutableLiveData<List<Board>> = MutableLiveData()

    fun getBoards() = getBoardsUseCase(UseCase.None())
    { it.either(::handleFailure, ::handleMovieList) }

    private fun handleMovieList(movies: List<Board>) {
        this.uiData.value = movies
    }
}

