package com.dllewellyn.denarii.math

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.models.currency.CryptoCurrency
import com.dllewellyn.denarii.models.marketinfo.BuySellPrice
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode

suspend fun List<Account>.currentValue(
    supportedCurrency: CryptoCurrency,
    exchangeRates: ReadOnlyRepositoryArgument<CryptoCurrency, List<BuySellPrice>>
): BigDecimal = exchangeRates.retrieveData(supportedCurrency).let { rates ->
    val values = map { it.currencyValue }

    var temp = BigDecimal.fromInt(0)

    val rates = rates.filter {
        values.contains(it.currency)
    }

    forEach { account ->
        rates.firstOrNull() { it.currency == account.currencyValue }
            ?.let { buyAndSell ->
                temp = temp.add(account.balance.divide(buyAndSell.amount, DecimalMode(10, RoundingMode.CEILING)))
            }
    }

    return temp
}

