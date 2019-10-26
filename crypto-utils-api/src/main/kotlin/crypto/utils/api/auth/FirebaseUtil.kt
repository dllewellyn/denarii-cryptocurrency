package crypto.utils.api.auth

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import io.micronaut.core.io.ResourceLoader
import io.micronaut.core.io.ResourceResolver
import io.micronaut.core.io.scan.ClassPathResourceLoader
import javax.inject.Singleton

fun Firestore.docForUser(uid: String) =
    collection("users")
        .document(uid)

fun Firestore.dataForUser(uid: String) =
    docForUser(uid)
        .get()
        .get()
        .data

object FirebaseUtil {

    private val loader: ResourceLoader = ResourceResolver().getLoader(ClassPathResourceLoader::class.java).get();

    private var initialised: Boolean = false

    private val options: FirebaseOptions? = FirebaseOptions.Builder()
        .setCredentials(
            GoogleCredentials.fromStream(
                loader.getResourceAsStream("static/firebase-admin.json")
                    .get()
            )
        )
        .build()

    fun initialise() {
        if (initialised.not()) {
            FirebaseApp.initializeApp(options)
            initialised = true
        }
    }

    fun fireStore(): Firestore = FirestoreClient.getFirestore()

}