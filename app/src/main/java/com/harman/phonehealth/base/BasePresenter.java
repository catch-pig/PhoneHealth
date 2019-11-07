package com.harman.phonehealth.base;

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter {
    protected final V mView;
    public BasePresenter(V view){
        mView = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
