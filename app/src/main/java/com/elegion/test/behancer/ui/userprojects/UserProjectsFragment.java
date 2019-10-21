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

import com.elegion.test.behancer.common.BaseFragment;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.databinding.UserProjectsBinding;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.utils.UserProjectsFactory;

import static com.elegion.test.behancer.ui.profile.ProfileFragment.PROFILE_KEY;

public class UserProjectsFragment extends BaseFragment {
    private UserProjectsViewModel mUserProjectsViewModel;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener=null;
    private String mUsername;


    public static UserProjectsFragment newInstance(Bundle args) {
       // return new UserProjectsFragment(args);
        UserProjectsFragment projectsFragment = new UserProjectsFragment();
        projectsFragment.setArguments(args);
        return projectsFragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mUsername = getArguments().getString(PROFILE_KEY);
        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            UserProjectsFactory factory = new UserProjectsFactory(storage,mUsername);
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
