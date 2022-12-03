import android.app.Activity
import android.util.Log
import com.example.aether.HomeFragment
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import java.io.IOException

fun getPicture(activity: Activity, callback: (url: String) -> Unit) {
    val queryUrl =
        "https://api.nasa.gov/planetary/apod?api_key=B4YYn2gIDEHMpsVS35Pam40CHktjee9oj3ZbJuEN"


    val client = OkHttpClient()
    val request = Request.Builder().url(queryUrl).get().build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body

                val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

                val queryAdapter = moshi.adapter(QueryPic::class.java)

                val url = queryAdapter.fromJson(response.body?.string()!!)!!.url

                activity.runOnUiThread {
                    callback(url)
                }


            }
        }

    })
}

fun getInfo(activity: Activity, attribute: String, callback: (url: String) -> Unit) {
    val queryUrl =
        "https://api.nasa.gov/planetary/apod?api_key=B4YYn2gIDEHMpsVS35Pam40CHktjee9oj3ZbJuEN"

    val client = OkHttpClient()
    val request = Request.Builder().url(queryUrl).get().build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body

                val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

                val queryAdapter = moshi.adapter(QueryPic::class.java)
                if (attribute == "title") {
                    val url = queryAdapter.fromJson(response.body?.string()!!)!!.title

                    activity.runOnUiThread {
                        callback(url)
                    }
                } else if (attribute == "explanation") {
                    val url = queryAdapter.fromJson(response.body?.string()!!)!!.explanation
                    activity.runOnUiThread {
                        callback(url)
                    }
                } else if (attribute == "url") {
                    val url = queryAdapter.fromJson(response.body?.string()!!)!!.url
                    activity.runOnUiThread {
                        callback(url)
                    }
                }

            }
        }

    })
}

fun getNews(activity: Activity, callback: (url: List<ArticleQueryResult>) -> Unit) {
    val queryUrl =
        "https://newsapi.org/v2/everything?q=space+nasa&from=2022-11-20&to=2022-12-01&sortBy=popularity&apiKey=9ff6040e433d46029fb15faf9ea48963"
    val client = OkHttpClient()
    val request = Request.Builder().url(queryUrl).get().build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body

                val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

                val queryAdapter = moshi.adapter(QueryNews::class.java)

                val url = queryAdapter.fromJson(response.body?.source())!!.articles

                activity.runOnUiThread {
                    callback(url)
                }


            }
        }

    })
}

fun getNewsJWST(activity: Activity, callback: (url: List<ArticleQueryResult>) -> Unit) {

    val queryUrl =
        "https://newsapi.org/v2/everything?q=%22James%20Webb%20Space%20Telescope%22&from=2022-11-20&to=2022-12-01&sortBy=popularity&apiKey=9ff6040e433d46029fb15faf9ea48963"
    val client = OkHttpClient()
    val request = Request.Builder().url(queryUrl).get().build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body

                val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

                val queryAdapter = moshi.adapter(QueryNews::class.java)

                val url = queryAdapter.fromJson(response.body?.source())!!.articles

                activity.runOnUiThread {
                    callback(url)
                }


            }
        }

    })
}