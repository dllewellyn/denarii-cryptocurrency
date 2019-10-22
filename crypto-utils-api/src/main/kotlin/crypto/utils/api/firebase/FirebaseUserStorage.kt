package crypto.utils.api.firebase

import com.dllewellyn.coinbaseapi.repositories.WriteRepository
import com.google.firebase.cloud.FirestoreClient
import crypto.utils.api.oauth.OauthWrapper
import javax.inject.Singleton

@Singleton
class FirebaseUserStorage : WriteRepository<OauthWrapper> {

    override suspend fun write(value: OauthWrapper) {
        val document = FirestoreClient.getFirestore()
            .collection("users")
            .document(value.user)
        with(document) {
            get()
                .get()
                .data
                ?.let {
                    it.set("coinbase", value.oauth)
                } ?: mutableMapOf("coinbase" to value.oauth)
        }


    }
}