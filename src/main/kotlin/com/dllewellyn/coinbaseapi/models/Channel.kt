package com.dllewellyn.coinbaseapi.models

sealed class Channel(val type : String) {
    class Type2 : Channel("level2")
}