package com.example.administrator.words;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.words.database.DBDifficultWords;
import com.example.administrator.words.database.DBOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifficultWordsBase extends AppCompatActivity {

    DBDifficultWords dbDifficultWords;
    ListView listView;
    List<Map<String,Object>> list;
    Mdialog mdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficultwords_base);

        dbDifficultWords = new DBDifficultWords(DifficultWordsBase.this,"tb_dwords",null,1);
        final ArrayList<Word> words = dbDifficultWords.getWords();
        listView = (ListView) findViewById(R.id.list);
        updateList();
    }

    //列表的显示
    private void updateList(){
        final ArrayList<Word> words = dbDifficultWords.getWords();
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0;i < words.size() ;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",words.get(i).id+".");
            map.put("word", words.get(i).word);
            map.put("translate", words.get(i).translate);
            map.put("counts",words.get(i).counts);
            list.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(DifficultWordsBase.this,list, R.layout.list_item,
                new String[]{"id","word","translate","counts"},new int[]{R.id.id,R.id.word,R.id.translate,R.id.counts});

        listView.setAdapter(simpleAdapter);
    }

}
