package com.dllewellyn.coinbaseapi.models.currency

import com.dllewellyn.coinbaseapi.exceptions.ApiException

fun List<CryptoCurrency>.toCsv() = this.map { it.str }.joinToString(",")

enum class CryptoCurrency(val str: String) {
    ALGO("ALGO"),
    EURO("EUR"),
    GBP("GBP"),
    CVC("CVC"),
    DAI("DAI"),
    DNT("DNT"),
    MANA("MANA"),
    BITCOIN("BTC"),

    GNT("GNT"),
    BAT("BAT"),
    USDC("USDC"),
    USDOLLAR("USD"),
    BITCOIN_CASH("BCH"),
    EOS("EOS"),
    LITECOIN("LTC"),
    LOOM("LOOM"),
    LINK("LINK"),
    XRP("XRP"),
    XTZ("XTZ"),
    TRON("TRX"),
    ARC_BLOCK("ABT"),
    BGOGO_TOKEN("BGG"),
    REP("REP"),
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
        fun fromString(str: String): CryptoCurrency {
            CryptoCurrency.values().forEach {
                if (it.str == str) {
                    return it
                }
            }

            throw ApiException("Unexpected cryptoCurrency provided to cryptoCurrency: $str")
        }
    }

}