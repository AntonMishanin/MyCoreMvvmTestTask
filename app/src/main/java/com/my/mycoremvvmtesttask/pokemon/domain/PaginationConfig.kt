package com.my.mycoremvvmtesttask.pokemon.domain

interface PaginationConfig {

    fun offset(): Int

    fun limit(): Int

    class Base : PaginationConfig {

        override fun offset() = 0

        override fun limit() = 20
    }
}