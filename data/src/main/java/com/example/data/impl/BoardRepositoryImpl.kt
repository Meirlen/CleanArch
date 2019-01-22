package com.example.data.impl
import com.example.data.remote.ApiService
import com.example.domain.exception.Failure
import com.example.domain.functional.Either
import com.example.domain.repository.BoardRepository
import com.example.gateway.entity.Board

import io.reactivex.Single
import retrofit2.Call

class BoardRepositoryImpl(private val api: ApiService) : BoardRepository {


    override fun getBoardList(): Either<Failure, List<Board>> {
        return request(api.getBoards(), { it }, emptyList())
    }


    private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}