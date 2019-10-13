package com.dllewellyn.coinbaseapi

import kotlin.Long
import kotlin.String

interface ProductTickerEntity {
  val ask: String

  val bid: String

  val price: String

  val time: String

  val size: String

  val tradeId: Long

  val volume: String

  data class Impl(
    override val ask: String,
    override val bid: String,
    override val price: String,
    override val time: String,
    override val size: String,
    override val tradeId: Long,
    override val volume: String
  ) : ProductTickerEntity {
    override fun toString(): String = """
    |ProductTickerEntity.Impl [
    |  ask: $ask
    |  bid: $bid
    |  price: $price
    |  time: $time
    |  size: $size
    |  tradeId: $tradeId
    |  volume: $volume
    |]
    """.trimMargin()
  }
}
