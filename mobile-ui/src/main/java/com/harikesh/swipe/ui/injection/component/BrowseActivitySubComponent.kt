package com.harikesh.swipe.ui.injection.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import com.harikesh.swipe.ui.browse.BrowseActivity

@Subcomponent
interface BrowseActivitySubComponent : AndroidInjector<BrowseActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BrowseActivity>()

}