package com.sabanbingul.fabriccatalog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sabanbingul.fabriccatalog.model.Fabric

class FeedViewModel : ViewModel() {
    val fabrics = MutableLiveData<List<Fabric>>()

    fun loadData(){

        val fabric1 = Fabric("30/1 Full Lyc Supreme", "180cm", "100% Cotton", "180gr", "asdasd", "www.ss.com")
        val fabric2 = Fabric("30/1 Full Lyc Supreme", "180cm", "100% Cotton", "180gr", "asdasd", "www.ss.com")
        val fabric3 = Fabric("30/1 Full Lyc Supreme", "180cm", "100% Cotton", "180gr", "asdasd", "www.ss.com")
        val fabric4 = Fabric("30/1 Full Lyc Supreme", "180cm", "100% Cotton", "180gr", "asdasd", "www.ss.com")

        val fabricList = arrayListOf<Fabric>(fabric1, fabric2, fabric3, fabric4)
        fabrics.value = fabricList
    }
}