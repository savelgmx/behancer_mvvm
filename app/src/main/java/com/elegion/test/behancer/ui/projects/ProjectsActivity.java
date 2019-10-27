package com.elegion.test.behancer.ui.projects;

import android.support.v4.app.Fragment;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.common.SingleFragmentActivity;
import com.elegion.test.behancer.data.Storage;

/**
 * Created by Vladislav Falzan.
 */

/*
ДНа ProfileFragment добавить кнопку, которая ведет на экран со списком проектов этого пользователя - UserProjectsFragment
Нужный запрос - GET /v2/users/{user}/projects
По факту, это будет экран, похожий на ProjectsFragment, поэтому вам нужно по максимуму переиспользовать текущий код
При нажатии на элементы списка UserProjectsFragment - ничего не должно происходить
Выделите одинаковый код в базовый вьюмодель и подготовьте абстрактные методы для наследников
 - ProjectsFragment и UserProjectsFragment/ProjectsViewModel и UserProjectsProjectsViewModel

 */
public class ProjectsActivity extends SingleFragmentActivity implements Storage.StorageOwner {

    @Override
    protected Fragment getFragment() {
        return ProjectsFragment.newInstance();
    }

    @Override
    public Storage obtainStorage() {
        return ((AppDelegate) getApplicationContext()).getStorage();
    }
}
