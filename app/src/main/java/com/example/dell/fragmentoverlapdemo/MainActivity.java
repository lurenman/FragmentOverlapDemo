
package com.example.dell.fragmentoverlapdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private Button bt_first;
    private Button bt_two;
    private Button bt_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        bt_first = (Button) findViewById(R.id.bt_first);
        bt_two = (Button) findViewById(R.id.bt_two);
        bt_three = (Button) findViewById(R.id.bt_three);

        bt_first.setOnClickListener(this);
        bt_two.setOnClickListener(this);
        bt_three.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_first:
                startActivity(new Intent(mContext, MethodOneActivity.class));
                break;
            case R.id.bt_two:
                startActivity(new Intent(mContext, MethodTwoActivity.class));
                break;
            case R.id.bt_three:
                startActivity(new Intent(mContext, MethodThreeActivity.class));
                break;
        }
    }
}
