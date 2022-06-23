package com.my.mycoremvvmtesttask.pokemon.domain

interface ResponseState {

    fun <R : Any> map(mapper: Mapper<R>): R

    data class Success<I : Any>(
        private val input: I
    ) : ResponseState {

        override fun <R : Any> map(mapper: Mapper<R>): R {
            return mapper.map(input)
        }
    }

    data class Error(
        private val exception: Exception
    ) : ResponseState {

        override fun <R : Any> map(mapper: Mapper<R>): R {
            return mapper.map(exception)
        }
    }

    interface Mapper<out R : Any> {

        fun map(input: Any): R
    }
}