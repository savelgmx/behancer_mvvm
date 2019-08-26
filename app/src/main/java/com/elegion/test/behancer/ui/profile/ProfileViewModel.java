package com.elegion.test.behancer.ui.profile;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends ViewModel {
    private String mUsername;
    private Storage mStorage;
    private Disposable mDisposable;
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);


    public ProfileViewModel(Storage storage, String user){
        mStorage=storage;
        mUsername = user;
    }


    private void getProfile() {
      mDisposable = ApiUtils.getApiService().getUserInfo(mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(mUsername) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mSwipeRefreshLayout.setRefreshState(true))
                .doFinally(() -> mSwipeRefreshLayout.setRefreshState(false))
                .subscribe(
                        response -> {
                            mIsErrorVisible.set(false);
                       },
                        throwable -> {

                            mIsErrorVisible.set(true);

                        });
 /* */    }





}


