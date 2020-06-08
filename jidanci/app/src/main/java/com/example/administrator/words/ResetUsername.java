package com.example.administrator.words;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.words.util.ToastUtil;

import org.w3c.dom.Text;

public class ResetUsername extends AppCompatActivity {
    Button button;
    EditText editText;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    FristActivity fristActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_username);
        button = (Button) findViewById(R.id.resetusername_btn);
        editText = (EditText) findViewById(R.id.resetusername_et);
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        editor = sharedPreferences.edit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user",editText.getText().toString());
                editor.apply();
                ToastUtil.showMsg(ResetUsername.this,"修改成功");
            }
        });
    }
}
