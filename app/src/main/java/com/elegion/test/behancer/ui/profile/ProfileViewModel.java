package com.elegion.test.behancer.ui.profile;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.ApiUtils;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ProfileViewModel extends ViewModel {

    //здесь абстактные методы базовой вьюмодели не подходят для реализации т.к. другая сигнатура
    //поэтому мы не наследуемся от него .строго говоря задание это и не требовало

    private String mUsername;
    private Storage mStorage;
    private Disposable mDisposable;
    private ObservableBoolean mIsLoading = new ObservableBoolean();
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean();
    private ObservableField<User> mProfile = new ObservableField<>();
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::loadProfile;

    private final ProfileFragment.OnItemClickListener mOnItemClickListener;

    public ProfileViewModel(
            Storage storage,
            String user,
            ProfileFragment.OnItemClickListener onItemClickListener
    ){
        mStorage=storage;
        mUsername = user;
        mOnItemClickListener = onItemClickListener;
        loadProfile();

     }


    public void loadProfile() {
        mDisposable = ApiUtils.getApiService().getUserInfo(mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(mUsername) :
                                null)
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() ->  mIsLoading.set(false))
                .subscribe(
                        response -> { mStorage.insertUser(response);
                            mProfile.set(response.getUser());
                            mIsLoading.set(false);
                        },
                        throwable -> { mIsErrorVisible.set(true);

                        });
    }

    @Override
    public void onCleared(){
        mStorage = null;
        if(mDisposable!=null){
            mDisposable.dispose();
        }

    }

    public ObservableBoolean getIsLoading(){
        return mIsLoading;
    }
    public ObservableBoolean getIsErrorVisible(){
        return mIsErrorVisible;
    }
    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() { return mOnRefreshListener; }
    public ProfileFragment.OnItemClickListener getOnItemClickListener() { return mOnItemClickListener; }


    public ObservableField<User> getProfile() {return mProfile; }



}