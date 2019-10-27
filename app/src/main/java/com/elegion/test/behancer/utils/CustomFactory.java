package com.elegion.test.behancer.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsViewModel;
import com.elegion.test.behancer.ui.userprojects.UserProjectsViewModel;

/**
 * @author Azret Magometov
 */
public class CustomFactory extends ViewModelProvider.NewInstanceFactory {

    private Storage mStorage;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;
    private String mUserName;

    public CustomFactory(Storage storage, ProjectsAdapter.OnItemClickListener mOnItemClickListener) {
        this.mStorage = storage;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public CustomFactory(Storage storage, String userName) {
        this.mStorage = storage;
        this.mOnItemClickListener = null;
        mUserName = userName;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ProjectsViewModel.class) {
        return (T) new ProjectsViewModel(mStorage, mOnItemClickListener);
        } else return (T) new UserProjectsViewModel(mStorage, mUserName);
    }
}
