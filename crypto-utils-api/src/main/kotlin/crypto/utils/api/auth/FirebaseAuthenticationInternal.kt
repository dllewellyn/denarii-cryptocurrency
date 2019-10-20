package crypto.utils.api.auth

import com.google.firebase.auth.FirebaseToken
import io.micronaut.security.authentication.Authentication

class FirebaseAuthenticationInternal(private val firebaseToken: FirebaseToken) : Authentication {
    override fun getName() = firebaseToken.uid
    override fun getAttributes() = firebaseToken.claims
}