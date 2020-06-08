package com.example.administrator.words;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    //读取数据
    private SharedPreferences sharedPreferences;
    //写入数据
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.main_et_username);

        button = (Button) findViewById(R.id.main_btn);

        //实例化sharedPreferences，参数：文件名称，模式（通常使用PRIVATE）
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        //实例化editor
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("user","").isEmpty() == false){
            Intent intent=new Intent();

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.setClass(MainActivity.this,FristActivity.class);

            startActivity(intent);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //putString写入数据：键值对，参数：key,value
                editor.putString("user",editText.getText().toString());
                editor.apply();
                Intent intent=new Intent();

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.setClass(MainActivity.this,FristActivity.class);
                startActivity(intent);
            }
        });
    }
}
