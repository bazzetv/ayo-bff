import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

class ReplicateClient(apiPath: String) {
    private val client = HttpClient()
    private val apiKey: String = System.getenv("REPLICATE_API_KEY") ?: throw IllegalStateException("Missing REPLICATE_API_KEY env variable")
    private val baseUrl = "https://api.replicate.com/" + apiPath
    private val callbackUrl = "https://4306-2a01-e0a-da9-e630-20db-def9-cb0a-12e2.ngrok-free.app/webhook/replicate/generation"

    suspend fun generateImage(parameters: JsonElement, version: String? = null): ReplicateResponse {
        val jsonParameters = if (parameters is JsonObject) parameters
        else throw IllegalArgumentException("Les paramètres doivent être un JsonObject, reçu : ${parameters::class}")

        val requestBody = ReplicateRequest(
            input = jsonParameters,
            webhook = callbackUrl,
            version = version
        )

        val response: HttpResponse = client.post(baseUrl) {
            header(HttpHeaders.Authorization, "Bearer $apiKey")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody(Json.encodeToString(requestBody))
        }

        return Json.decodeFromString(response.bodyAsText())
    }
}

@Serializable
data class ReplicateRequest(
    val version: String? = null,
    val input: Map<String, JsonElement>,
    val webhook: String
)

@Serializable
data class ReplicateResponse(
    val id: String,
    val model: String,
    val version: String?,
    val input: Map<String, JsonElement>,
    val logs: String?,
    val output: List<String>?,
    val data_removed: Boolean,
    val error: String?,
    val status: String,
    val created_at: String,
    val urls: ReplicateUrls
)

@Serializable
data class ReplicateUrls(
    val cancel: String,
    val get: String,
    val stream: String
)