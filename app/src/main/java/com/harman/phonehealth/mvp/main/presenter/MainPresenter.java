package com.harman.phonehealth.mvp.main.presenter;

import com.harman.phonehealth.base.BasePresenter;
import com.harman.phonehealth.mvp.main.MainContract;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private final MainContract.Model mModel;

    public MainPresenter(MainContract.View view,MainContract.Model model) {
        super(view);
        mModel = model;
    }
}
