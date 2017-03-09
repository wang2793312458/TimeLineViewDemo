package com.example.timelineviewdemo;



import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.timelineviewdemo.TimeLineView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements TimeLineView.HeightLineProvider {
    private TimeLineView tlv;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tlv = (TimeLineView) findViewById(R.id.tlv);
        tlv.setHeightLineProvider(this);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher);
        tlv.setHeightBitmap(bitmapDrawable.getBitmap());
    }

    @Override
    public boolean isHeightLine(int position) {
        if (position % 4 == 0) {
            return true;
        }
        return false;
    }

    private void addItem() {
        View v = LayoutInflater.from(this).inflate(R.layout.item_test, tlv, false);
        ((TextView) v.findViewById(R.id.tx_action)).setText(tlv.getChildCount() + "探访了你");
        ((TextView) v.findViewById(R.id.tx_action_time)).setText(sdf.format(new Date()));
        ((TextView) v.findViewById(R.id.tx_action_status)).setText("查看");
        tlv.addView(v);
    }

    public void addView(View v) {
        addItem();
    }

    public void removeView(View v) {
        if (tlv.getChildCount() != 0) {
            tlv.removeViewAt(tlv.getChildCount() - 1);
        }
    }
}
