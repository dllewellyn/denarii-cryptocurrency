package crypto.utils.api.firebase

import com.dllewellyn.coinbaseapi.models.OauthProvider
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepositoryArgument
import com.dllewellyn.coinbaseapi.repositories.WriteRepository
import com.google.firebase.cloud.FirestoreClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import crypto.utils.api.auth.dataForUser
import crypto.utils.api.auth.docForUser
import crypto.utils.api.oauth.OauthWrapper
import javax.inject.Singleton

fun <I, O> I.convert(): O {
    val gson = Gson()
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {}.type)
}

@Singleton
class FirebaseCoinbaseStorage : WriteRepository<OauthWrapper>, ReadOnlyRepositoryArgument<String, OauthProvider> {


    private val firestore = FirestoreClient.getFirestore()

    fun <T> T.serializeToMap(): Map<String, Any> {
        return convert()
    }

    fun <T> Map<String, Any>.toDataClass(): T {
        return convert()
    }


    override suspend fun write(value: OauthWrapper) {
        with(firestore.docForUser(value.user)) {
            firestore.dataForUser(value.user)
                ?.let {
                    it.set("coinbase", value.oauth.convert())
                    update(it)
                } ?: create(mutableMapOf("coinbase" to value.oauth.convert()))
        }
    }

    override suspend fun retrieveData(arg: String): OauthProvider {
        val data = firestore
            .collection("users")
            .document(arg)
            .get()
            .get()

        val coinbaseData = data?.data?.get("coinbase") as? Map<String, Any>
        return coinbaseData?.let {
            OauthProvider(
                coinbaseData["access_token"] as String,
                coinbaseData["created_at"] as Double,
                coinbaseData["expires_in"] as Double,
                coinbaseData["refresh_token"] as String,
                coinbaseData["scope"] as String,
                coinbaseData["token_type"] as String
            )
        } ?: throw IllegalArgumentException()
    }

}