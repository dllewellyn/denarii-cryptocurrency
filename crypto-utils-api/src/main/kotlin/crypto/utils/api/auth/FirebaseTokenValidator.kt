package crypto.utils.api.auth

import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import io.micronaut.security.authentication.Authentication
import org.reactivestreams.Publisher
import io.micronaut.security.token.validator.TokenValidator
import io.reactivex.Flowable
import javax.inject.Singleton


@Singleton
class FirebaseTokenValidator : TokenValidator {

    override fun validateToken(token: String): Publisher<Authentication> {
        return try {
            val firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token)
            Flowable.just(FirebaseAuthenticationInternal(firebaseToken))
        } catch (ex: FirebaseAuthException) {
            Flowable.empty()
        }
    }
}