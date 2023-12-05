import com.example.app.presentations.Product.Product
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path


// Interface que define os endpoints da API
interface ProductService {
    @GET("/api/products")
    suspend fun getProducts(): List<GridProductItem>

    @GET("/api/products/{productId}")
    suspend fun getProductById(@Path("productId") productId: String): Product
}

// Objeto de configuração do Retrofit
object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.66:8080"

    // instância do Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

// Função para buscar os produtos da API usando Retrofit
suspend fun getProductsFromAPI(): List<GridProductItem> {
    val productService = RetrofitClient.retrofit.create(ProductService::class.java)
    return productService.getProducts()
}

// Função para buscar um único produto da API usando Retrofit
suspend fun getProductByIdFromAPI(productId: String): Product {
    val productService = RetrofitClient.retrofit.create(ProductService::class.java)
    return productService.getProductById(productId)
}
