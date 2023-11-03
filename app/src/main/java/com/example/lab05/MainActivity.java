package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void cau1(View view){
        Toast.makeText(getApplicationContext(), "Câu 1", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, Calculator.class);
        startActivity(intent);
    }
    public void cau2(View view){
        Toast.makeText(getApplicationContext(), "Câu 2", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, Hienthithongtin.class);
        startActivity(intent);
    }

}