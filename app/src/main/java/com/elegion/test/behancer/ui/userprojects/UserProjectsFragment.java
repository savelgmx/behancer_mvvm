package com.elegion.test.behancer.ui.userprojects;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import com.elegion.test.behancer.common.BaseProjectFragment;
import com.elegion.test.behancer.utils.CustomFactory;

public class UserProjectsFragment extends BaseProjectFragment {

    public static final String PROFILE_KEY = "PROFILE_KEY";

    public static UserProjectsFragment newInstance(Bundle args) {
        UserProjectsFragment fragment = new UserProjectsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        String userName = "";

        if (getArguments() != null) {
            userName = getArguments().getString(PROFILE_KEY);
        }

        if (mStorage != null) {
            CustomFactory customFactory = new CustomFactory(mStorage, userName);
            mBaseProjectsViewModel = ViewModelProviders.of(this, customFactory).get(UserProjectsViewModel.class);
        }
    }
}
