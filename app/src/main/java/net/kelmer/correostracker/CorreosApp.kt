package net.kelmer.correostracker

import android.app.Application
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import net.kelmer.correostracker.base.worker.MyWorkerFactory
import net.kelmer.correostracker.data.db.DbModule
import net.kelmer.correostracker.service.ParcelWorker
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by gabriel on 25/03/2018.
 */
class CorreosApp : Application() {

    companion object {
        lateinit var graph: ApplicationComponent
    }

    @Inject
    lateinit var myWorkerFactory: MyWorkerFactory


    override fun onCreate() {
        super.onCreate()
        initDependencyGraph()
        setupTimber()
        setupWorkers()
    }

    private fun setupWorkers() {


        // Create a Constraints object that defines when the task should run
        val constraints = Constraints.Builder()
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .build()

// ...then create a OneTimeWorkRequest that uses those constraints
        val compressionWork = OneTimeWorkRequestBuilder<ParcelWorker>()
                .setConstraints(constraints)
                .build()

//        WorkManager.initialize(
//                this,
//                Configuration.Builder()
//                        .setWorkerFactory(myWorkerFactory)
//                        .build()
//        )
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

//    private fun setupStetho() {
//        Stetho.initializeWithDefaults(this)
//    }

    private fun initDependencyGraph() {
        graph = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .dbModule(DbModule(this))
                .build()
        graph.injectTo(this)
    }
}