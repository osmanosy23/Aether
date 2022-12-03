import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QueryNews(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleQueryResult>

)

@JsonClass(generateAdapter = true)
data class ArticleQueryResult(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)
