package com.dllewellyn.coinbaseapi

import kotlin.String

interface AccountEntity {
  val currencyValue: String

  val balance: String

  val available: String

  val hold: String

  val uid: String

  val provider: String

  data class Impl(
    override val currencyValue: String,
    override val balance: String,
    override val available: String,
    override val hold: String,
    override val uid: String,
    override val provider: String
  ) : AccountEntity {
    override fun toString(): String = """
    |AccountEntity.Impl [
    |  currencyValue: $currencyValue
    |  balance: $balance
    |  available: $available
    |  hold: $hold
    |  uid: $uid
    |  provider: $provider
    |]
    """.trimMargin()
  }
}
