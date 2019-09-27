package com.harikesh.swipe.ui.injection.module

import dagger.Module
import dagger.Provides
import com.harikesh.swipe.domain.interactor.browse.GetCardData
import com.harikesh.swipe.presentation.browse.BrowseContract
import com.harikesh.swipe.presentation.browse.BrowsePresenter
import com.harikesh.swipe.presentation.mapper.CardDataMapper
import com.harikesh.swipe.ui.browse.BrowseActivity
import com.harikesh.swipe.ui.injection.scopes.PerActivity



/**
 * Module used to provide dependencies at an activity-level.
 */
@Module
open class BrowseActivityModule {

    @PerActivity
    @Provides
    internal fun provideBrowseView(browseActivity: BrowseActivity): BrowseContract.View {
        return browseActivity
    }

    @PerActivity
    @Provides
    internal fun provideBrowsePresenter(mainView: BrowseContract.View,
                                        getCardData: GetCardData, mapper: CardDataMapper):
            BrowseContract.Presenter {
        return BrowsePresenter(mainView, getCardData, mapper)
    }

}
