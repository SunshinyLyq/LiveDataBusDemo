package lyq.com.livedatabusdemo;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Observer mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });


        findViewById(R.id.btn_send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataBusBeta.get()
                        .with("key_forever")
                        .setValue("hello sunshiny");
            }
        });

        //给未启动的Activity发送消息
        findViewById(R.id.btn_send_activity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataBusBeta.get()
                        .with("key_word")
                        .setValue("hello sunshiny");
            }
        });

        //定义消息订阅者
        mObserver = new Observer<String>() {

            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this,"A-A forever get thi msg is : " + s,Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onChanged:  A-A forever get thi msg is : "+s);
            }
        };

        LiveDataBusBeta.get()
                .with("key_forever",String.class)
                .observe(this,mObserver);
    }

}
