package net.kelmer.correostracker.data.repository.local

import net.kelmer.correostracker.data.model.local.LocalParcelDao
import net.kelmer.correostracker.data.model.local.LocalParcelReference

/**
 * Created by gabriel on 25/03/2018.
 */
class LocalParcelRepositoryImpl(val localParcelDao: LocalParcelDao) : LocalParcelRepository{


    override fun getParcels()  =
       localParcelDao.getParcels()

    override fun getParcel(code: String) = localParcelDao.getParcel(code)

    override fun saveParcel(parcel: LocalParcelReference) = localParcelDao.saveParcel(parcel)

    override fun deleteParcel(parcel: LocalParcelReference) = localParcelDao.deleteParcel(parcel)



    companion object {
        private var instance: LocalParcelRepositoryImpl? = null

        fun getInstance(localParcelDao: LocalParcelDao): LocalParcelRepositoryImpl {
            if (instance == null) {
                instance = LocalParcelRepositoryImpl(localParcelDao)
            }
            return instance!!
        }
    }


}