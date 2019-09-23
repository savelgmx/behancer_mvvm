package com.elegion.test.behancer.ui.userprojects;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.ui.profile.ProfileViewModel;

public class UserProjectsViewModel extends ViewModel {

    private Storage mStorage;
    private ProfileViewModel.OnItemClickListener mOnItemClickListener;
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener;
    private LiveData<PagedList<RichProject>> mUserProjects;


    public UserProjectsViewModel(Storage storage, ProfileViewModel.OnItemClickListener onItemClickListener) {
        mStorage = storage;
        mOnItemClickListener = onItemClickListener;
        updateUserProjects();
    }

    private void updateUserProjects() {
        Log.d("behancer_mvvm", "Here list of user projects must be updated");

    }

    public ProfileViewModel.OnItemClickListener getOnItemClickListener() {
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