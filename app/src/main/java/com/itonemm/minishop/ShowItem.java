package com.itonemm.minishop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShowItem extends AppCompatActivity {

    public static  FragmentManager fragmentManager;
    public static ArrayList<String>keys=new ArrayList<String>();
    public static ListView itemlist;
    public static  LayoutInflater inflater;
    public  static Context context;
    public  static ItemAdapter adapter;
    public static ArrayList<ItemModel> itemModels=new ArrayList<ItemModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        context=getApplicationContext();
        inflater=getLayoutInflater();
        fragmentManager=getSupportFragmentManager();
        ShowItem.itemModels.clear();;
        ShowItem.keys.clear();;
        itemlist=findViewById(R.id.itemlist);
        new FireBaseDataBaseHelper().getAllItesm();;


    }

    public static void displaydata()
    {
        adapter=new ItemAdapter(itemModels);
        itemlist.setAdapter(adapter);
    }

    public void btnaddItem(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    private static class ItemAdapter extends BaseAdapter
    {
        ArrayList<ItemModel> items;

        public ItemAdapter(ArrayList<ItemModel> items) {
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=ShowItem.inflater;
            View view=inflater.inflate(R.layout.itemlistview,null);
            final  ItemModel itemModel=items.get(position);
            TextView itemlistname=view.findViewById(R.id.itemlistviewname);
            TextView itemlistsr=view.findViewById(R.id.itemlistviewsrno);
            final ImageView imageView=view.findViewById(R.id.itemlistviewimage);
            itemlistsr.setText(String.valueOf(position+1));
            LinearLayout linearLayout=view.findViewById(R.id.itemlistviewlayout);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopUp.key=ShowItem.keys.get(position);
                    PopUp.itemModel=itemModel;
                    PopUp itemeditform=new PopUp();
                    itemeditform.show(fragmentManager,"Show Edit");

                }
            });
            itemlistname.setText(itemModel.itemName);
            Glide.with(ShowItem.context)
                    .load(itemModel.itemImagePath)
                    .override(100,100)
                    .into(imageView);
            return view;
        }
    }

}
