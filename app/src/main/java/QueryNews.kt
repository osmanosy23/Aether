import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QueryNews(
    val status : String,
    val totalResults : Int,
    val articles: List<ArticleQueryResult>
//    val author:String,
//    val title:String,
//    val description : String,
//    val url:String,
//    val urlToImage:String,
//    val publishedAt : String,
//    val content : String
)

@JsonClass(generateAdapter = true)
data class ArticleQueryResult(
//    val source: String,
    val author : String?,
    val title : String?,
    val description : String?,
    val url : String?,
    val urlToImage : String?,
    val publishedAt : String?,
    val content : String?
)
