package crypto.utils.api

import com.dllewellyn.coinbaseapi.RetrofitCoinbaseProApi
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import com.dllewellyn.coinbaseapi.utils.StatisticsCalculator
import com.google.gson.Gson
import io.micronaut.core.io.ResourceLoader
import io.micronaut.core.io.ResourceResolver
import io.micronaut.core.io.scan.ClassPathResourceLoader
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Produces
import io.swagger.v3.oas.annotations.Operation
import kotlinx.coroutines.runBlocking
import javax.annotation.security.PermitAll


data class Pairs(val id: String, val code: String)
@Controller("/currencies")
class ApiController {
    val loader: ResourceLoader = ResourceResolver().getLoader(ClassPathResourceLoader::class.java).get();

    private val gson: Gson by lazy {
        Gson()
    }

    private val statistics by lazy {
        StatisticsCalculator(RetrofitCoinbaseProApi.productTicker(), RetrofitCoinbaseProApi.twentyFourHours())
    }
    private val dataFromFile: String by lazy {
        loader.getResourceAsStream("static/all_currencies.json")
            .get().bufferedReader().use { it.readText() }
    }

    private val mp = mutableMapOf<String, String>().apply {
        putAll(gson.fromJson(dataFromFile, Map::class.java) as Map<String, String>)
    }

    @Get("/image/{id}")
    @PermitAll
    @Operation(summary = "Get an image", operationId = "getImage", description = "Get an image for a given cryptocurrency short code. E.g BTC will return the bitcoin icon", tags=[])
    @Produces("image/svg+xml")
    fun getImage(@PathVariable("id") name: String) =
        loader.getResourceAsStream("static/images/${name.toLowerCase()}.svg")
            .get().readBytes()

    @Operation(summary = "Get all names", operationId = "allNames", description = "Get all names from a short code. E.g BTC will return Bitcoin", tags=[])
    @Get("/all")
    @PermitAll
    fun pairs() = mp.map {
        Pairs(it.key, it.value)
    }

    @Operation(summary = "Get the name from a short code", operationId = "getName", description = "Get the name from a short code. E.g BTC will return Bitcoin", tags=[])
    @Get("/name/{id}")
    @PermitAll
    fun getBuyName(@PathVariable("id") name: String) =
        if (mp.containsKey(name.toUpperCase())) {
            mapOf("value" to mp[name.toUpperCase()])
        } else {
            throw NotFoundException()
        }

    @Operation(summary = "Get cryptocurrency statistics", operationId = "getStats", description = "Retrieve cryptocurrency statistics", tags=[])
    @Get("/stats/{id}")
    @PermitAll
    fun getStats(@PathVariable("id") id: String) = runBlocking {
        statistics.createStatisticsForCurrency(SupportedCurrency(id))
    }

}