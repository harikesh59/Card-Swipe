package com.harikesh.swipe.ui.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import com.harikesh.swipe.ui.CardApplication
import com.harikesh.swipe.ui.injection.module.ActivityBindingModule
import com.harikesh.swipe.ui.injection.module.ApplicationModule
import com.harikesh.swipe.ui.injection.scopes.PerApplication

@PerApplication
@Component(modules = arrayOf(ActivityBindingModule::class, ApplicationModule::class,
        AndroidSupportInjectionModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: CardApplication)

}
