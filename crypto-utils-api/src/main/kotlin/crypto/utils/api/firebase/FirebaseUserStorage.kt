package crypto.utils.api.firebase

import com.dllewellyn.coinbaseapi.repositories.WriteRepository
import com.google.firebase.cloud.FirestoreClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import crypto.utils.api.oauth.OauthWrapper
import javax.inject.Singleton

@Singleton
class FirebaseUserStorage : WriteRepository<OauthWrapper> {

    val gson = Gson()

    fun <T> T.serializeToMap(): Map<String, Any> {
        return convert()
    }

    private inline fun <I, reified O> I.convert(): O {
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<O>() {}.type)
    }

    override suspend fun write(value: OauthWrapper) {
        val firestore = FirestoreClient.getFirestore()
        val document = firestore
            .collection("users")
            .document(value.user)
        with(document) {
            get()
                .get()
                .data
                ?.let {
                    it.set("coinbase", value.oauth.convert())
                    document.update(it)
                } ?: document.create(mutableMapOf("coinbase" to value.oauth.convert()))
        }


    }
}