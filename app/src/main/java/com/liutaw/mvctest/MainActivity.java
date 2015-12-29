package com.liutaw.mvctest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    ScrollTextVertical scrollTextVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        scrollTextVertical = (ScrollTextVertical) this.findViewById(R.id.scrolltextvertical);
        List<String> data = new ArrayList<>();
        data.add("This is from LiuTaw's GitHub!");
        data.add("Try to click me!");
        data.add("3333333333333333");
        data.add("4444444444444444");
        data.add("5555555555555555");
        data.add("6666666666666666");
        scrollTextVertical.setData(data);
        scrollTextVertical.setListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //give you position
                Toast.makeText(getApplicationContext(), "you click position" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.btn_start)
    public void btn_start() {
        scrollTextVertical.startTurning();
    }

    @OnClick(R.id.btn_end)
    public void btn_end() {
        scrollTextVertical.pauseTurning();
    }

    @OnClick(R.id.btn_stop)
    public void btn_stop() {
        scrollTextVertical.stopTurnning();
    }

    @OnClick(R.id.btn_replace)
    public void btn_replace() {

        List<String> data = new ArrayList<>();
        data.add("new/新的1111111111111111");
        data.add("new/新的2222222222222222");
        data.add("new/新的3333333333333333");
        data.add("new/新的4444444444444444");
        data.add("new/新的5555555555555555");
        data.add("new/新的6666666666666666");
        scrollTextVertical.setData(data);
    }

    @OnClick(R.id.btn_change)
    public void btn_change() {
        List<String> data = new ArrayList<>();
        data.add("new added/我新增的数据11111111111111");
        scrollTextVertical.addData(data);
    }
}
