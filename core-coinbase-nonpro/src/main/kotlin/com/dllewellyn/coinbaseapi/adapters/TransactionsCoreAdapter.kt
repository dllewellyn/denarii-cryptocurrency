package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.models.ApiNativeAmount
import com.dllewellyn.coinbaseapi.models.ApiTransaction
import com.dllewellyn.coinbaseapi.models.BaseResponseApi
import com.dllewellyn.coinbaseapi.models.account.Transaction
import com.dllewellyn.coinbaseapi.nonpro.interfaces.Prices
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText

class TransactionsCoreAdapter(private val client: InternalHttpClient, private val prices: Prices? = null) :
    ReadOnlyRepositoryArgument<String, List<Transaction>> {

    @ExperimentalUnsignedTypes
    override suspend fun retrieveData(arg: String) = client.httpClient.get<HttpResponse>(
        client.url("accounts/$arg/transactions")
    )
        .readText()
        .let {
            client.json.parse(BaseResponseApi.serializer(ApiTransaction.serializer()), it)
        }
        .data
        .map {
            Transaction(
                amount = it.amount.amount,
                date = it.created_at,
                description = it.description,
                id = it.id,
                status = it.status,
                type = it.type,
                nativeAmount = it.native_amount.amount,
                nativeCurrency = it.native_amount.currency,
                dollarValue = if (it.native_amount.currency == "USD") {
                    it.native_amount.amount
                } else {
                    tryToGetDollarAmount(it.native_amount)
                }
            )
        }

    private suspend fun tryToGetDollarAmount(apiNativeAmount: ApiNativeAmount) =
        try {
            prices?.getSpotPrice(CurrencyPair.fromId("${apiNativeAmount.currency}-USD"))?.amount?.multiply(
                BigDecimal.parseString(apiNativeAmount.amount)
            )?.toStringExpanded()
        } catch (exception: Exception) {
            null // Not worth failing over this nullable value
        }
}