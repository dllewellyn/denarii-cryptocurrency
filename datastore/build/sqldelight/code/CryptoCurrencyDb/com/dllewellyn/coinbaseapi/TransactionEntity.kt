package com.dllewellyn.coinbaseapi

import kotlin.String

interface TransactionEntity {
  val accountId: String

  val amount: String

  val balance: String?

  val description: String?

  val id: String

  val status: String?

  val type: String

  val date: String

  val nativeCurrency: String?

  val nativeAmount: String?

  data class Impl(
    override val accountId: String,
    override val amount: String,
    override val balance: String?,
    override val description: String?,
    override val id: String,
    override val status: String?,
    override val type: String,
    override val date: String,
    override val nativeCurrency: String?,
    override val nativeAmount: String?
  ) : TransactionEntity {
    override fun toString(): String = """
    |TransactionEntity.Impl [
    |  accountId: $accountId
    |  amount: $amount
    |  balance: $balance
    |  description: $description
    |  id: $id
    |  status: $status
    |  type: $type
    |  date: $date
    |  nativeCurrency: $nativeCurrency
    |  nativeAmount: $nativeAmount
    |]
    """.trimMargin()
  }
}
