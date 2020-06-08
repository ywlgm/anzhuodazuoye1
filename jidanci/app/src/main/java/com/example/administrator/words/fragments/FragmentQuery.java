package com.example.administrator.words.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.words.R;
import com.example.administrator.words.database.DBAllWords;
import com.example.administrator.words.database.DBDifficultWords;
import com.example.administrator.words.database.DBOpenHelper;
import com.example.administrator.words.database.DBReviewWords;
import com.example.administrator.words.model.Basic;
import com.example.administrator.words.model.Word;
import com.example.administrator.words.util.JsonUitl;
import com.example.administrator.words.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/4/26.
 */

public class FragmentQuery extends Fragment implements View.OnClickListener{

    Context context = getActivity();

    private String TAG = "FragmentQuery";

    EditText editText;
    TextView textView;
    Button button,button_input;
    ImageView imageView;

    DBOpenHelper dbOpenHelper;
    DBAllWords allWords;
    DBDifficultWords dbDifficultWords;

    //2.创建handler
    private Handler handler = new Handler(){
        //4.handler获取消息进行处理
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //1.获取对象
            Word word = (Word) msg.obj;
            //2.获取数据

            Basic basic = null;
            String explains = null;
            try {
                basic = word.getBasic();
                explains = basic.getStrings(basic.getExplains());
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtil.showMsg(getActivity(),"查无此词！");
            }

            //3.在界面显示
            textView.setText(explains);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queryword,null);
        dbOpenHelper = new DBOpenHelper(getActivity(),"tb_dict",null,1);
        allWords = new DBAllWords(getActivity(),"tb_words",null,1);
        dbDifficultWords = new DBDifficultWords(getActivity(),"tb_dwords",null,1);
        return view;
    }

    //创建时显示
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.queryword_et);
        textView = view.findViewById(R.id.queryword_tv);
        button = view.findViewById(R.id.queryword_bt);
        button_input = view.findViewById(R.id.queryword_bt_input);
        imageView = view.findViewById(R.id.queryword_iv);

        button.setOnClickListener(this);
        imageView.setOnClickListener(this);
        button_input.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.queryword_bt:
                String word = editText.getText().toString();
                //调用上网查词方法
                queryWord(word);
                break;
            case R.id.queryword_iv:
                editText.setText("");
                break;
            case R.id.queryword_bt_input:
                String word1 = editText.getText().toString();
                String translate = textView.getText().toString();
                if(word1==null || translate!="这里显示查询结果")
                    ToastUtil.showMsg(getActivity(),"出现错误！");
                else {
                    dbOpenHelper.writeData(word1, translate);
                    ToastUtil.showMsg(getActivity(), "已添加到单词库！");
                    if (allWords.isWordExist(word1)) {
                        ToastUtil.showMsg(getActivity(), "单词已存在，并添加到单词库！");
                        if (!dbDifficultWords.isWordExist(word1))
                            dbDifficultWords.writeData(word1, translate);
                    } else {
                        allWords.writeData(word1, translate);
                    }
                }
        }
    }

    /**
     * 3.实现上网查词
     1.创建OkClient和Request对象
     2.创建Call对象
     3.重写Call对象的enCall方法
     1.获取响应数据
     2.封装成json对象
     3.转为java对象
     4.发送message给handler
     * @param s 要查询的单词
     */
    public void queryWord(String s) {
        String url = "http://fanyi.youdao.com/openapi.do?keyfrom=lewe518&key=70654389&type=data&doctype=json&version=1.1&q=" + s;
        //1.创建OkClient和Request对象
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        //2.创建Call对象
        Call call = okHttpClient.newCall(request);
        //3.重写Call对象的enqueue方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //1.获取响应数据
                String str = response.body().string();
                Log.d(TAG, "onResponse: " + str);
                try {
                    //2.封装成json对象
                    JSONObject jsonObject = new JSONObject(str);
                    //3.转为java对象
                    Word word = (Word) JsonUitl.stringToObject(jsonObject.toString(), Word.class);
                    //4.创建message,包裹信息
                    Message message = new Message();
                    message.obj = word;
                    //5.发送message给handler
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
