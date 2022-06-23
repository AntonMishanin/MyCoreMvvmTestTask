package com.my.mycoremvvmtesttask.pokemon.data

import com.github.johnnysc.coremvvm.data.HandleError
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState

interface Repository {

    suspend fun handle(block: suspend () -> ResponseState): ResponseState

    abstract class Abstract(
        private val handleError: HandleError
    ) : Repository {

        override suspend fun handle(block: suspend () -> ResponseState): ResponseState {
            return try {
                block.invoke()
            } catch (exception: Exception) {
                exception.printStackTrace()

                val domainException = handleError.handle(exception)
                ResponseState.Error(exception = domainException)
            }
        }
    }
}