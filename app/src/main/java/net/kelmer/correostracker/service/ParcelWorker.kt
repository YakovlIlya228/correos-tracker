package net.kelmer.correostracker.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import net.kelmer.correostracker.base.worker.ChildWorkerFactory
import net.kelmer.correostracker.data.repository.correos.CorreosRepository
import net.kelmer.correostracker.data.repository.local.LocalParcelRepository
import net.kelmer.correostracker.util.SchedulerProvider
import javax.inject.Inject

class ParcelWorker @Inject constructor(
        val localParcelRepository: LocalParcelRepository,
        val correosRepository: CorreosRepository,
        val schedulerProvider: SchedulerProvider,
        appContext: Context,
        workerParams: WorkerParameters
) : Worker(appContext, workerParams) {


    override fun doWork(): Result {

        localParcelRepository.getParcels()
                .flatMapIterable {
                    it
                }
                .map {
                    correosRepository.getParcelStatus(it.code)
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.io())
                .subscribe()

        return Result.success()
    }

    class Factory @Inject constructor(
            val localParcelRepository: LocalParcelRepository,
            val correosRepository: CorreosRepository,
            val schedulerProvider: SchedulerProvider
            ): ChildWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): Worker {
            return ParcelWorker(localParcelRepository, correosRepository, schedulerProvider, appContext, params)
        }
    }


}