package com.example.aether

import News
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import NewsAdapter
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import getNews

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExploreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myAdapter: NewsAdapter
    val categoryRVModalArrayList : MutableList<CategoryModel> = ArrayList<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){results ->

        }


        val recyclerView: RecyclerView = view.findViewById(R.id.newsRecycler)
        recyclerView.adapter = activity?.let { NewsAdapter(mutableListOf(), requireActivity(), intentLauncher) }
        recyclerView.layoutManager = LinearLayoutManager(context)

        getNews(requireActivity()) { list ->
            Log.d("key12", "erer")
//            val news = News("Gf", "fg", "f", "g")
            Log.d("key14", "erer")

            val newsList = list.map {
//                News(it.title, "Authory McAuthor", it.url, it.urlToImage ?: "")
                News(
                    it.title ?: "",
                    it.author ?: "",
                    it.url ?: "",
                    it.publishedAt ?: "",
                    it.urlToImage ?: "",
                    it.description ?: ""
                )

            }
            (recyclerView.adapter as NewsAdapter).updateData(newsList)  }
        val intentLauncher2 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){results ->

        }
            val categoryRV: RecyclerView = view.findViewById(R.id.tabRecycler)
            val categoryRVModelArrayList: MutableList<CategoryModel> = ArrayList<CategoryModel>()
            val categoryRVAdapter = context?.let { TabAdapter(categoryRVModalArrayList, it,intentLauncher2) }
            categoryRVModelArrayList.add(CategoryModel("News", (getString(R.string.newspic))))
            categoryRVModelArrayList.add(
                CategoryModel(
                    "Podcasts",
                    (getString(R.string.podcastpic))
                )
            )
            categoryRVModelArrayList.add(CategoryModel("Nasa Live", (getString(R.string.videospic))))

            categoryRV.adapter = categoryRVAdapter
            categoryRVAdapter?.updateData(categoryRVModelArrayList)
//        Log.d("keyf100", categoryRV.toString())
        Log.d("keyf100","bruh")

        if (categoryRVAdapter != null) {
            categoryRVAdapter.setOnItemClickListener (object : TabAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    Log.d("keyf100","tappingworks")

                        if(position==0){
                        Log.d("keyf100", "one")
        val recyclerView: RecyclerView = view.findViewById(R.id.newsRecycler)
        recyclerView.adapter = activity?.let { NewsAdapter(mutableListOf(), requireActivity(), intentLauncher) }
        recyclerView.layoutManager = LinearLayoutManager(context)

        getNews(requireActivity()) { list ->
            Log.d("key12", "erer")
//            val news = News("Gf", "fg", "f", "g")
            Log.d("key14", "erer")

            val newsList = list.map {
//                News(it.title, "Authory McAuthor", it.url, it.urlToImage ?: "")
                News(
                    it.title ?: "",
                    it.author ?: "",
                    it.url ?: "",
                    it.publishedAt ?: "",
                    it.urlToImage ?: "",
                    it.description ?: ""
                )

            }
            (recyclerView.adapter as NewsAdapter).updateData(newsList)  }





                    }
                        if(position==1){
                            Log.d("keyf100", "two")
//                            val intentLauncher2 = registerForActivityResult(
//                                ActivityResultContracts.StartActivityForResult()
//                            ){results ->
//
//                            }
//                                Log.d("keyf100","OH MAMA")
//                                val intent = Intent(context, NewYoutubeActivity::class.java)
//
//                                intentLauncher2.launch(intent)

                        }
                        if(position==2){
                            Log.d("keyf100", "three")
                        }
                    }



            })
        }



        return view

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String = "", param2: String = "") =
            ExploreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}