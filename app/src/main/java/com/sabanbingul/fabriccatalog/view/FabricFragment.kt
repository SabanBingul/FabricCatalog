package com.sabanbingul.fabriccatalog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sabanbingul.fabriccatalog.R
import com.sabanbingul.fabriccatalog.databinding.FragmentFabricBinding
import com.sabanbingul.fabriccatalog.model.Fabric
import com.sabanbingul.fabriccatalog.viewmodel.FabricViewModel


class FabricFragment : Fragment() {

    private lateinit var binding: FragmentFabricBinding
    private var fabricUuid = 0
    private lateinit var viewModel: FabricViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFabricBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FabricViewModel::class.java)
        viewModel.getData()

        arguments?.let {
            fabricUuid = FabricFragmentArgs.fromBundle(it).fabricUuid
        }

        observeLiveData()
    }


    private fun observeLiveData(){
        viewModel.fabricLiveData.observe(viewLifecycleOwner, Observer{ fabric ->
            fabric.let {
                binding.fabricName.text = fabric.fabricName
                binding.fabricWeight.text = fabric.fabricWeight
                binding.fabricMaterial.text = fabric.fabricMaterial
                binding.fabricWidth.text = fabric.fabricWidth
                binding.fabricInfo.text = fabric.fabricInfo

            }
        })
    }
}