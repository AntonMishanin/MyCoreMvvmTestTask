package com.my.mycoremvvmtesttask.pokemon.data

import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException
import com.my.mycoremvvmtesttask.pokemon.domain.ResponseState

interface Repository {

    suspend fun <T : ResponseState> handle(block: suspend () -> T): T

    abstract class Abstract(
        private val handleError: HandleError
    ) : Repository {

        override suspend fun <T : ResponseState> handle(block: suspend () -> T): T =
            try {
                block.invoke()
            } catch (exception: Exception) {
                val e = handleError.handle(exception)
                ResponseState.Error(
                    message = e.message.toString(),
                    exception = e
                ) as T
            }
    }
}