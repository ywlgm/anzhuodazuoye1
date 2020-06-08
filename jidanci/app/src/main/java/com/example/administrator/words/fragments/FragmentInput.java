package com.example.administrator.words.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.words.R;
import com.example.administrator.words.database.DBAllWords;
import com.example.administrator.words.database.DBDifficultWords;
import com.example.administrator.words.database.DBOpenHelper;
import com.example.administrator.words.util.ToastUtil;

/**
 * Created by Administrator on 2019/4/26.
 */

public class FragmentInput extends Fragment {
    Button button;
    EditText editText_word,editText_translate;
    DBOpenHelper dbOpenHelper;
    DBAllWords allWords;
    DBDifficultWords dbDifficultWords;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input,null);
        dbOpenHelper = new DBOpenHelper(getActivity(),"tb_dict",null,1);
        allWords = new DBAllWords(getActivity(),"tb_words",null,1);
        dbDifficultWords = new DBDifficultWords(getActivity(),"tb_dwords",null,1);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText_word = view.findViewById(R.id.input_et_words);
        editText_translate = view.findViewById(R.id.input_et_translate);
        button = view.findViewById(R.id.input_btn);

        //点击录入按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String word = editText_word.getText().toString();
                    String translate = editText_translate.getText().toString();
                    if(word.endsWith("ion")){
                        translate = "n. "+editText_translate.getText().toString();
                    }else if(word.endsWith("ness")){
                        translate = "n. "+editText_translate.getText().toString();
                    }else if(word.endsWith("ment")){
                        translate = "n. "+editText_translate.getText().toString();
                    }else if(word.endsWith("ty")){
                        translate = "n. "+editText_translate.getText().toString();
                    }else if(word.endsWith("acy")){
                        translate = "n. "+editText_translate.getText().toString();
                    }else if(word.endsWith("ance")){
                        translate = "n. "+editText_translate.getText().toString();
                    }else if(word.endsWith("ence")){
                        translate = "n. "+editText_translate.getText().toString();
                    }else if(word.endsWith("ice")){
                        translate = "n. "+editText_translate.getText().toString();
                    }else if(translate.endsWith("的")){
                        translate = "adj. "+editText_translate.getText().toString();
                    }else if (translate.endsWith("地")){
                        translate = "adv. "+editText_translate.getText().toString();
                    }else{
                        translate = editText_translate.getText().toString();
                    }
                    if (word.isEmpty() || editText_translate.getText().toString().isEmpty()){
                        ToastUtil.showMsg(getActivity(),"请输入数据");
                    }else{
                        if(!allWords.isWordExist(word)) {
                            dbOpenHelper.writeData(word, translate);
                            allWords.writeData( word, translate);
                            ToastUtil.showMsg(getActivity(), "录入成功");
                            editText_translate.setText("");
                            editText_word.setText("");
                            editText_word.requestFocus();
                        }
                        else{
                            dbOpenHelper.writeData(word, translate);
                            ToastUtil.showMsg(getActivity(),"单词已存在！");
                            if(dbDifficultWords.isWordExist(word))
                                ToastUtil.showMsg(getActivity(),"单词已存在！");
                            else
                                dbDifficultWords.writeData(word,translate);
                            editText_translate.setText("");
                            editText_word.setText("");
                            editText_word.requestFocus();
                        }
                }
            }
        });
    }
}


