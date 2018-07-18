package com.example.dell.fragmentoverlapdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * 创建日期：2018/7/18
 * 作者:baiyang
 * 24.0.0之前的support库有一个bug，就是在FragmentManager保存Fragment实例状态的时候，没有保存mHidden，
 * 因此重创建之后Fragment都处于显示状态就造成了重叠。如果使用的24版本以下就要保存fragment的显示状态
 */
public class BaseFragment extends Fragment {
    private static final String IS_HIDDEN = "IS_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (savedInstanceState != null) {
//            boolean isHidden = savedInstanceState.getBoolean(IS_HIDDEN);
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            if (isHidden) {
//                ft.hide(this);
//            } else {
//                ft.show(this);
//            }
//            ft.commit();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        boolean hidden = isHidden();
//        outState.putBoolean(IS_HIDDEN, hidden);
        super.onSaveInstanceState(outState);
    }
}
