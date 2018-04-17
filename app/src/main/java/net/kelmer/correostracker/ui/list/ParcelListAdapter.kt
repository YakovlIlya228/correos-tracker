package net.kelmer.correostracker.ui.list

import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_parcel_item.view.*
import net.kelmer.correostracker.R
import net.kelmer.correostracker.data.model.local.LocalParcelReference
import android.view.MotionEvent



/**
 * Created by gabriel on 25/03/2018.
 */
class ParcelListAdapter constructor(
        val clickListener: ParcelClickListener
) : RecyclerView.Adapter<ParcelListAdapter.ViewHolder>() {


    var items = mutableListOf<LocalParcelReference>()

    override fun getItemCount(): Int =
            items.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.rv_parcel_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(parcel: LocalParcelReference,
                 clickListener: ParcelClickListener) = with(itemView) {

            parcel_name.text = parcel.parcelName
            parcel_code.text = parcel.code
            parcel_cardview.setOnClickListener {
                clickListener.click(parcel)
            }
            more.setOnClickListener {
                clickListener.dots(more, parcel)
            }

            parcel_cardview.setOnLongClickListener {
                clickListener.longPress(parcel)
                true
            }
        }

    }

    fun updateItems(data: List<LocalParcelReference>) {
        items = data.toMutableList()
    }
}