package com.dllewellyn.coinbaseapi.authentcation

import com.dllewellyn.coinbaseapi.models.OauthProvider
import org.joda.time.DateTime

fun OauthProvider.hasExpired() =
    DateTime(created_at + expires_in).isAfterNow
