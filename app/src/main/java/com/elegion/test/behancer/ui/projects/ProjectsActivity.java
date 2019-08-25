package com.elegion.test.behancer.ui.projects;

import android.support.v4.app.Fragment;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.common.SingleFragmentActivity;
import com.elegion.test.behancer.data.Storage;

/**
 * Created by Vladislav Falzan.
 */

/*
Добавить реализацию ViewModel на экран Profile с помощью databinding library

Критерии проверки
1) ProfileActivity наследуется от SingleFragmentActivity,
в R.layout.fr_profile есть SwipeRefreshLayout
(по аналогии с рефакторингом экрана проектов, см. уроки)
2) В fr_profile добавлены необходимые изменения для работы с databinding library
3) Создан класс ProfileViewModel, который поставляет данные для ProfileFragment/fr_profile
4) Логика загрузки, отображения, обработки ошибок перенесена в ProfileViewModel/fr_pofile
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
