package com.sabanbingul.fabriccatalog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sabanbingul.fabriccatalog.model.Fabric

class FeedViewModel(application: Application) : AndroidViewModel(application) {

    private val firebaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("fabrics")
    private val _fabricList = MutableLiveData<List<Fabric>>()
    val fabricList: LiveData<List<Fabric>> get() = _fabricList

    init {
        fetchFabrics()
    }

    private fun fetchFabrics() {
        firebaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val fabrics = mutableListOf<Fabric>()
                if (snapshot.exists()) {
                    for (fabricSnap in snapshot.children) {
                        val fabric = fabricSnap.getValue(Fabric::class.java)
                        fabric?.let { fabrics.add(it) }
                    }
                }
                _fabricList.value = fabrics
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors.
            }
        })
    }
}
