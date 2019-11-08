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

### Accounts

Accounts is an area that requires authentication. So either with OauthCoinbaseApi or ApikeyCoinbaseApi call 


```val accounts = api.accounts()```

You can list accounts

```accounts.getAllAccounts()```

Delete an account

```accounts.delete(account)```

Or update the name of an account

```accounts.update(account, "My new name")```

Set an account as the primary account 

```accounts.setAccountAsPrimary(account)```

##### Addresses

For an account you can get the addresses associated with that account

```val addresses = api.addresses()```

List

```addresses.retrieveData(account)```

Create a new address

 
```
val newAddress CreateAddressApi(name = "new name")
addresses.write(account, newAddress)
``` 

##### UserProfile

```val profiles = api.userProfile()```

Get the currently logged in user profile

```profiles.retrieveData()```