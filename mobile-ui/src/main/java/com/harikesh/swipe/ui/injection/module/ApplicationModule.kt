package com.harikesh.swipe.ui.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import com.harikesh.swipe.cache.CardDataCacheImpl
import com.harikesh.swipe.cache.PreferencesHelper
import com.harikesh.swipe.cache.db.DbOpenHelper
import com.harikesh.swipe.cache.mapper.CardDataEntityMapper
import com.harikesh.swipe.data.DataRepository
import com.harikesh.swipe.data.executor.JobExecutor
import com.harikesh.swipe.data.mapper.CardDataMapper
import com.harikesh.swipe.data.repository.CardDataCache
import com.harikesh.swipe.data.repository.CardDataRemote
import com.harikesh.swipe.data.source.CardDataStoreFactory
import com.harikesh.swipe.domain.executor.PostExecutionThread
import com.harikesh.swipe.domain.executor.ThreadExecutor
import com.harikesh.swipe.domain.repository.Repository
import com.harikesh.swipe.remote.CardDataRemoteImpl
import com.harikesh.swipe.remote.SwipeApi
import com.harikesh.swipe.remote.ServiceFactory
import com.harikesh.swipe.ui.BuildConfig
import com.harikesh.swipe.ui.UiThread
import com.harikesh.swipe.ui.injection.scopes.PerApplication

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }


    @Provides
    @PerApplication
    internal fun repository(factory: CardDataStoreFactory,
                            mapper: CardDataMapper): Repository {
        return DataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun cardDataCache(factory: DbOpenHelper,
                               entityMapper: CardDataEntityMapper,
                               mapper: com.harikesh.swipe.cache.db.mapper.LocalCardDataMapper,
                               helper: PreferencesHelper): CardDataCache {
        return CardDataCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @PerApplication
    internal fun cardDataRemote(service: SwipeApi,
                                factory: com.harikesh.swipe.remote.mapper.PojoEntityMapper): CardDataRemote {
        return CardDataRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun cardDataService(): SwipeApi {
        return ServiceFactory.makeApiService(BuildConfig.DEBUG)
    }
}
