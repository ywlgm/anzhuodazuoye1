package com.example.administrator.words;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.words.database.DBOpenHelper;
import com.example.administrator.words.fragments.FragmentRecite;
import com.example.administrator.words.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsBase extends AppCompatActivity {

    DBOpenHelper dbOpenHelper;
    ListView listView;
    List<Map<String,Object>> list;
    Mdialog mdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_base);

        dbOpenHelper = new DBOpenHelper(WordsBase.this,"tb_dict",null,1);
        final ArrayList<Word> words = dbOpenHelper.getWords();
        listView = (ListView) findViewById(R.id.list);
        updateList();
    }


    private void updateList(){
        final ArrayList<Word> words = dbOpenHelper.getWords();
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0;i < words.size() ;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",words.get(i).id+".");
            map.put("word", words.get(i).word);
            map.put("translate", words.get(i).translate);
            list.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(WordsBase.this,list, R.layout.list_item1,
                new String[]{"id","word","translate"},new int[]{R.id.id,R.id.word,R.id.translate});

        listView.setAdapter(simpleAdapter);
    }


}
