package com.my.mycoremvvmtesttask.pokemon.domain

interface ResponseState<I : Any> {

    fun <R : Any> map(mapper: Mapper<I, R>): R

    data class Success<I : Any>(
        private val input: I
    ) : ResponseState<I> {

        override fun <R : Any> map(mapper: Mapper<I, R>): R {
            return mapper.map(input)
        }
    }

    data class Error(
        private val exception: Exception
    ) : ResponseState<Exception> {

        override fun <R : Any> map(mapper: Mapper<Exception, R>): R {
            return mapper.map(exception)
        }
    }

    interface Mapper<in I : Any, out R : Any> {

        fun map(input: I): R
    }
}