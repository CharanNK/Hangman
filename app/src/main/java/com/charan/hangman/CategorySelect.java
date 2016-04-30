package com.charan.hangman;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategorySelect extends AppCompatActivity {
    ListView listView;
    String categories[] = {"Movies","Books","Sports","TV Shows","Personalities","Animals","Countries","Fashion","Dictionary","Music"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_select);

        listView = (ListView)findViewById(R.id.listView);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.single_row,categories);
        listView.setAdapter(new HangAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CategorySelect.this,GameplayActivity.class);
                startActivity(intent);
            }
        });

    }

    class SingleRow{
        String Title;
        String Description;
        int Image;

        public SingleRow(String title, String description, int image) {
            Description = description;
            Title = title;
            Image = image;
        }
    }

    class HangAdapter extends BaseAdapter{
        ArrayList<SingleRow> list;
        Context context;
        HangAdapter(Context context){
            list = new ArrayList<SingleRow>();
            this.context=context;
            Resources resources = context.getResources();
            String[] category = resources.getStringArray(R.array.category);
            String[] description = resources.getStringArray(R.array.descriptions);
            int[] images = {R.drawable.ic_movie_black_24dp,R.drawable.ic_import_contacts_black_24dp,
                    R.drawable.ic_rowing_black_24dp,R.drawable.ic_subscriptions_black_24dp,R.drawable.ic_face_black_24dp,
                    R.drawable.ic_android_black_24dp,R.drawable.ic_language_black_24dp,R.drawable.ic_local_mall_black_24dp,
                    R.drawable.ic_library_music_black_24dp,R.drawable.ic_audiotrack_black_24dp};

            for (int i=0;i<=9;i++){
                list.add(new SingleRow(category[i],description[i],images[i]));
            }
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.single_row,parent,false);

            TextView Title = (TextView)row.findViewById(R.id.textView);
            TextView Description = (TextView)row.findViewById(R.id.textView2);
            ImageView imageView = (ImageView)row.findViewById(R.id.imageView);

            SingleRow RowObj = list.get(position);
            Title.setText(RowObj.Title);
            Description.setText(RowObj.Description);
            imageView.setImageResource(RowObj.Image);
            return row;
        }
    }
}
