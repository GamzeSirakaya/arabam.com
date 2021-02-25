package com.example.cars.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cars.R
import com.example.cars.databinding.RecyclerRowBinding
import com.example.cars.model.CarList
import com.example.cars.util.CarClickListener
import kotlinx.android.synthetic.main.recycler_row.view.*


class CarAdapter:PagedListAdapter<CarList,CarAdapter.CarViewHolder>(diffUtilCallBack),
    CarClickListener {


    class CarViewHolder(var view: RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(carList: CarList?){
            view.carlist=carList
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerRowBinding>(
            inflater,
            R.layout.recycler_row,
            parent,
            false
        )


        return CarViewHolder(view)

    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.view.executePendingBindings()
        holder.view.listener=this



    }




    override fun carClick( view: View) {
        val carId = view.idTextt.text.toString().toInt()
        val bundle = bundleOf("carId" to carId)
        Navigation.findNavController(view).navigate(R.id.action_carListFragment_to_carDetailFragment,bundle)


    }


    companion object {
        private val diffUtilCallBack =
            object  : DiffUtil.ItemCallback<CarList>(){
                override fun areItemsTheSame(oldItem: CarList, newItem: CarList): Boolean {
                    return oldItem == newItem
                }
                override fun areContentsTheSame(oldItem: CarList, newItem: CarList): Boolean {
                    return oldItem.title == newItem.title && oldItem.id == newItem.id
                }

            }

    }
}

