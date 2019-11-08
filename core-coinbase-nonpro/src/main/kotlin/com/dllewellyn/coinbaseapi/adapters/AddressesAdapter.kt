package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.models.AccountData
import com.dllewellyn.coinbaseapi.models.AddressApi
import com.dllewellyn.coinbaseapi.models.BaseResponseApi
import com.dllewellyn.coinbaseapi.models.CreateAddressApi
import com.dllewellyn.coinbaseapi.nonpro.interfaces.Addresses
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent

class AddressesAdapter(private val client: InternalHttpClient) : Addresses {


    override suspend fun retrieveData(arg: AccountData) =
        client.httpClient.get<HttpResponse>(client.url("accounts/${arg.id}/addresses"))
            .readText()
            .let {
                client.json.parse(BaseResponseApi.serializer(AddressApi.serializer()), it)
            }
            .data

    override suspend fun write(value: CreateAddressApi, arg: AccountData) {
        client.httpClient.post<CreateAddressApi>(client.url("accounts/${arg.id}/addresses")) {
            body = TextContent(
                client.json.toJson(CreateAddressApi.serializer(), value).toString(),
                ContentType.Application.Json
            )
        }
    }
}