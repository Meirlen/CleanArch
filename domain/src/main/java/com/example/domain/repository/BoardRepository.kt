package com.example.domain.repository

import com.example.domain.exception.Failure
import com.example.domain.functional.Either
import com.example.gateway.entity.Board

interface BoardRepository {

    fun getBoardList(): Either<Failure, List<Board>>

}