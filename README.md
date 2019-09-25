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
    def coinbaseApiVersion = "1.3.10"
    // Core
    implementation "com.dllewellyn.coinbaseAPI:coinbase-api-core:${coinbaseApiVersion}"
    
    // If you want to use rxjava 
    implementation "com.dllewellyn.coinbaseAPI:coinbase-api-rxadapter:${coinbaseApiVersion}"
    
    // If you want to use coroutines
    implementation "com.dllewellyn.coinbaseAPI:coinbase-api-coroutines-adapter:${coinbaseApiVersion}"
    
}
```
[More info](https://bintray.com/dllewellyn/coinbase-api-kt/coinbase-api-kt)
# Usage

## Sandbox

By default, the real coinbase API is used. To use the sandbox
at some point before first using the API call

Note: There is a RetrofitApi object for coroutines and a retofit API for rxjava.. make sure you use the right one :)
```
RetrofitApi.sandbox = true
```
## Exceptions

The coinbase API can give fairly useful responses. These are encapsulated in an 'ApiException'
will contain the error message from the server. Example response:

```
ApiException: {"message":"Insufficient funds"}
```

## RxJava or Coroutines

For all API calls there is a coroutines or an Rxjava option. They work in roughly the same way. Examples might be given using either or both 
but the classes and functions used should be roughly the same. 

These are the interfaces to look out for:

```
// Users account information (each account is for each currency. E.g there is a BTC account, a GBP account etc)
Accounts

// List of supported currencies
CurrencyList

// Buy and sell prices / order book
CurrencyPrice

// Manage a users orders
Orders

// ExchangeRateRetriever
ExchangeRateRetriever
```

To take an example the orders list we would use it like this in rx:

```
api.
    .orders()
    .retrieveOpenOrders()
    .subscribe(::println)
```

For coroutines

```
GlobalScope.launch {
   println(api.retrieveOpenOrders())
}
```

### Getting the handlers

The interfaces mentioned above can be retrieved from the ``RetrofitApi`` class

E.g.

```
RetrofitApi.orders()
RetrofitApi.currencies() 
RetrofitApi.exchangeRates() 
RetrofitApi.currencyPairs() 
RetrofitApi.buyAndSellPrices() 
RetrofitApi.subscription() 
```

For any commands that needs authentication, use the authenticated_builder

[Link to Coinbase api](https://pro.coinbase.com/profile/api)

And generate your keys. You can use these keys to generate an API object to use the authenticated requests. Example:

```
val api = authenticated_builder {
    apiKey = "key"
    password = "password"
    secretKey = "blahblah=="
    sandbox = true // Optional field
}.build()

api.orders()
api.accounts()
```


## CurrencyList

(Unauthenticated)

List currencies as an observable, emitting each
cryptoCurrency one at a time

```
import com.dllewellyn.coinbaseapi.RetrofitApi

RetrofitApi.currencies().getCurrencies()
    .doAfterNext { println(it) }
    .blockingLast()
```

List currencies as one list of all currencies

```
import com.dllewellyn.coinbaseapi.RetrofitApi

RetrofitApi.currencies().getCurrencyList()
    .blockingGet()
```

## ExchangeRates

List of exchange rates 
```
import com.dllewellyn.coinbaseapi.RetrofitApi
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

## Orders

To retrieve the order book

```
 Api.buyAndSellPrices()
        .getProductOrderBook(pair, OrderBookLevel.LEVEL_2)
        .blockingGet()
        .let {
            println(it)
        }       
 ```
 
## Currency pairs

Retrieve currency pairs - i.e the pairs that coinbase supports

```
import com.dllewellyn.coinbaseapi.RetrofitApi

val result = Api.currencyPairs()
    .getCurrencyPairs()
    .blockingGet()

println(result)
```

Retrieve as observable

```
import com.dllewellyn.coinbaseapi.RetrofitApi

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

### Retrieve buy and sell prices

Simple example  
```
import com.dllewellyn.coinbaseapi.RetrofitApi
import com.dllewellyn.coinbaseapi.cache.MemoryCache
import com.dllewellyn.coinbaseapi.cache.intoCache
import com.dllewellyn.coinbaseapi.models.CurrencyPair


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
import com.dllewellyn.coinbaseapi.RetrofitApi
import com.dllewellyn.coinbaseapi.cache.MemoryCache
import com.dllewellyn.coinbaseapi.cache.intoCache
import com.dllewellyn.coinbaseapi.models.CurrencyPair


val cache = Api.currencyPairs()
    .getCurrencyPairs()
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

### Orders

Get all open orders

```
api.
    .orders()
    .retrieveOpenOrders()
    .subscribe(::println)
```

Delete a specific order

```
api
    .orders()
    .deleteOrder("abc")
```

Delete all orders

```
api
    .orders()
    .deleteAll()
```

### Sample application

A sample of how to keep an up-to-date book is detailed below:

(Highly experimental, use at your own risk)

```
val buys = mutableListOf<EventResponse.Level2Update>()
val sell = mutableListOf<EventResponse.Level2Update>()

fun main() {

    //Api.sandbox = true

    Api.sandbox = true
    Api.subscription().subscribeToEvent(
        Channel.Type2().only(),
        CurrencyPair.fromId("BTC-USD"),
        CurrencyPair.fromId("ETH-BTC")
    )
        .doAfterNext {
            if (it is EventResponse.Level2Snapshot) {
                buys.add(
                    EventResponse.Level2Update(
                        buyAndSell = CurrencyValue(
                            it.buyAndSell.currencyFrom,
                            it.buyAndSell.currencyTo,
                            it.buyAndSell.buy
                        ),
                        buyOrSell = BuyOrSell.BUY,
                        size = 0.0
                    )
                )

                sell.add(
                    EventResponse.Level2Update(
                        buyAndSell = CurrencyValue(
                            it.buyAndSell.currencyFrom,
                            it.buyAndSell.currencyTo,
                            it.buyAndSell.sell
                        ),
                        buyOrSell = BuyOrSell.SELL,
                        size = 0.0
                    )
                )
            }

            if (it is EventResponse.Level2Update) {
                if (it.buyOrSell == BuyOrSell.BUY) {
                    if (it.size == 0.0) {
                        buys.filter { amnt -> it.buyAndSell.amount == amnt.buyAndSell.amount }
                            .forEach { a -> buys.remove(a) }
                    } else {
                        buys.add(it)
                    }
                } else {
                    if (it.size == 0.0) {
                        sell.filter { amnt -> it.buyAndSell.amount == amnt.buyAndSell.amount }
                            .forEach { a -> sell.remove(a) }
                    } else {
                        sell.add(it)
                    }
                }
            }

            println(it)
            if (buys.isNotEmpty() && sell.isNotEmpty()) {
                println("***")
                buys.sortByDescending { amount -> amount.buyAndSell.amount }
                println(buys.first())
                sell.sortBy { amount -> amount.buyAndSell.amount }
                println(sell.first())
                println("Spread: ${sell.first().buyAndSell.amount - buys.first().buyAndSell.amount}")
            }
        }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe()

    readLine()
}
```