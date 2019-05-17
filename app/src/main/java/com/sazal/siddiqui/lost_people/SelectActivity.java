package com.sazal.siddiqui.lost_people;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectActivity extends AppCompatActivity {

    @BindView(R.id.appCompatButtonLost)
    AppCompatButton ms_AppCompatButtonLost;
    @BindView(R.id.appCompatButtonFound)
    AppCompatButton ms_AppCompatButtonFound;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        intent = new Intent(SelectActivity.this, MainActivity.class);
    }

    @OnClick({R.id.appCompatButtonLost, R.id.appCompatButtonFound})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.appCompatButtonLost:
                intent.putExtra("isLost", true);
                startActivity(intent);
                break;
            case R.id.appCompatButtonFound:
                intent.putExtra("isLost", false);
                startActivity(intent);
                break;
        }
    }
}
