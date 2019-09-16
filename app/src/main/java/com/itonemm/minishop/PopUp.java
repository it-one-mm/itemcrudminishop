package com.itonemm.minishop;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PopUp extends DialogFragment {
    public static  ItemModel itemModel;
    public static String key;
    EditText itemname;
    EditText itemimagepath;
    Button btnsave,btndelete;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.itemeditform,container,false);
        itemname=view.findViewById(R.id.edititemname);
        itemimagepath=view.findViewById(R.id.edititempath);
        itemname.setText(itemModel.itemName);
        itemimagepath.setText(itemModel.itemImagePath);
        btnsave=view.findViewById(R.id.editsaveitem);
        btndelete=view.findViewById(R.id.deleteitem);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemModel.itemName=itemname.getText().toString().trim();
                itemModel.itemImagePath=itemimagepath.getText().toString().trim();
                new FireBaseDataBaseHelper().updateItem(itemModel,key);
                itemname.setText("");
                itemimagepath.setText("");
                dismiss();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FireBaseDataBaseHelper().delete(key);
                itemname.setText("");
                itemimagepath.setText("");
                dismiss();

            }
        });

        return view;
    }
}
