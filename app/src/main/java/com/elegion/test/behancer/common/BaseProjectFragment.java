package com.elegion.test.behancer.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.databinding.ProjectsBinding;

/*       необходимо создать базовый фрагмент
               для отображения списка проектов.
        Наследовать от него два фрагмента,
               для проектов и для проектов определенного пользователя.
*/


public abstract class BaseProjectFragment extends Fragment {

    protected BaseProjectsViewModel mBaseProjectsViewModel;
    protected Storage mStorage;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            mStorage = ((Storage.StorageOwner) context).obtainStorage();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProjectsBinding binding = ProjectsBinding.inflate(inflater, container, false);
        binding.setVm(mBaseProjectsViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }


}
