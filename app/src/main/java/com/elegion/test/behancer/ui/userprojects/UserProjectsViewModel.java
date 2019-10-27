package com.elegion.test.behancer.ui.userprojects;


import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.ProjectResponse;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.Single;

public class UserProjectsViewModel extends BaseProjectsViewModel {

    private String mUserName;

    public UserProjectsViewModel(Storage storage, String userName) {
        super(storage, null, storage.getUserProjectsPaged(userName));
        mUserName = userName;
        updateProjects();
    }


    @Override
    protected Single<ProjectResponse> getProjectsFromServer() {
        return ApiUtils.getApiService().getUserProjects(mUserName);
    }
}
