package com.dllewellyn.denarii.repositories

interface UpdateRepository<K, T> {
    suspend fun update(thingToUpdate : K, newValue : T)
}