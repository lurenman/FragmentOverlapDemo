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
 * 刚恢复的时候默认选中0的位置onCheckedChanged会走两遍R.id.rb_one，
 * super.onSaveInstanceState(outState)方法帮我们保存了选中的位置，恢复的时候会setChecked上次退出的位置
 */
public class MethodTwoActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mMainTabGroup;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        setTitle("方法二");
        mMainTabGroup = (RadioGroup) findViewById(R.id.main_tab_group);
        mMainTabGroup.setOnCheckedChangeListener(this);
        initFragment();
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
            if (f != fragment&&f.isAdded()) {
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

    /**
     * 思路同样是阻止系统恢复Fragment state，在FragmentActivity保存所有Fragment状态前把Fragment从FragmentManager中移除掉。
     * 缺点：和方法一都是沒有保存Fragment的相关数据
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(fragmentOne);
        transaction.remove(fragmentTwo);
        transaction.remove(fragmentThree);
        transaction.commitAllowingStateLoss();
        //这个方法会帮我们保留选中位置状态
        super.onSaveInstanceState(outState);
    }
}
