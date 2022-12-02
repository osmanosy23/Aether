package com.example.aether

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MySingleton constructor(context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: MySingleton? = null

    }


    fun <T> addToRequestQueue(req: Request<T>) {

    }
}