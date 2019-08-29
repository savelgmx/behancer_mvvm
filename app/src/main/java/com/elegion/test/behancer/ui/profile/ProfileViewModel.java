package com.elegion.test.behancer.ui.profile;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends ViewModel {
    private String mUsername;
    private Storage mStorage;
    private Disposable mDisposable;
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();

    private ObservableField<User> mProfile = new ObservableField<>();

  //  private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    //private SwipeRefreshLayout mSwipeRefreshLayout = view.FindViewById(R.id.profile_refresher);
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::loadProfile;

    public ProfileViewModel(Storage storage, String user){
        mStorage=storage;
        mUsername = user;
       // mUser = mStorage.getUserWithImageLiveByName(mUsername);
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
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() ->  mIsLoading.postValue(false))
                .subscribe(
                        response -> { mStorage.insertUser(response);
                            mProfile.set(response.getUser());
                            mIsLoading.postValue(false);
                        },
                        throwable -> { mIsErrorVisible.postValue(true);

                        });
     }

    @Override
    public void onCleared(){
        mStorage = null;
        if(mDisposable!=null){
            mDisposable.dispose();
        }

    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }


    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }
    public ObservableField<User> getProfile() {
        return mProfile;
    }


}


