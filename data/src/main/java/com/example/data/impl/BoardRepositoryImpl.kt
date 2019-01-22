package com.example.data.impl

import com.example.data.remote.ApiService
import com.example.domain.repository.BoardRepository
import com.example.gateway.entity.Board
import io.reactivex.Single

class BoardRepositoryImpl(private val api: ApiService) : BoardRepository {

    override fun getBoardList(): Single<List<Board>> {
        return api.getBoards()
    }
}