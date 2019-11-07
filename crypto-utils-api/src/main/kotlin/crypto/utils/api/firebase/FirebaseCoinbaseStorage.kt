package crypto.utils.api.firebase

import com.dllewellyn.coinbaseapi.models.OauthProvider
import com.dllewellyn.coinbaseapi.models.toMap
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument
import com.dllewellyn.denarii.repositories.WriteRepositorySingleArgument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import crypto.utils.api.auth.FirebaseUtil
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
class FirebaseCoinbaseStorage : WriteRepositorySingleArgument<OauthWrapper>, ReadOnlyRepositoryArgument<String, OauthProvider?> {


    private val firestore = FirebaseUtil.fireStore()

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
                    it.set("coinbase", value.oauth.toMap())
                    update(it)
                } ?: create(mutableMapOf("coinbase" to value.oauth.toMap()) as Any)
        }
    }

    override suspend fun retrieveData(arg: String): OauthProvider? {
        val data = firestore
            .collection("users")
            .document(arg)
            .get()
            .get()

        val coinbaseData = data?.data?.get("coinbase") as? Map<String, Any>
        return coinbaseData?.let {
            OauthProvider(
                coinbaseData["access_token"] as String,
                coinbaseData["created_at"] as Long,
                coinbaseData["expires_in"] as Long,
                coinbaseData["refresh_token"] as String,
                coinbaseData["scope"] as String,
                coinbaseData["token_type"] as String
            )
        }
    }

}