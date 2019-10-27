package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.ProjectResponse;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.Single;

public class ProjectsViewModel extends BaseProjectsViewModel {

    public ProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        super(storage, onItemClickListener, storage.getProjectsPaged());
        updateProjects();
    }


    @Override
    protected Single<ProjectResponse> getProjectsFromServer() {
        return ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY);
    }
}


