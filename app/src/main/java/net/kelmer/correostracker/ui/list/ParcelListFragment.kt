package net.kelmer.correostracker.ui.list

import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_parcel_list.*
import net.kelmer.correostracker.R
import net.kelmer.correostracker.base.BaseFragment
import net.kelmer.correostracker.data.Result
import net.kelmer.correostracker.ext.observe


/**
 * Created by gabriel on 25/03/2018.
 */
class ParcelListFragment : BaseFragment<ParcelListViewModel>() {
    override val layoutId: Int = R.layout.fragment_parcel_list
    override val viewModelClass: Class<ParcelListViewModel> = ParcelListViewModel::class.java

    val adapter = ParcelListAdapter({ p ->
        Toast.makeText(context, "Clicked ${p.codEnvio}!!", Toast.LENGTH_LONG).show()
    })


    override fun loadUp() {

        setupRecyclerView()

        viewModel.retrieveParcelList()
        viewModel.parcelList.observe(this, {
            when (it) {
                is Result.Success -> {
                    adapter.updateItems(it.data)
                }
                is Result.Failure -> {

                }
            }
        })
    }

    private fun setupRecyclerView() {
        val llm = LinearLayoutManager(context)
        rv_parcel_list.layoutManager = llm
        rv_parcel_list.adapter = adapter
    }
}