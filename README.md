# Usage



## List of currencies

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

## Exchange rates

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

## Currency pairs

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

## Retrieve buy and sell prices
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