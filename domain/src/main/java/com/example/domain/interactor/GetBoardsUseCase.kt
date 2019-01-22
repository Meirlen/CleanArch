package com.example.domain.interactor

import com.example.domain.base.SingleUseCase
import com.example.domain.repository.BoardRepository
import com.example.gateway.entity.Board
import io.reactivex.Single
import javax.inject.Inject

class GetBoardsUseCase @Inject constructor(private val boardRepository: BoardRepository) :
    SingleUseCase<List<Board>>() {

    override fun buildUseCaseSingle(): Single<List<Board>> {
        return boardRepository.getBoardList()
    }

}