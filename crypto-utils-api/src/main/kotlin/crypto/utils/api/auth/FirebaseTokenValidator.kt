package crypto.utils.api.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.token.validator.TokenValidator
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Singleton


@Singleton
class FirebaseTokenValidator : TokenValidator {

    override fun validateToken(token: String): Publisher<Authentication> {
        FirebaseUtil.initialise()
        return try {
            val firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token)
            Flowable.just(FirebaseAuthenticationInternal(firebaseToken))
        } catch (ex: FirebaseAuthException) {
            Flowable.empty()
        }
    }
}