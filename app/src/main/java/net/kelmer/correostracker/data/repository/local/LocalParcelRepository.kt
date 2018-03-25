package net.kelmer.correostracker.data.repository.local

import io.reactivex.Single
import net.kelmer.correostracker.data.model.local.LocalParcelReference

/**
 * Created by gabriel on 25/03/2018.
 */
interface LocalParcelRepository {

    fun getParcels(): Single<List<LocalParcelReference>>
    fun getParcel(code: String): Single<LocalParcelReference>
    fun saveParcel(parcel: LocalParcelReference)
    fun deleteParcel(parcel: LocalParcelReference)
}