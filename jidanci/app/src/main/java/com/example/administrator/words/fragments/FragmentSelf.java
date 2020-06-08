package com.example.administrator.words.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.words.DifficultWordsBase;
import com.example.administrator.words.Explain;
import com.example.administrator.words.R;
import com.example.administrator.words.ResetUsername;
import com.example.administrator.words.WordsBase;
import com.example.administrator.words.WordsReviewBase;
import com.example.administrator.words.allWordsBase;

/**
 * Created by Administrator on 2019/4/27.
 */

public class FragmentSelf extends Fragment {

    TextView textView_wordsbase,textView_explain,textView_resetname,textView_reviewword,textView_allword,textView_difficultword ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_self,null);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView_wordsbase = view.findViewById(R.id.self_tv_words);
        textView_explain = view.findViewById(R.id.self_tv_explain);
        textView_resetname = view.findViewById(R.id.self_tv_resetname);
        textView_reviewword = view.findViewById(R.id.self_tv_reviewwords);
        textView_allword = view.findViewById(R.id.self_tv_allwords);
        textView_difficultword = view.findViewById(R.id.self_tv_difficultwords);
        //点击背诵单词库
        textView_wordsbase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WordsBase.class);
                startActivity(intent);
            }
        });

        //点击软件说明
        textView_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Explain.class);
                startActivity(intent);
            }
        });

        //点击修改用户名
        textView_resetname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResetUsername.class);
                startActivity(intent);
            }
        });

        //点击复习单词库
        textView_reviewword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WordsReviewBase.class);
                startActivity(intent);
            }
        });

        //点击单词库
        textView_allword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), allWordsBase.class);
                startActivity(intent);
            }
        });
        //点击difficult
        textView_difficultword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DifficultWordsBase.class);
                startActivity(intent);
            }
        });
    }
}
