package com.example.dell.fragmentoverlapdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.fragmentoverlapdemo.R;

/**
 * 创建日期：2018/7/17
 * 作者:baiyang
 */
public class FragmentTwo extends BaseFragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
