package com.example.arraylist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    LinearLayout layout;

    ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        layout = findViewById(R.id.layout);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder=
                        new AlertDialog.Builder(MainActivity.this);

                LayoutInflater layoutInflater = getLayoutInflater();
                View dview = layoutInflater.inflate(R.layout.dialog,
                        (ViewGroup) findViewById(R.id.dlayout)
                );
                ImageView dimg = dview.findViewById(R.id.imageView2);
                dimg.setImageResource(persons.get(position).getImg());
                builder.setView(dview);
                builder.setTitle("HELLO");
                builder.setMessage("Name:"+persons.get(position).getName());
                builder.show();
            }
        });


    }

    class PersonAdapter extends BaseAdapter{

        ArrayList<Person> datas;
        public PersonAdapter(ArrayList<Person> datas){
            this.datas=datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = null;
            LayoutInflater inflater= (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(
                    R.layout.person,
                    layout,
                    true
            );

            ImageView im = view.findViewById(R.id.imageView);
            TextView tx_id= view.findViewById(R.id.tx_id);
            TextView tx_name= view.findViewById(R.id.tx_name);
            TextView tx_age=view.findViewById(R.id.tx_age);
            Person p = datas.get(position);

            im.setImageResource(p.getImg());
            tx_id.setText(p.getId());
            tx_name.setText(p.getName());
            tx_age.setText(p.getAge());


            return view;
        }
    }

    public void setList(ArrayList<Person> persons){
        PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);

    }

    public void getdata(){

        persons = new ArrayList<>();
        persons.add(new Person(R.drawable.m1,"Lee","010-1111","20"));
        persons.add(new Person(R.drawable.m2,"gee","010-1111","21"));
        persons.add(new Person(R.drawable.m3,"qee","010-1111","23"));
        persons.add(new Person(R.drawable.m4,"wee","010-1111","24"));
        persons.add(new Person(R.drawable.m5,"eee","010-1111","25"));
        persons.add(new Person(R.drawable.m6,"ree","010-1111","26"));
        persons.add(new Person(R.drawable.m7,"tee","010-1111","27"));
        persons.add(new Person(R.drawable.m8,"vee","010-1111","28"));
        persons.add(new Person(R.drawable.m9,"cee","010-1111","29"));
        setList(persons);

    }

    public void ckbt(View v){

        getdata();

    }
}