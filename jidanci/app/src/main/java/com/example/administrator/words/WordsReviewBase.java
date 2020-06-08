package com.example.administrator.words;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.words.database.DBReviewWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsReviewBase extends AppCompatActivity {

    DBReviewWords dbOpenHelper;
    ListView listView;
    List<Map<String,Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_review_base);
        dbOpenHelper = new DBReviewWords(WordsReviewBase.this,"tb_dictrecite",null,1);
        ArrayList<Word> words = dbOpenHelper.getWords();
        listView = (ListView) findViewById(R.id.list1);
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0;i < words.size() ;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",words.get(i).id+".");
            map.put("word", words.get(i).word);
            map.put("translate", words.get(i).translate);
            list.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(WordsReviewBase.this,list, R.layout.list_item1,
                new String[]{"id","word","translate"},new int[]{R.id.id,R.id.word,R.id.translate});

        listView.setAdapter(simpleAdapter);
    }
}
