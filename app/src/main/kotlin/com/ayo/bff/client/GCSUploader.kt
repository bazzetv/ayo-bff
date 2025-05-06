import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.net.URI

object GCSUploader {
    private const val BUCKET_NAME = "terra-ai-bucket"
    private const val MINIO_ENDPOINT = "http://localhost:9000"
    private val ACCESS_KEY = System.getenv("MINIO_ACCESS_KEY") ?: "admin"
    private val SECRET_KEY = System.getenv("MINIO_SECRET_KEY") ?: "admin123"

    // 🚀 Connexion à MinIO avec AWS SDK
    private val s3Client: S3Client = S3Client.builder()
        .region(Region.US_EAST_1) // 🚨 Région fictive (MinIO ne gère pas les régions, mais c'est obligatoire)
        .endpointOverride(URI.create(MINIO_ENDPOINT))
        .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)))
        .forcePathStyle(true) // ✅ Obligatoire pour MinIO
        .build()

    private val client = HttpClient(CIO)

    suspend fun uploadFromUrl(imageUrl: String, destinationPath: String): String {
        return try {
            println("📥 Téléchargement de l'image depuis Replicate: $imageUrl")
            val imageBytes: ByteArray = client.get(imageUrl).body()

            println("🚀 Upload vers MinIO: $destinationPath")
            val request = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(destinationPath)
                .build()

            s3Client.putObject(request, RequestBody.fromBytes(imageBytes))

            val publicUrl = "$MINIO_ENDPOINT/$BUCKET_NAME/$destinationPath"
            println("✅ Upload réussi: $publicUrl")

            publicUrl
        } catch (e: Exception) {
            println("❌ Erreur d'upload sur MinIO: ${e.message}")
            throw e
        }
    }
}