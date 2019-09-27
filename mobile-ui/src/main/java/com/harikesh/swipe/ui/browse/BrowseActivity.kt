package com.harikesh.swipe.ui.browse

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.harikesh.swipe.presentation.browse.BrowseContract
import com.harikesh.swipe.presentation.model.CardDataView
import com.harikesh.swipe.ui.R
import com.harikesh.swipe.ui.mapper.CardMapper
import com.harikesh.swipe.ui.model.CardDataViewModel
import com.yuyakaido.android.cardstackview.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

class BrowseActivity : AppCompatActivity(), BrowseContract.View, CardStackListener {

    @Inject
    lateinit var onboardingPresenter: BrowseContract.Presenter
    @Inject
    lateinit var browseAdapter: BrowseAdapter
    @Inject
    lateinit var mapper: CardMapper
    private val cardStackView: CardStackView by lazy { findViewById<CardStackView>(R.id.card_stack_view) }
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private val cards = arrayListOf<CardDataViewModel>()

    override fun setPresenter(presenter: BrowseContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun hideProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun showProgress() {
        progress.visibility = View.GONE
    }

    override fun showCardData(cardData: List<CardDataView>) {
        paginate(cardData.map { mapper.mapToViewModel(it) })
        cards.addAll(cardData.map { mapper.mapToViewModel(it) })
    }

    override fun hideCardData() {
        recycler_browse.visibility = View.VISIBLE
    }

    override fun showErrorState() {
    }

    override fun hideErrorState() {
    }

    override fun showEmptyState() {
    }

    override fun hideEmptyState() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        AndroidInjection.inject(this)
//        setupBrowseRecycler()
        setupCardStackView()
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start()
    }

    private fun setupCardStackView() {
        initialize()
    }

    private fun initialize() {

        val direction = mutableListOf<Direction>()
        direction.add(Direction.Left)
        direction.add(Direction.Right)
        direction.add(Direction.Top)
        val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Top)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()

        manager.setStackFrom(StackFrom.Top)
        manager.setVisibleCount(2)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(direction)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setRewindAnimationSetting(setting)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = browseAdapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

    }

    private fun paginate(cardData: List<CardDataViewModel>) {
        val old = cardData
        val new = old.plus(cardData)
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        browseAdapter.cardData = new
        result.dispatchUpdatesTo(browseAdapter)
    }

    private fun reload() {
        val old = browseAdapter.getCardList()
        val new = cards
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        browseAdapter.cardData = new
        result.dispatchUpdatesTo(browseAdapter)
    }


    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        if (direction == Direction.Top) {
            cardStackView.rewind()
        }
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        val status = """${(position + 1)}/${browseAdapter.itemCount}"""
        text_counter.text = status
        Log.d("CardStackView", "onCardAppeared: (position}")
    }

    override fun onCardDisappeared(view: View, position: Int) {
        Log.d("CardStackView", "onCardDisappeared")
        if (position == browseAdapter.itemCount - 1) {
            paginate(cards)
        }
    }

}