package com.altran.ibanarriola.teamworktest.common;

import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends BaseView> {

    protected WeakReference<V> view;

    @UiThread
    public void attachView(V view) {
        this.view = new WeakReference(view);
    }

    @UiThread
    public void detachView() {
        clearView();
    }

    @UiThread
    public V getView() {
        return view == null ? null : view.get();
    }

    @UiThread
    public boolean isViewAttached() {
        return view != null && view.get() != null;
    }

    private void clearView() {
        if (view == null) {
            return;
        }
        view.clear();
        view = null;
    }
}
