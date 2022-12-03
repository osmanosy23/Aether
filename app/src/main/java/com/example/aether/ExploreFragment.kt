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
import getNewsJWST


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ExploreFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myAdapter: NewsAdapter
    val categoryRVModalArrayList: MutableList<CategoryModel> = ArrayList<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { results ->

        }


        val recyclerView: RecyclerView = view.findViewById(R.id.newsRecycler)
        recyclerView.adapter =
            activity?.let { NewsAdapter(mutableListOf(), requireActivity(), intentLauncher) }
        recyclerView.layoutManager = LinearLayoutManager(context)

        getNews(requireActivity()) { list ->


            val newsList = list.map {
                News(
                    it.title ?: "",
                    it.author ?: "",
                    it.url ?: "",
                    it.publishedAt ?: "",
                    it.urlToImage ?: "",
                    it.description ?: ""
                )

            }
            (recyclerView.adapter as NewsAdapter).updateData(newsList)
        }
        val intentLauncher2 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { results ->

        }
        val categoryRV: RecyclerView = view.findViewById(R.id.tabRecycler)
        val categoryRVModelArrayList: MutableList<CategoryModel> = ArrayList<CategoryModel>()
        val categoryRVAdapter =
            context?.let { TabAdapter(categoryRVModalArrayList, it, intentLauncher2) }
        categoryRVModelArrayList.add(CategoryModel("Nasa News", (getString(R.string.newspic))))
        categoryRVModelArrayList.add(
            CategoryModel(
                "JWST Updates", (getString(R.string.JWSTpic))
            )
        )
        categoryRVModelArrayList.add(CategoryModel("Nasa Live", (getString(R.string.videospic))))

        categoryRV.adapter = categoryRVAdapter
        categoryRVAdapter?.updateData(categoryRVModelArrayList)


        if (categoryRVAdapter != null) {
            categoryRVAdapter.setOnItemClickListener(object : TabAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {

                    if (position == 0) {
                        val recyclerView: RecyclerView = view.findViewById(R.id.newsRecycler)
                        recyclerView.adapter = activity?.let {
                            NewsAdapter(
                                mutableListOf(), requireActivity(), intentLauncher
                            )
                        }
                        recyclerView.layoutManager = LinearLayoutManager(context)

                        getNews(requireActivity()) { list ->


                            val newsList = list.map {
                                News(
                                    it.title ?: "",
                                    it.author ?: "",
                                    it.url ?: "",
                                    it.publishedAt ?: "",
                                    it.urlToImage ?: "",
                                    it.description ?: ""
                                )

                            }
                            (recyclerView.adapter as NewsAdapter).updateData(newsList)
                        }


                    }
                    if (position == 1) {

                        val recyclerView: RecyclerView = view.findViewById(R.id.newsRecycler)
                        recyclerView.adapter = activity?.let {
                            NewsAdapter(
                                mutableListOf(), requireActivity(), intentLauncher
                            )
                        }
                        recyclerView.layoutManager = LinearLayoutManager(context)

                        getNewsJWST(requireActivity()) { list ->


                            val newsList = list.map {
                                News(
                                    it.title ?: "",
                                    it.author ?: "",
                                    it.url ?: "",
                                    it.publishedAt ?: "",
                                    it.urlToImage ?: "",
                                    it.description ?: ""
                                )

                            }
                            (recyclerView.adapter as NewsAdapter).updateData(newsList)
                        }

                    }
                    if (position == 2) {
                    }
                }


            })
        }



        return view

    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String = "", param2: String = "") = ExploreFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}