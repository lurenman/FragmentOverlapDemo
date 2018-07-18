package com.example.dell.fragmentoverlapdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dell.fragmentoverlapdemo.fragment.FragmentOne;
import com.example.dell.fragmentoverlapdemo.fragment.FragmentThree;
import com.example.dell.fragmentoverlapdemo.fragment.FragmentTwo;

/**
 * 创建日期：2018/7/17
 * 作者:baiyang
 */
public class MethodOneActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private Fragment mOldFragment;
    private RadioGroup mMainTabGroup;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        setTitle("方法一");
        mMainTabGroup = (RadioGroup) findViewById(R.id.main_tab_group);
        mMainTabGroup.setOnCheckedChangeListener(this);
        //  mMainTabGroup.check(R.id.rb_one);  //开始会引起onCheckedChanged走两遍
        ((RadioButton) mMainTabGroup.getChildAt(0)).setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_one:
                if (fragmentOne == null) {
                    fragmentOne = new FragmentOne();
                }
                addContentLayout(fragmentOne);
                break;
            case R.id.rb_two:
                if (fragmentTwo == null) {
                    fragmentTwo = new FragmentTwo();
                }
                addContentLayout(fragmentTwo);
                break;
            case R.id.rb_three:
                if (fragmentThree == null) {
                    fragmentThree = new FragmentThree();
                }
                addContentLayout(fragmentThree);
                break;
            default:
                break;
        }
    }


    private void addContentLayout(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mOldFragment != null && mOldFragment.isAdded()) {
            ft.hide(mOldFragment);
        }
        mOldFragment = fragment;
        if (fragment.isAdded()) {
            if (fragment.isHidden()) {
                ft.show(fragment);
            }
        } else {
            ft.add(R.id.main_content, fragment, fragment.getClass().getSimpleName());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
        ft.commit();
    }

    /**
     * 通过注释掉这句话，这样主 Activity 因为种种原因被回收的时候就不会保存之前的 fragment state
     *
     * @param outState
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }
}
