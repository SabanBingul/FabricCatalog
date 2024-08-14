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

class FabricViewModel(application: Application) : AndroidViewModel(application) {

    private val firebaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("fabrics")
    private val _fabricLiveData = MutableLiveData<Fabric>()
    val fabricLiveData: LiveData<Fabric> get() = _fabricLiveData

    fun getData(fabricUuid: Int) {
        firebaseRef.child(fabricUuid.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val fabric = snapshot.getValue(Fabric::class.java)
                _fabricLiveData.value = fabric!!
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors.
            }
        })
    }
}
