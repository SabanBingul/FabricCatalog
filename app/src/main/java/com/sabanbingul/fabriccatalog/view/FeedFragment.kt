package com.sabanbingul.fabriccatalog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sabanbingul.fabriccatalog.R
import com.sabanbingul.fabriccatalog.adapter.FabricAdapter
import com.sabanbingul.fabriccatalog.databinding.FragmentFeedBinding
import com.sabanbingul.fabriccatalog.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel
    private lateinit var fabricAdapter: FabricAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        viewModel.fabricList.observe(viewLifecycleOwner, Observer { fabrics ->
            fabrics?.let { fabricAdapter.updateFabricList(it) }
        })
    }
}
