package com.elegion.test.behancer.ui.userprojects;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.test.behancer.common.BaseViewModel;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.ProjectResponse;
import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserProjectsViewModel extends BaseViewModel {


    private Disposable mDisposable;

    private Storage mStorage;
    private String mUsername;
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    private LiveData<PagedList<RichProject>> mUserProjects;
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener=this::updateUserProjects;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;


    public UserProjectsViewModel(Storage storage, String username) {
        mStorage = storage;
        mUsername =username;
        mUserProjects = mStorage.getProjectsPaged();
        updateUserProjects();

    }


    private void updateUserProjects() {

        mDisposable = ApiUtils.getApiService().getUserProjects(mUsername)

                .map(ProjectResponse::getProjects)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))

                .doOnSuccess(response -> mIsErrorVisible.postValue(false))

                .subscribe(
                        response -> mStorage.insertProjects(response),
                        throwable -> {
                            boolean value = mUserProjects.getValue() == null || mUserProjects.getValue().size() == 0;
                            mIsErrorVisible.postValue(value);
                        });

    }

    @Override
    public void onCleared() {
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public MutableLiveData<Boolean> getIsLoading() { return mIsLoading; }
    public MutableLiveData<Boolean> getIsErrorVisible() { return mIsErrorVisible; }
    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() { return mOnRefreshListener;}
    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {return null;}


    public LiveData<PagedList<RichProject>> getUserProjects() { return mUserProjects; }

}