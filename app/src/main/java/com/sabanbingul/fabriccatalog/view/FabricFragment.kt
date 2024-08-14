package com.sabanbingul.fabriccatalog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sabanbingul.fabriccatalog.R
import com.sabanbingul.fabriccatalog.databinding.FragmentFabricBinding
import com.sabanbingul.fabriccatalog.viewmodel.FabricViewModel

class FabricFragment : Fragment() {

    private lateinit var binding: FragmentFabricBinding
    private var fabricUuid: Int = 0
    private lateinit var viewModel: FabricViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFabricBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FabricViewModel::class.java)

        arguments?.let {
            fabricUuid = FabricFragmentArgs.fromBundle(it).fabricUuid
        }

        viewModel.getData(fabricUuid)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.fabricLiveData.observe(viewLifecycleOwner, Observer { fabric ->
            fabric?.let {
                binding.fabricName.text = it.name
                binding.fabricWeight.text = it.weight
                binding.fabricMaterial.text = it.material
                binding.fabricWidth.text = it.width
                binding.fabricInfo.text = it.info

                // Fotoğrafı Glide ile yükleme
                Glide.with(this)
                    .load(it.pic)
                    .placeholder(R.drawable.ic_launcher_background) // Yer tutucu
                    .error(R.drawable.ic_launcher_background) // Hata resmi
                    .into(binding.fabricPic)
            }
        })
    }

}
