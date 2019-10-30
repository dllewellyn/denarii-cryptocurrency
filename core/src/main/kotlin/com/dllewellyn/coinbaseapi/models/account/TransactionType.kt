package com.dllewellyn.coinbaseapi.models.account

sealed class TransactionType {
    class Send() : TransactionType()
    class REQUEST() : TransactionType()
    class TRANSFER() : TransactionType()
    class BUY() : TransactionType()
    class SELL() : TransactionType()
    class FIAT_DEPOSIT() : TransactionType()
    class FIAT_WITHDRAWAL() : TransactionType()
    class EXCHANGE_DEPOSIT() : TransactionType()
    class EXCHANGE_WITHDRAWAL() : TransactionType()
    class VAULT_WITHDRAWAL() : TransactionType()
    class Other() : TransactionType()
}