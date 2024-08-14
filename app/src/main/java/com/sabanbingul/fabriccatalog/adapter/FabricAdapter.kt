package com.sabanbingul.fabriccatalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sabanbingul.fabriccatalog.databinding.ItemFabricBinding
import com.sabanbingul.fabriccatalog.model.Fabric
import com.sabanbingul.fabriccatalog.view.FeedFragmentDirections
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sabanbingul.fabriccatalog.R

class FabricAdapter(val fabricList: ArrayList<Fabric>) : RecyclerView.Adapter<FabricAdapter.FabricViewHolder>() {

    class FabricViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var itemName: TextView = view.findViewById(R.id.itemName)
        var itemPic: ImageView = view.findViewById(R.id.itemPic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FabricViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_fabric, parent, false)
        return FabricViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fabricList.size
    }

    override fun onBindViewHolder(holder: FabricViewHolder, position: Int) {
        val fabric = fabricList[position]
        holder.itemName.text = fabric.name

        // Glide kullanarak resmi yükleyin
        Glide.with(holder.view.context)
            .load(fabric.pic) // imageUrl, Fabric modelinde tanımladığınız URL
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background)) // Yer tutucu resmi (opsiyonel)
            .into(holder.itemPic)

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToFabricFragment(fabric.uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateFabricList(newFabricList: List<Fabric>) {
        fabricList.clear()
        fabricList.addAll(newFabricList)
        notifyDataSetChanged()
    }
}
