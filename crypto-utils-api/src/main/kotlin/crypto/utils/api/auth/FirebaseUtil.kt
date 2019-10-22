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

object FirebaseUtil {

    private val loader: ResourceLoader = ResourceResolver().getLoader(ClassPathResourceLoader::class.java).get();

    private val options: FirebaseOptions? = FirebaseOptions.Builder()
        .setCredentials(
            GoogleCredentials.fromStream(
                loader.getResourceAsStream("static/firebase-admin.json")
                    .get()
            )
        )
        .build()

    fun initialise(): FirebaseApp = FirebaseApp.initializeApp(options)
    fun fireStore(): Firestore = FirestoreClient.getFirestore()

}