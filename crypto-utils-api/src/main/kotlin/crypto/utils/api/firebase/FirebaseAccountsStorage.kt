package crypto.utils.api.firebase

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.repositories.WriteRepositoryArgument
import com.google.firebase.cloud.FirestoreClient
import crypto.utils.api.auth.dataForUser
import crypto.utils.api.auth.docForUser
import javax.inject.Singleton

@Singleton
class FirebaseAccountsStorage :
    WriteRepositoryArgument<String, List<Account>> {
    private val firestore = FirestoreClient.getFirestore()

    override suspend fun write(value: List<Account>, arg: String) {
        with(firestore.docForUser(arg)) {
            firestore.dataForUser(arg)
                ?.let {
                    it.set("accounts", value.convert())
                    update(it)
                } ?: create(mutableMapOf("accounts" to value.convert()))
        }
    }
}