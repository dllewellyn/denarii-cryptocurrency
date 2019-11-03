package com.dllewellyn.denarii.models.currency

data class SupportedCurrency(
    val id: String,
    val name: String = "",
    val minSize: Double = 0.0) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as SupportedCurrency
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
