package net.kelmer.correostracker.ui.list

import net.kelmer.correostracker.R
import net.kelmer.correostracker.base.BaseFragment

/**
 * Created by gabriel on 25/03/2018.
 */
class ParcelListFragment : BaseFragment<ParcelListViewModel>() {
    override val layoutId: Int = R.layout.fragment_parcel_list

    override val viewModelClass: Class<ParcelListViewModel> = ParcelListViewModel::class.java



    override fun loadUp() {
        viewModel.retrieveParcelList()
    }
}