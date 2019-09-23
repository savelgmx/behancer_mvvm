package com.elegion.test.behancer.ui.userprojects;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.profile.ProfileViewModel;

public class UserProjectsFragment extends Fragment {
    private UserProjectsViewModel mUserProjectsViewModel;
    private ProfileViewModel.OnItemClickListener mOnItemClickListener = username->{
        //здесь должен быть вызов списка проектов пользвателя

      Intent intent = new Intent(getActivity(), UserProjectsActivity.class);
      Bundle args = new Bundle();
      args.putString(ProfileFragment.PROFILE_KEY, username);
      intent.putExtra(ProfileActivity.USERNAME_KEY, args);
      startActivity(intent);


    };

    public static UserProjectsFragment newInstance() {
        return new UserProjectsFragment();
    }
}
