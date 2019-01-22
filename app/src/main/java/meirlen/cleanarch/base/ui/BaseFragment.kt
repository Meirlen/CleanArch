/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package meirlen.cleanarch.base.ui


import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import com.example.gateway.entity.Error

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_lce.*
import kotlinx.android.synthetic.main.layout_lce.*
import meirlen.cleanarch.R
import meirlen.cleanarch.base.state.LceEmptyView
import meirlen.cleanarch.base.state.LceLayout
import meirlen.cleanarch.routers.MainRouter
import meirlen.cleanarch.utill.interfaces.CallbackResponse
import org.koin.android.ext.android.inject

abstract class BaseFragment<in M> : Fragment(),
        BaseLceView<M>, CallbackResponse<M> {

    val router by inject<MainRouter>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getRootView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lceLayout.setupContentLayout(getContentView())
        lceLayout.tryAgainButtonClickListener = View.OnClickListener { tryAgainButtonClicked() }
        lceLayout.backButtonClickListener = View.OnClickListener { activity?.onBackPressed() }
        setupEmptyView(emptyView)
    }



    //INIT
    @LayoutRes
    protected open fun getRootView() = R.layout.fragment_lce

    @LayoutRes
    abstract fun getContentView(): Int

    //SETUP
    protected open fun setupEmptyView(emptyView: LceEmptyView) {}

    //LCE
    @CallSuper
    open fun loadData(pullToRefresh: Boolean = false) {
        if (!pullToRefresh) {
            showLoading()
        }
    }

    @CallSuper
    override fun showContent(data: M) {
        lceLayout.changeState(LceLayout.LceState.ContentState)
    }

    override fun showLoading(isTranslucent: Boolean) {
        lceLayout.changeState(LceLayout.LceState.LoadingState(isTranslucent))
    }

    override fun showEmptyState() {
        lceLayout.changeState(LceLayout.LceState.EmptyState)
    }

    override fun showError(error: Error) {
        lceLayout.changeState(LceLayout.LceState.ErrorState(error))
    }

    override fun showMessage(messageRes: Int) {
        showMessage(getString(messageRes))
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    //CALLBACK
    open fun tryAgainButtonClicked() {
        loadData()
    }

    //CALLBACK HTTP RESPONSE
    /*fun handleResponse(listResource: Resource<M>) {
        when (listResource.status) {
            Status.ERROR ->
                showError(Error.NonCritical(listResource.message!!))
            Status.LOADING ->
                loadData(false)
            Status.SUCCESS -> {
                onResponse(listResource)

            }
        }
    }*/

    internal fun toast(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}