package com.harikesh.swipe.ui.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.harikesh.swipe.ui.browse.BrowseActivity
import com.harikesh.swipe.ui.injection.scopes.PerActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
    abstract fun bindMainActivity(): BrowseActivity

}