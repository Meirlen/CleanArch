package meirlen.cleanarch.base.state
import com.example.gateway.entity.Error

import android.content.Context
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import meirlen.cleanarch.R
import kotlinx.android.synthetic.main.view_lce_empty.view.*
import kotlinx.android.synthetic.main.view_lce_error.view.*
import kotlinx.android.synthetic.main.layout_lce.view.*

class LceLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
        LceView {

    var tryAgainButtonClickListener: OnClickListener? = null
        set(value) {
            field = value
            value?.let { tryAgainButton.setOnClickListener(it) }
        }

    var backButtonClickListener: OnClickListener? = null
        set(value) {
            field = value
            value?.let { backButton.setOnClickListener(it) }
        }

    var emptyButtonClickListener: OnClickListener? = null
        set(value) {
            field = value
            value?.let { emptyButton.setOnClickListener(it) }
        }

    init {
        View.inflate(context, R.layout.layout_lce, this)
    }

    fun setupContentLayout(@LayoutRes layout: Int) {
        contentView.removeAllViews()
        View.inflate(context, layout, contentView)
    }

    override fun changeState(state: LceState) {
        loadingView.changeState(state)
        contentView.changeState(state)
        emptyView.changeState(state)
        errorView.changeState(state)
    }

    sealed class LceState {
        class LoadingState(val isTranslucent: Boolean = false) : LceState()
        object ContentState : LceState()
        object EmptyState : LceState()
        class ErrorState(val error: Error) : LceState()
    }

}