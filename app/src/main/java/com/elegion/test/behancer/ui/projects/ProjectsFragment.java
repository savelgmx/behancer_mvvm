package com.elegion.test.behancer.ui.projects;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.elegion.test.behancer.common.BaseProjectFragment;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.userprojects.UserProjectsActivity;
import com.elegion.test.behancer.ui.userprojects.UserProjectsFragment;
import com.elegion.test.behancer.utils.CustomFactory;

/**
 * Created by Vladislav Falzan.
 */

public class ProjectsFragment extends BaseProjectFragment {


    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {
        return username -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            Bundle args = new Bundle();
            args.putString(UserProjectsFragment.PROFILE_KEY, username);
            intent.putExtra(UserProjectsActivity.USERNAME_KEY, args);
            startActivity(intent);
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (mStorage != null) {
            CustomFactory customFactory = new CustomFactory(mStorage, getOnItemClickListener());
            mBaseProjectsViewModel = ViewModelProviders.of(this, customFactory).get(ProjectsViewModel.class);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBaseProjectsViewModel.setOnItemClickListener(getOnItemClickListener());
    }
}
