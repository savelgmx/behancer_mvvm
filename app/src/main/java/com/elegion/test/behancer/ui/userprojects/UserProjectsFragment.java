package com.elegion.test.behancer.ui.userprojects;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.databinding.UserProjectsBinding;

import com.elegion.test.behancer.ui.profile.ProfileViewModel;
import com.elegion.test.behancer.utils.UserProjectsFactory;

public class UserProjectsFragment extends Fragment {
    private UserProjectsViewModel mUserProjectsViewModel;
    private ProfileViewModel.OnItemClickListener mOnItemClickListener;

    public static UserProjectsFragment newInstance() {
        return new UserProjectsFragment();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            UserProjectsFactory factory = new UserProjectsFactory(storage, mOnItemClickListener);
            mUserProjectsViewModel = ViewModelProviders.of(this, factory).get(UserProjectsViewModel.class);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        UserProjectsBinding binding = UserProjectsBinding.inflate(inflater,container,false);
        binding.setUvm(mUserProjectsViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

}
