package com.elegion.test.behancer.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*       необходимо создать базовый фрагмент
               для отображения списка проектов.
        Наследовать от него два фрагмента,
               для проектов и для проектов определенного пользователя.
*/
public abstract class BaseFragment extends Fragment {
      public abstract View onCreateView(@NonNull LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState);

}
