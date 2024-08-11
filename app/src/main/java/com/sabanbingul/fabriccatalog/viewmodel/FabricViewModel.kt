package com.sabanbingul.fabriccatalog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sabanbingul.fabriccatalog.model.Fabric

class FabricViewModel : ViewModel() {

    val fabricLiveData = MutableLiveData<Fabric>()

    fun getData(){
        val fabric = Fabric("30/1 Full Lyc Supreme", "180cm", "100% Cotton", "180gr", "asdasd", "www.ss.com")

        fabricLiveData.value = fabric


    }

}