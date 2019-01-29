package lyq.com.livedatabusdemo;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //创建一个订阅者
        LiveDataBusBeta.get()
                .with("key_word",String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Toast.makeText(Main2Activity.this,"当前B页面消息  Normal get thi msg is : " + s,Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "当前B页面消息  Normal get thi msg is : " + s);
                    }
                });


        //主线程发送消息
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataBusBeta.get()
                        .with("key_word",String.class)
                        .setValue("send msg from MainThread");
            }
        });

        findViewById(R.id.btn_thread_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LiveDataBusBeta.get()
                                .with("key_word",String.class)
                                .postValue("send msg from  subThread");
                    }
                }).start();
            }
        });

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataBusBeta.get()
                        .with("key_forever",String.class)
                        .setValue("send msg from  B-A");
            }
        });

    }
}
