package com.example.quiz4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,id,uni,cgpa;
    Button btnInsert,btnShow,btnDelete,btnUpdate;
    DBHelper db;
    TextView tvshowData;
    StringBuffer myBuffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = name.getText().toString().trim();
                String studentId = id.getText().toString().trim();
                String studentUni = uni.getText().toString().trim();
                String studentCgpa = cgpa.getText().toString().trim();
                Boolean checkInsert= db.insertData(studentName,studentId,studentCgpa,studentUni);
                if(checkInsert==true)
                {
                    Toast.makeText(MainActivity.this, "Data Inserted Succesfully", Toast.LENGTH_SHORT).show();
                    setClear();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = name.getText().toString().trim();
                String studentId = id.getText().toString().trim();
                String studentUni = uni.getText().toString().trim();
                String studentCgpa = cgpa.getText().toString().trim();
                Boolean checkUpdate= db.updatestudentData(studentName,studentId,studentCgpa,studentUni);
                if(checkUpdate==true)
                {
                    Toast.makeText(MainActivity.this, "Data Updated Succesfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data Not Updated Succesfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = name.getText().toString().trim();
                Boolean checkDelete= db.deleteData(studentName);
                if(checkDelete==true)
                {
                    Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data Not Deleted Succesfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = db.getData();
                myBuffer = new StringBuffer();
                while(result.moveToNext() && !result.isAfterLast())
                {
                    myBuffer.append("ID: "+result.getString(0) +"\n");
                    myBuffer.append("Name: " + result.getString(1)+"\n");
                    myBuffer.append("Cgpa: " + result.getString(2)+"\n");
                    myBuffer.append("University: " + result.getString(3)+"\n\n");
                }
                DialogBox();

            }
        });
    }
    private  void init()
    {
        name = findViewById(R.id.etName);
        id = findViewById(R.id.etID);
        uni = findViewById(R.id.etUniversity);
        cgpa= findViewById(R.id.etCGPA);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        btnDelete= findViewById(R.id.btnDelete);

        btnUpdate = findViewById(R.id.btnUpdate);
        db = new DBHelper(this);
    }
    private void setClear()
    {
        name.setText("");
        id.setText("");
        uni.setText("");
        cgpa.setText("");
    }
    private void DialogBox()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.students_db_removebg_preview);
        builder.setCancelable(true);
        builder.setTitle("Students Data");
        builder.setMessage(myBuffer.toString());
        builder.show();
    }
}