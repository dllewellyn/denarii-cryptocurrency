package crypto.utils.api.auth

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.micronaut.core.io.ResourceLoader
import io.micronaut.core.io.ResourceResolver
import io.micronaut.core.io.scan.ClassPathResourceLoader
import javax.inject.Singleton


@Singleton
class FirebaseUtil {

    val loader: ResourceLoader = ResourceResolver().getLoader(ClassPathResourceLoader::class.java).get();

    init {
        val options = FirebaseOptions.Builder()
            .setCredentials(
                GoogleCredentials.fromStream(
                    loader.getResourceAsStream("static/firebase-admin.json")
                        .get()
                )
            )
            .build()

        FirebaseApp.initializeApp(options)
    }
}