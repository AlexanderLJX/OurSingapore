package com.id20048076.oursingapore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText etID, etName, etDescription, etArea;
    Button btnCancel, btnUpdate, btnDelete;
    RadioGroup rg;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = (Button) findViewById(R.id.button3);
        btnDelete = (Button) findViewById(R.id.button2);
        btnUpdate = (Button) findViewById(R.id.button);
        etID = (EditText) findViewById(R.id.editTextTextPersonName);
        etName = (EditText) findViewById(R.id.editTextTextPersonName2);
        etDescription = (EditText) findViewById(R.id.editTextTextPersonName3);
        etArea = (EditText) findViewById(R.id.editTextTextPersonName4);
        ratingBar = findViewById((R.id.ratingBar));

        Intent i = getIntent();
        final Island currentIsland = (Island) i.getSerializableExtra("island");

        etID.setText(currentIsland.getId()+"");
        etName.setText(currentIsland.getName());
        etDescription.setText(currentIsland.getDescription());
        etArea.setText(currentIsland.getArea()+"");
        ratingBar.setRating(currentIsland.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity2.this);
                currentIsland.setName(etName.getText().toString().trim());
                currentIsland.setDescription(etDescription.getText().toString().trim());
                currentIsland.setArea(Integer.parseInt(etArea.getText().toString()));
                currentIsland.setStars(ratingBar.getRating());
                int result = dbh.updateIsland(currentIsland);
                if (result>0){
                    Toast.makeText(MainActivity2.this, "Island updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(MainActivity2.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity2.this);
                int result = dbh.deleteIsland(currentIsland.getId());
                if (result>0){
                    Toast.makeText(MainActivity2.this, "Island deleted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(MainActivity2.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}