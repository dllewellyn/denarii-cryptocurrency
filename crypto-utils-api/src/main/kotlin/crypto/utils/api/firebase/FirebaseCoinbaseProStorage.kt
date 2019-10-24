package crypto.utils.api.firebase

import com.dllewellyn.coinbaseapi.api.models.ApiKeyAuth
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepositoryArgument
import com.dllewellyn.coinbaseapi.repositories.WriteRepositoryArgument
import com.google.firebase.cloud.FirestoreClient
import crypto.utils.api.auth.dataForUser
import crypto.utils.api.auth.docForUser
import javax.inject.Singleton

@Singleton
class FirebaseCoinbaseProStorage :
    ReadOnlyRepositoryArgument<String, ApiKeyAuth?>,
    WriteRepositoryArgument<String, ApiKeyAuth> {

    private val firestore = FirestoreClient.getFirestore()

    override suspend fun retrieveData(arg: String): ApiKeyAuth? {
        val data = firestore
            .dataForUser(arg)
        val coinbaseData = data?.get("coinbasepro") as? Map<String, Any>
        return coinbaseData?.let {
            if (coinbaseData.containsKey("secretKey") && coinbaseData.containsKey("apiKey") && coinbaseData.containsKey(
                    "password"
                )
            ) {
                ApiKeyAuth(
                    coinbaseData["secretKey"] as String,
                    coinbaseData["apiKey"] as String,
                    coinbaseData["password"] as String
                )
            } else {
                null
            }
        }
    }

    override suspend fun write(value: ApiKeyAuth, arg: String) {
        with(firestore.docForUser(arg)) {
            firestore.dataForUser(arg)
                ?.let {
                    it.set("coinbasepro", value.convert())
                    update(it)
                } ?: create(mutableMapOf("coinbasepro" to value.convert()))
        }
    }
}