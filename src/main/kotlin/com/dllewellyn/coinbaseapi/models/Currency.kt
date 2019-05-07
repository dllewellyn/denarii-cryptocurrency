package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.exceptions.ApiException

fun List<Currency>.toCsv() = this.map { it.str }.joinToString(",")

enum class Currency(val str : String) {
    BITCOIN("BTC"),
    USDOLLAR("USD"),
    BITCOIN_CASH("BCH"),
    EOS("EOS"),
    LITECOIN("LTC"),
    XRP("XRP"),
    TRON("TRX"),
    ARC_BLOCK("ABT"),
    BGOGO_TOKEN("BGG"),
    QTUM("QTUM"),
    ETHEREUM_CLASS("ETC"),
    ZERO_BLANK("ZB"),
    Z_CASH("ZEC"),
    NEO("NEO"),
    BINANCE_COIN("BNB"),
    DASH("DASH"),
    STELLAR("XLM"),
    CARDANO("ADA"),
    TETHER("USDT"),
    BITCOIN_SV("BSV"),
    PAXOS_STANDARD("PAX"),
    ETHEREUM("ETH");

    companion object {
        fun fromString(str: String): Currency {
            Currency.values().forEach {
                if (it.str == str) {
                    return it
                }
            }

            throw ApiException("Unexpected currency provided to currency: $str")
        }
    }

}