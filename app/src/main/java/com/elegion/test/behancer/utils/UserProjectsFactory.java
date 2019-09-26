package com.elegion.test.behancer.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.profile.ProfileViewModel;
import com.elegion.test.behancer.ui.userprojects.UserProjectsViewModel;

public class UserProjectsFactory extends ViewModelProvider.NewInstanceFactory {
    private ProfileFragment.OnItemClickListener mOnItemClickListener;
    private Storage mStorage;
    public UserProjectsFactory(Storage storage , ProfileFragment.OnItemClickListener onItemClickListener ){
        mStorage = storage;
        mOnItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
     public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        return (T) new UserProjectsViewModel(mStorage,mOnItemClickListener);
    }
}
