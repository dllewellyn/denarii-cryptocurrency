package com.dllewellyn.coinbaseapi.authentcation

import com.dllewellyn.coinbaseapi.models.OauthProvider
import java.util.*

fun OauthProvider.hasExpired() =
    Date((created_at * 1000) + (expires_in * 1000)).before(Date())
