package com.elegion.test.behancer.ui.userprojects;

import android.support.v4.app.Fragment;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.common.SingleFragmentActivity;
import com.elegion.test.behancer.data.Storage;

public class UserProjectsActivity extends SingleFragmentActivity implements Storage.StorageOwner {
    @Override
    protected Fragment getFragment() {
        //return null;
        return UserProjectsFragment.newInstance();
    }

    @Override
    public Storage obtainStorage() {

        //return null;
        return ((AppDelegate) getApplicationContext()).getStorage();
    }
}
