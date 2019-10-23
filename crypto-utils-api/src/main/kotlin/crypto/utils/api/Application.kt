package crypto.utils.api

import crypto.utils.api.auth.FirebaseUtil
import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.servers.Server

@OpenAPIDefinition(
    info = Info(
        title = "Denarii - Cryptocurrency utils",
        version = "1.0",
        description = "",
        license = License(name = "Apache 2.0", url = "https://coinbaseliveexchange.appspot.com")
    ), servers = [
        Server(
            description = "Live server",
            url = "https://coinbaseliveexchange.appspot.com"
        )]

)
object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("crypto.utils.api")
            .mainClass(Application.javaClass)
            .start()
    }
}