package com.elegion.test.behancer.ui.userprojects;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.profile.ProfileViewModel;
import com.elegion.test.behancer.utils.ApiUtils;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserProjectsViewModel extends ViewModel {

    private Disposable mDisposable;

    private Storage mStorage;
    private ProfileFragment.OnItemClickListener mOnItemClickListener;
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    private LiveData<PagedList<RichProject>> mUserProjects;
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener=this::updateUserProjects;


    public UserProjectsViewModel(Storage storage, ProfileFragment.OnItemClickListener onItemClickListener) {
        mStorage = storage;
        mOnItemClickListener = onItemClickListener;
        mUserProjects = mStorage.getProjectsPaged();
        updateUserProjects();
    }

    private void updateUserProjects() {
        Log.d("behancer_mvvm", "Here list of user projects must be updated");

        mDisposable = ApiUtils.getApiService().getUserProjects("Hidden_Foo")
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .doOnSuccess(response -> mIsErrorVisible.postValue(false))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> mStorage.insertProjects(response),
                        throwable -> {
                            boolean value = mUserProjects.getValue() == null || mUserProjects.getValue().size() == 0;
                            mIsErrorVisible.postValue(value);
                        });


    }

    public ProfileFragment.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public LiveData<PagedList<RichProject>> getUserProjects() {
        return mUserProjects;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;

    }

}