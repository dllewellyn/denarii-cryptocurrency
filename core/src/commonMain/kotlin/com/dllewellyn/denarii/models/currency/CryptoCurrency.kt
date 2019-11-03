package com.dllewellyn.denarii.models.currency

import com.dllewellyn.coinbaseapi.exceptions.ApiException

fun List<CryptoCurrency>.toCsv() = this.map { it.str }.joinToString(",")

enum class Fiat(val str: String) {
    EURO("Euro"),
    GBP("£"),
    USD("$")
}

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
    AED("AED"),
    AFN("AFN"),
    ALL("ALL"),
    AMD("AMD"),
    ANG("ANG"),
    AOA("AOA"),
    ARS("ARS"),
    AUD("AUD"),
    AWG("AWG"),
    AZN("AZN"),
    BAM("BAM"),
    BBD("BBD"),
    BDT("BDT"),
    BGN("BGN"),
    BHD("BHD"),
    BIF("BIF"),
    BMD("BMD"),
    BND("BND"),
    BOB("BOB"),
    BRL("BRL"),
    BSD("BSD"),
    BTN("BTN"),
    BWP("BWP"),
    BYN("BYN"),
    BYR("BYR"),
    BZD("BZD"),
    CAD("CAD"),
    CDF("CDF"),
    CHF("CHF"),
    CLF("CLF"),
    CLP("CLP"),
    CNH("CNH"),
    CNY("CNY"),
    COP("COP"),
    CRC("CRC"),
    CUC("CUC"),
    CVE("CVE"),
    CZK("CZK"),
    DJF("DJF"),
    DKK("DKK"),
    DOP("DOP"),
    DZD("DZD"),
    EEK("EEK"),
    EGP("EGP"),
    ERN("ERN"),
    ETB("ETB"),
    FJD("FJD"),
    FKP("FKP"),
    GEL("GEL"),
    GGP("GGP"),
    GHS("GHS"),
    GIP("GIP"),
    GMD("GMD"),
    GNF("GNF"),
    GTQ("GTQ"),
    GYD("GYD"),
    HKD("HKD"),
    HNL("HNL"),
    HRK("HRK"),
    HTG("HTG"),
    HUF("HUF"),
    IDR("IDR"),
    ILS("ILS"),
    IMP("IMP"),
    INR("INR"),
    IQD("IQD"),
    ISK("ISK"),
    JEP("JEP"),
    JMD("JMD"),
    JOD("JOD"),
    JPY("JPY"),
    KES("KES"),
    KGS("KGS"),
    KHR("KHR"),
    KMF("KMF"),
    KRW("KRW"),
    KWD("KWD"),
    KYD("KYD"),
    KZT("KZT"),
    LAK("LAK"),
    LBP("LBP"),
    LKR("LKR"),
    LRD("LRD"),
    LSL("LSL"),
    LTL("LTL"),
    LVL("LVL"),
    LYD("LYD"),
    MAD("MAD"),
    MDL("MDL"),
    MGA("MGA"),
    MKD("MKD"),
    MMK("MMK"),
    MNT("MNT"),
    MOP("MOP"),
    MRO("MRO"),
    MTL("MTL"),
    MUR("MUR"),
    MVR("MVR"),
    MWK("MWK"),
    MXN("MXN"),
    MYR("MYR"),
    MZN("MZN"),
    NAD("NAD"),
    NGN("NGN"),
    NIO("NIO"),
    NOK("NOK"),
    NPR("NPR"),
    NZD("NZD"),
    OMR("OMR"),
    PAB("PAB"),
    PEN("PEN"),
    PGK("PGK"),
    PHP("PHP"),
    PKR("PKR"),
    PLN("PLN"),
    PYG("PYG"),
    QAR("QAR"),
    RON("RON"),
    RSD("RSD"),
    RUB("RUB"),
    RWF("RWF"),
    SAR("SAR"),
    SBD("SBD"),
    SCR("SCR"),
    SEK("SEK"),
    SGD("SGD"),
    SHP("SHP"),
    SLL("SLL"),
    SOS("SOS"),
    SRD("SRD"),
    SSP("SSP"),
    STD("STD"),
    SVC("SVC"),
    SZL("SZL"),
    THB("THB"),
    TJS("TJS"),
    TMT("TMT"),
    TND("TND"),
    TOP("TOP"),
    TRY("TRY"),
    TTD("TTD"),
    TWD("TWD"),
    TZS("TZS"),
    UAH("UAH"),
    UGX("UGX"),
    UYU("UYU"),
    UZS("UZS"),
    VEF("VEF"),
    VES("VES"),
    VND("VND"),
    VUV("VUV"),
    WST("WST"),
    XAF("XAF"),
    XAG("XAG"),
    XAU("XAU"),
    XCD("XCD"),
    XDR("XDR"),
    XOF("XOF"),
    XPD("XPD"),
    XPF("XPF"),
    XPT("XPT"),
    YER("YER"),
    ZAR("ZAR"),
    ZMK("ZMK"),
    ZMW("ZMW"),
    ZRX("ZRX"),
    ZWL("ZWL"),
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