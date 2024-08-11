package com.sabanbingul.fabriccatalog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sabanbingul.fabriccatalog.R
import com.sabanbingul.fabriccatalog.adapter.FabricAdapter
import com.sabanbingul.fabriccatalog.databinding.FragmentFeedBinding
import com.sabanbingul.fabriccatalog.viewmodel.FeedViewModel
import java.util.ArrayList

class FeedFragment : Fragment() {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel
    private lateinit var fabricAdapter : FabricAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        fabricAdapter = FabricAdapter(arrayListOf())



        val gridLayoutManager = GridLayoutManager(context, 2) // 2 columns
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.adapter = fabricAdapter
        observeLiveData()
        viewModel.loadData()
    }

    private fun observeLiveData(){
        viewModel.fabrics.observe(viewLifecycleOwner, Observer { fabrics ->
            fabrics.let {
                fabricAdapter = FabricAdapter(ArrayList(it))
                binding.recyclerView.adapter = fabricAdapter
                binding.recyclerView.visibility = View.VISIBLE

            }
        })
    }
}