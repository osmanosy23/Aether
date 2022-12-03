package com.example.aether

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import getInfo
import getPicture


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val picofday = view.findViewById<ImageView>(R.id.myImageView)
        val pictitle: TextView = view.findViewById(R.id.titleTextView)

        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { results ->

        }


        picofday.setOnClickListener {

            val intent = Intent(context, PicofDayActivity::class.java)
            intent.putExtra("title", pictitle.text)

            intentLauncher.launch(intent)

        }

        activity?.let {
            getPicture(it) { myUrlArgument ->
                try {
                    Glide
                        .with(this)
                        .load(myUrlArgument)
                        .centerCrop()
                        .placeholder(R.drawable.telescope_vec)
                        .into(picofday)
                } catch (_: Exception) {
                }
            }

        }

        activity?.let {
            getInfo(it, "title") { myUrlArgument ->
                pictitle.text = myUrlArgument
            }
            return view
        }
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String = "", param2: String = "") =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}