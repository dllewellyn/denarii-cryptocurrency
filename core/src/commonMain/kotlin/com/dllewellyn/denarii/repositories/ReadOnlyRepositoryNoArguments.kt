package com.dllewellyn.denarii.repositories

interface ReadOnlyRepositoryNoArguments<T> {
    suspend fun retrieveData() : T
}