package com.itonemm.minishop;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    EditText itemname;
    EditText itemimagepath;
    String strItemName,strItemImagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemname=findViewById(R.id.itemname);
        itemimagepath=findViewById(R.id.itempath);


    }

    public void btnsaveitem(View view) {
        strItemName=itemname.getText().toString().trim();
        strItemImagePath=itemimagepath.getText().toString().trim();

        ItemModel itemModel=new ItemModel(strItemName,strItemImagePath);

        FireBaseDataBaseHelper dataBaseHelper=new FireBaseDataBaseHelper();
        dataBaseHelper.addItem(itemModel);
        itemname.setText("");
        itemimagepath.setText("");




    }

    public void btncancelitem(View view) {
        itemname.setText("");
        itemimagepath.setText("");
    }

    public void btnloaditem(View view) {
        Intent intent=new Intent(getApplicationContext(),ShowItem.class);
        startActivity(intent);
    }
}
