package com.example.domain.interactor
import com.example.domain.repository.BoardRepository
import com.example.gateway.entity.Board
import javax.inject.Inject

open class GetBoardsUseCase @Inject constructor(private val boardRepository: BoardRepository)
    : UseCase<List<Board>, UseCase.None>() {

    override suspend fun run(params: None) = boardRepository.getBoardList()

}