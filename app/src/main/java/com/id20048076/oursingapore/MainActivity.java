package com.id20048076.oursingapore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button btnInsert, btn5Stars;
    ListView lv;
    ArrayList<Island> islandList;
    CustomAdapter adapter;
    int requestCode = 9;
    ArrayAdapter<Island> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        btn5Stars = findViewById(R.id.button4);
        btnInsert = findViewById(R.id.button5);


//        setTitle(getTitle().toString() + " ~ " +  getResources().getText(R.string.title_activity_second));

        islandList =  new ArrayList<>();
        adapter = new CustomAdapter(this, R.layout.row, islandList);
        lv.setAdapter(adapter);

//        arrayAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                islandList );
//
//        lv.setAdapter(arrayAdapter);
        update();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("island", islandList.get(position));
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                islandList.clear();
                islandList.addAll(dbh.getAllIslandsByStars(5));
                adapter.notifyDataSetChanged();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.editorinsert, null);

                final EditText etName = viewDialog.findViewById(R.id.editTextTextPersonName2);
                final EditText etDescription = viewDialog.findViewById(R.id.editTextTextPersonName3);
                final EditText etArea = viewDialog.findViewById(R.id.editTextTextPersonName4);
                final RatingBar ratingBar = viewDialog.findViewById(R.id.ratingBar);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Insert~");
                myBuilder.setPositiveButton("INSERT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = etName.getText().toString();
                        String description = etDescription.getText().toString();
                        int area = Integer.parseInt(etArea.getText().toString());
                        float stars = ratingBar.getRating();

                        System.out.println(name+" "+description+" "+area+" "+stars);
                        DBHelper dbh = new DBHelper(MainActivity.this);
                        dbh.insertIsland(name, description, area, stars);
                        update();
                    }
                });
                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == this.requestCode && resultCode == RESULT_OK){
//            DBHelper dbh = new DBHelper(this);
//            islandList.clear();
//            islandList.addAll(dbh.getAllIslands());
//            dbh.close();
//            adapter.notifyDataSetChanged();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    public void update(){
        DBHelper dbh = new DBHelper(MainActivity.this);
        islandList.clear();
        islandList.addAll(dbh.getAllIslands());

        adapter.notifyDataSetChanged();

//        arrayAdapter.notifyDataSetChanged();

    }
    public void onResume() {
        super.onResume();
        update();
    }
}