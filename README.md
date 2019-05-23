[![Build Status](https://travis-ci.org/dllewellyn/coinbaseAPI.svg?branch=master)](https://travis-ci.org/dllewellyn/coinbaseAPI)


# Table of Contents  
[Sandbox](#Sandbox)<br>

[Un-authenticated endpoints](#Unauthenticated requests)<br>
[WebSockets](#Web sockets) <br>
[Authenticated endpoints](#Authenticated API)

# Installation 


```
repositories {
    maven {
        url  "https://dl.bintray.com/dllewellyn/coinbase-api-kt" 
    }
}
dependencies {
    implementation 'com.dllewellyn.coinbaseAPI:CoinbaseAPI:1.0'
}
```
[More info](https://bintray.com/dllewellyn/coinbase-api-kt/coinbase-api-kt)
# Usage

## Sandbox

By default, the real coinbase API is used. To use the sandbox
at some point before first using the API call

```
Api.sandbox = true
```
## Exceptions

The coinbase API can give fairly useful responses. These are encapsulated in an 'ApiException'
will contain the error message from the server. Example response:

```
ApiException: {"message":"Insufficient funds"}
```

## Unauthenticated requests

Below is a list of un-authenticated requests (i.e. those that do not require any tokens to be generated)

### List of currencies

List currencies as an observable, emitting each
cryptoCurrency one at a time

```
import com.dllewellyn.coinbaseapi.Api

Api.currencies().getCurrencies()
    .doAfterNext { println(it) }
    .blockingLast()
```

List currencies as one list of all currencies

```
import com.dllewellyn.coinbaseapi.Api

Api.currencies().getCurrencyList()
    .blockingGet()
```

### Exchange rates

List of exchange rates 
```
import com.dllewellyn.coinbaseapi.Api
import com.dllewellyn.coinbaseapi.interfaces.filterByCurrency
import com.dllewellyn.coinbaseapi.models.CryptoCurrency

val result = Api.exchangeRates()
    .getExchangeRates(Currency.BITCOIN)
    .blockingGet()
println(result)

```

Filter only for the cryptoCurrency you're interested in 

```
val filteredResult = Api.exchangeRates()
    .getExchangeRates(Currency.BITCOIN)
    .filterByCurrency("USD")
    .blockingGet()

println(filteredResult)
```

### Currency pairs

Retrieve currency pairs

```
import com.dllewellyn.coinbaseapi.Api

val result = Api.currencyPairs()
    .getCurrencyPairs()
    .blockingGet()

println(result)
```

Retrieve as observable

```
import com.dllewellyn.coinbaseapi.Api

val result = Api.currencyPairs()
    .getCurrencyPairs()
    .blockingGet()

println(result)
```

You can also retrieve and filter by a certain currency
```
Api.currencyPairs()
    .currencyPairsContaining(SupportedCurrency("USDC"))
    .blockingGet()
```

Store the results into a cache

```
import com.dllewellyn.coinbaseapi.Api
import com.dllewellyn.coinbaseapi.cache.MemoryCache
import com.dllewellyn.coinbaseapi.cache.intoCache
import com.dllewellyn.coinbaseapi.models.CurrencyPair


val cache = MemoryCache<CurrencyPair>()

Api.currencyPairs()
    .getCurrencyPairs()
    .intoCache(cache)
    .blockingGet()


println(cache)
```

### Retrieve buy and sell prices
Simple example  
```
import com.dllewellyn.coinbaseapi.Api
import com.dllewellyn.coinbaseapi.cache.MemoryCache
import com.dllewellyn.coinbaseapi.cache.intoCache
import com.dllewellyn.coinbaseapi.models.CurrencyPair


val cache = MemoryCache<CurrencyPair>()

Api.currencyPairs()
    .getCurrencyPairs()
    .intoCache(cache)
    .blockingGet()

val buyAndSell = Api.buyAndSellPrices()

println(
    buyAndSell.getCurrencyBuyPrice(
        CurrencyPair(
            "BTC",
           "0",
            "0",
            "BTC-USD",
            "USD",
            "0"
        )
    ).blockingGet())
```
This is a more involved example, but we retrieve valid pairs, store them into a cache
then iterate each currency pair, requesting the buy and sell prices
then loop through the results and print them out

```
import com.dllewellyn.coinbaseapi.Api
import com.dllewellyn.coinbaseapi.cache.MemoryCache
import com.dllewellyn.coinbaseapi.cache.intoCache
import com.dllewellyn.coinbaseapi.models.CurrencyPair


val cache = MemoryCache<CurrencyPair>()

Api.currencyPairs()
    .getCurrencyPairs()
    .intoCache(cache)
    .blockingGet()


val buyAndSell = Api.buyAndSellPrices()

cache.listOfCurrencies().forEach {
    try {
        println(
            Pair(
                buyAndSell.getCurrencyBuyPrice(it).blockingGet(),
                buyAndSell.getCurrencySellPrice(it).blockingGet()
            )
        )
    } catch (exception : Exception) {}
}
```

## Web sockets

Simple usage

```
   Api.subscription().subscribeToEvent(
       Channel.Type2().only(),
       currency("BTC", "USD")
   )
   .doAfterNext { println(it) }
   .subscribeOn(Schedulers.io())
   .observeOn(Schedulers.io())
   .subscribe()
```

***
The websocket classes return a sealed class defined as:

```
sealed class EventResponse {
    data class Level2Snapshot(val buyAndSell: CurrencyBuyAndSell) : EventResponse()
    data class Level2Update(val buyAndSell: CurrencyValue, val buyOrSell: BuyOrSell) : EventResponse()
}
```

I would recommend an exhaustive when statement; future updates will increase the numer of potential responses

To unsubscribe, do the inverse of what you did before

```
    Api.subscription().subscribeToEvent(
        Channel.Type2().only(),
        currency("BTC", "USD")
    )
        .toFlowable(BackpressureStrategy.LATEST)
        .doAfterNext { println(it) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe()

    Thread.sleep(1000 * 5)
    println("Going to unsubscribe")
    Api.subscription().unsubscribeToEvent(
        Channel.Type2().only(),
        currency("BTC", "USD")
    )

    readLine()
```

## Authenticated API

Below is a list of calls you can make when you have generated an API key. To do so go here

[Link to Coinbase api](https://pro.coinbase.com/profile/api)

And generate your keys. You can use these keys to generate an API object to use the authenticated requests. Example:

```
val api = authenticated_builder {
    apiKey = "key"
    password = "password"
    secretKey = "blahblah=="
    sandbox = true // Optional field
}.build()
```

### Get accounts

Get all accounts

```
api
    .accounts()
    .getAccounts()
    .doAfterSuccess { println(it) }
    .doOnError { println(it) }
    .blockingGet()
```

Get all accounts that have a non-zero blanace

```
api
```