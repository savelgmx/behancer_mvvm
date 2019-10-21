package com.elegion.test.behancer.common;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.test.behancer.ui.projects.ProjectsAdapter;

/*необходимо создать базовый ViewModel для отображения списка проектов.
         Наследовать от него два ViewModel,
         для проектов и для проектов определенного пользователя.
        */

public abstract class BaseViewModel extends ViewModel {


    public abstract MutableLiveData<Boolean> getIsLoading();
    public abstract MutableLiveData<Boolean> getIsErrorVisible();
    public abstract SwipeRefreshLayout.OnRefreshListener getOnRefreshListener();
    protected abstract ProjectsAdapter.OnItemClickListener getOnItemClickListener();

}
