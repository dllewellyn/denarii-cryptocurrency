package crypto.utils.api

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("crypto.utils.api")
                .mainClass(Application.javaClass)
                .start()
    }
}