import android.app.Activity
import com.example.aether.HomeFragment
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import java.io.IOException

fun getPicture(activity: Activity, callback: (url:String) -> Unit) {
    val queryUrl = "https://api.nasa.gov/planetary/apod?api_key=B4YYn2gIDEHMpsVS35Pam40CHktjee9oj3ZbJuEN"


    val client = OkHttpClient()
    val request = Request.Builder()
        .url(queryUrl)
        .get()
        .build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body

                val moshi: Moshi = Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()

                val queryAdapter = moshi.adapter(QueryPic::class.java)

                val url = queryAdapter.fromJson(response.body?.string()!!)!!.url

                activity.runOnUiThread{
                    callback(url)
                }


            }
        }

    }
    )
}

fun getInfo(activity: Activity, callback: (url:String) -> Unit) {
    val queryUrl = "https://api.nasa.gov/planetary/apod?api_key=B4YYn2gIDEHMpsVS35Pam40CHktjee9oj3ZbJuEN"


    val client = OkHttpClient()
    val request = Request.Builder()
        .url(queryUrl)
        .get()
        .build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body

                val moshi: Moshi = Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()

                val queryAdapter = moshi.adapter(QueryPic::class.java)

                val url = queryAdapter.fromJson(response.body?.string()!!)!!.title

                activity.runOnUiThread{
                    callback(url)
                }


            }
        }

    }
    )
}
//fun postNotes(title: String, body : String, poster : String) {
//    val client = OkHttpClient()
//
//
//    val moshi: Moshi = Moshi.Builder().build()
//    val adapter: JsonAdapter<AddedNotes> =
//        moshi.adapter(AddedNotes::class.java)
//    val json: String = adapter.toJson(AddedNotes(title, body, poster))
//
//    val therequestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
//
//    val postRequest = Request.Builder()
//        .post(therequestBody)
//        .url("http://143.198.115.54:8080/posts/")
//        .build()
//
//
//    client.newCall(postRequest).enqueue(object : okhttp3.Callback {
//        override fun onFailure(call: okhttp3.Call, e: IOException) {
//            e.printStackTrace()
//        }
//
//        override fun onResponse(call: okhttp3.Call, response: Response) {
//            if (!response.isSuccessful) {
//                // Can also respond with some UI saying check internet, try again, network request failed,
//                // etc. as opposed to exception (which will crash your app unless caught)!
//                throw IOException("Unexpected code $response")
//            } else {
//                //val body = response.body
//
//                //val addedNote = adapter.fromJson(body!!.source())
//
//            }
//        }
//    })
