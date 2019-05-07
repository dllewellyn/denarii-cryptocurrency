# Usage



## List of currencies

List currencies as an observable, emitting each
currency one at a time

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
import com.dllewellyn.coinbaseapi.models.Currency

val result = Api.exchangeRates()
    .getExchangeRates(Currency.BITCOIN)
    .blockingGet()
println(result)

```

Filter only for the currency you're interested in 

```
val filteredResult = Api.exchangeRates()
    .getExchangeRates(Currency.BITCOIN)
    .filterByCurrency("USD")
    .blockingGet()

println(filteredResult)
```
