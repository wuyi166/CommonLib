package com.march.lib.core.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.march.lib.core.mvp.view.BaseView;


/**
 * Project  : Reaper
 * Package  : com.march.reaper.base.mvp.mPresenter
 * CreateAt : 2016/10/13
 * Describe :
 *
 * @author chendong
 */

public class BasePresenter<V extends BaseView> implements IPresenter<V> {

    protected V mView;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public Context getContext() {
        return mView.getContext();
    }

    public Activity getActivity() {
        return mView.getActivity();
    }
}
