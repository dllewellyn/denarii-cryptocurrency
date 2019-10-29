package com.dllewellyn.coinbaseapi.math

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.models.marketinfo.BuySellPrice
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import java.math.BigDecimal

suspend fun List<Account>.currentValue(
    supportedCurrency: CryptoCurrency,
    exchangeRates: ReadOnlyPostRepository<CryptoCurrency, List<BuySellPrice>>
): BigDecimal = exchangeRates.retrieveData(supportedCurrency).let { rates ->
    val values = map { it.currencyValue }

    var temp = BigDecimal(0)

    val rates = rates.filter {
        values.contains(it.currency)
    }

    forEach { account ->
        rates.firstOrNull() { it.currency == account.currencyValue }
            ?.let { buyAndSell ->
                temp = temp.add(BigDecimal(account.balance.toDouble() / buyAndSell.amount.toDouble()))
            }
    }

    return temp
}

