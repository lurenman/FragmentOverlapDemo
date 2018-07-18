package com.example.dell.fragmentoverlapdemo;

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

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2018/7/17
 * 作者:baiyang
 */
public class MethodThreeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mMainTabGroup;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        setTitle("方法三");
        mMainTabGroup = (RadioGroup) findViewById(R.id.main_tab_group);
        mMainTabGroup.setOnCheckedChangeListener(this);
        if (savedInstanceState != null) {
            fragmentOne = (FragmentOne) getSupportFragmentManager().findFragmentByTag(FragmentOne.class.getSimpleName());
            fragmentTwo = (FragmentTwo) getSupportFragmentManager().findFragmentByTag(FragmentTwo.class.getSimpleName());
            fragmentThree = (FragmentThree) getSupportFragmentManager().findFragmentByTag(FragmentThree.class.getSimpleName());
            //这块一定要 这样写如果我们进入界面只添加了fragmentOne 那就会引起空指针异常
            if (fragmentOne != null) {
                fragmentList.add(fragmentOne);
            } else {
                fragmentOne = new FragmentOne();
                fragmentList.add(fragmentOne);
            }
            if (fragmentTwo != null) {
                fragmentList.add(fragmentTwo);
            } else {
                fragmentTwo = new FragmentTwo();
                fragmentList.add(fragmentTwo);
            }
            if (fragmentThree != null) {
                fragmentList.add(fragmentThree);
            } else {
                fragmentThree = new FragmentThree();
                fragmentList.add(fragmentThree);
            }
        } else {
            initFragment();
        }
        ((RadioButton) mMainTabGroup.getChildAt(0)).setChecked(true);

    }

    private void initFragment() {
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
        fragmentList.add(fragmentOne);
        fragmentList.add(fragmentTwo);
        fragmentList.add(fragmentThree);
    }

    /**
     * 既然恢复的时候走两遍我们就控制一下
     */
    private int mCurrentCheckedId;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (mCurrentCheckedId != checkedId)
            mCurrentCheckedId = checkedId;
        else {
            return;
        }
        switch (checkedId) {
            case R.id.rb_one:
                addContentLayout(fragmentOne);
                break;
            case R.id.rb_two:
                addContentLayout(fragmentTwo);
                break;
            case R.id.rb_three:
                addContentLayout(fragmentThree);
                break;
            default:
                break;
        }
    }

    private void addContentLayout(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (Fragment f : fragmentList) {
            if (f != fragment && f.isAdded()) {
                ft.hide(f);
            }
        }
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
}
