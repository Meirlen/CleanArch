package com.example.domain.repository

import com.example.domain.exception.Failure
import com.example.domain.functional.Either
import com.example.gateway.entity.Board
import io.reactivex.Single

interface BoardRepository {

    fun getBoardList(): Single<List<Board>>

}