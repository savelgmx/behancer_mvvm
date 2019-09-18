package com.elegion.test.behancer.ui.userprojects;

import android.arch.lifecycle.ViewModel;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.ui.profile.ProfileViewModel;

public class UserProjectsViewModel extends ViewModel {

    private ProfileViewModel.OnItemClickListener mOnItemClickListener;
    private Storage mStorage;

    public UserProjectsViewModel(Storage storage, ProfileViewModel.OnItemClickListener onItemClickListener )
    {
        mStorage = storage;
        mOnItemClickListener= onItemClickListener;
    }
}
