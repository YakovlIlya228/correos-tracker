package net.kelmer.correostracker.di

import androidx.work.Worker
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import net.kelmer.correostracker.base.worker.ChildWorkerFactory
import net.kelmer.correostracker.service.ParcelWorker
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out Worker>)


@Module
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(ParcelWorker::class)
    internal abstract fun bindMyWorkerFactory(worker: ParcelWorker.Factory): ChildWorkerFactory


}