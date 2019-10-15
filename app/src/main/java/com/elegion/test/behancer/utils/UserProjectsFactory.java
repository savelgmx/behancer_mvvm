package com.elegion.test.behancer.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.userprojects.UserProjectsViewModel;

public class UserProjectsFactory extends ViewModelProvider.NewInstanceFactory {

    private String mUsername;
    private Storage mStorage;
    public UserProjectsFactory(Storage storage, String username){
        mStorage = storage;
        mUsername = username;

      //  mOnItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
     public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
      //  Log.d("behancer_mvvm","UserProjectsFactory вызываем UserProjectsViewModel.class"+"User");
        return (T) new UserProjectsViewModel(mStorage,mUsername);
    }
}
