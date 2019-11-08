package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.models.BaseResponseApiSingle
import com.dllewellyn.coinbaseapi.models.UserData
import com.dllewellyn.coinbaseapi.nonpro.interfaces.UserProfile
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText

class UserProfileAdapter(val client: InternalHttpClient) : UserProfile {

    override suspend fun retrieveData() =
        client.httpClient.get<HttpResponse>(client.url("user"))
            .readText()
            .let {
                client.json.parse(BaseResponseApiSingle.serializer(UserData.serializer()), it)
                    .data
            }
}