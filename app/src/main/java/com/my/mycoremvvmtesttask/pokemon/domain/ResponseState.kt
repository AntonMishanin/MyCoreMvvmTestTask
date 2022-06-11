package com.my.mycoremvvmtesttask.pokemon.domain

interface ResponseState {

    fun <I : Any, R : Any> map(mapper: Mapper<I, R>): R

    data class Success<in T : Any>(
        private val input: T
    ) : ResponseState {

        override fun <I : Any, R : Any> map(mapper: Mapper<I, R>): R {
            return mapper.map(input as I)
        }
    }

    data class Error(
        private val message: String,
        private val exception: Exception
    ) : ResponseState {

        override fun <I : Any, R : Any> map(mapper: Mapper<I, R>): R {
            return mapper.map(exception as I)
        }
    }

    class Progress : ResponseState {

        override fun <I : Any, R : Any> map(mapper: Mapper<I, R>): R {
            return mapper.map(Unit as I)
        }
    }

    interface Mapper<in I : Any, out R : Any> {

        fun map(input: I): R
    }
}