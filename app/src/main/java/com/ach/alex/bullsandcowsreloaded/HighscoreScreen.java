package com.ach.alex.bullsandcowsreloaded;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class HighscoreScreen extends AppCompatActivity {

    private ListView listView;
    private Button backButton;

    ArrayList<String> values;
    private String filename = "scores.data";

    ArrayList<ArrayList<String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_screen);

        readScores();

        //beta
        listView = (ListView) findViewById(R.id.highscore_list);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, list);
        listView.setAdapter(listViewAdapter);
//        // list
//        listView = (ListView) findViewById(R.id.highscore_list);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, values);
//
//        listView.setAdapter(adapter);

        // back button
        backButton = (Button) findViewById(R.id.highscore_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void readScores() {

        values = new ArrayList<>();
        list = new ArrayList<>();
        FileInputStream input = null;
        File file = new File(getFilesDir(), filename);

        try {
            if (!file.exists())
                file.createNewFile();
            input = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] components = line.split(" ");
//                String name = components[0];
//                Float score = Float.parseFloat(components[1]);
                ArrayList<String> rowValue = new ArrayList<>();
                rowValue.add(components[0]);
                rowValue.add(components[1]);
                list.add(rowValue);
//                values.add(name + " : " + score.toString());
            }

            bufferedReader.close();
            isr.close();
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
