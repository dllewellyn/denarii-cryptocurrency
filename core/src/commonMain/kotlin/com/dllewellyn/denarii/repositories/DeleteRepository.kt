package com.dllewellyn.denarii.repositories

interface DeleteRepository<T> {
    suspend fun delete(toDelete : T)
}