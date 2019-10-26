# Coinbase API

The API broadly works using the ReadOnlyRepository interfaces which are defined in core.

## Unauthenticated calls

In order to call endpoints which don't need any authentication, you can use use 

```
val coinbase = CoinbaseApi()

coinbase.exchangeRateRetriever()
coinbase.currencyList()
coinbase.prices()
```

## Authenticated calls

You can either use an API key genereated, or an OAuth generated key

```OauthCoinbaseApi``` take an ```OauthProvider``` whereas
```ApikeyCoinbaseApi``` takes a secret key and api key

Whichever you use you can call through to ```acounts``` for account information or ```transactions``` to get transactions for a 
specific account.

