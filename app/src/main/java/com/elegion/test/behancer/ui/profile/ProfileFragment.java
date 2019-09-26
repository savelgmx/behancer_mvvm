package com.elegion.test.behancer.ui.profile;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.common.Refreshable;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.databinding.ProfileBinding;
import com.elegion.test.behancer.ui.userprojects.UserProjectsActivity;
import com.elegion.test.behancer.utils.DateUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by Vladislav Falzan.
 */
public class ProfileFragment extends Fragment {

    public static final String PROFILE_KEY = "PROFILE_KEY";
    private String mUsername;
    private ProfileViewModel mProfileViewModel;

    public final OnItemClickListener mOnItemClickListener = username -> {
        //здесь должен быть вызов списка проектов пользвателя
        Log.d("behancer_mvvm","ProfileFragment Intent вызываем UserProjectsActivity.class");

        Intent intent = new Intent(getActivity(), UserProjectsActivity.class);
        Bundle args = new Bundle();
        args.putString(ProfileFragment.PROFILE_KEY, username);
        intent.putExtra(ProfileActivity.USERNAME_KEY, args);
        startActivity(intent);

    };


    public static ProfileFragment newInstance(Bundle args) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mUsername=getArguments().getString(PROFILE_KEY);


        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            mProfileViewModel = new ProfileViewModel(storage,mUsername,mOnItemClickListener);

            ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
                @NonNull
                @Override
                public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                    return (T) new ProfileViewModel(storage,mUsername,mOnItemClickListener);
                }
            };
            mProfileViewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel.class);


        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProfileBinding binding = ProfileBinding.inflate(inflater,container,false);
        binding.setPm(mProfileViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    public interface OnItemClickListener {
        void onItemClick(String username);
    }


}
