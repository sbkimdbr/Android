package com.example.p427;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Person> persons;
    ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
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
                dimg.setImageResource(persons.get(position).getId());
                builder.setView(dview);
                builder.setTitle("HELLO");

                //클릭한 것이 어떤 것인지도 함께 출력해
                builder.setMessage("Name"+persons.get(position).getName());


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //어떤 변화가 있을 때 자동으로 새로고침 해주는 구문

                    }
                });


                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });
    }

    //우리만의 어댑터 만들어야한다.
   //PersonAdapter라는 함수 이름으로 만듦
    class PersonAdapter extends BaseAdapter{

        //adapter에 arraylist 데이터 넣어
        ArrayList<Person> datas;

        public PersonAdapter(ArrayList<Person> datas){
            this.datas = datas;

        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            //각각의 데이터를 하나씩 꺼낸
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        //몇번 째 데이터를 요청하면 화면을 보여 준다-> 첫번째 화면줄래? 두번째 화면줄래? n번째에 해당하는 데이터를 화면에 맵핑해서 리
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = null;
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //ListView의 한 줄을 의미
            view = inflater.inflate(
                    R.layout.person,
                    container,
                    true
            );
            ImageView im = view.findViewById(R.id.imageView);
            TextView tx_name= view.findViewById(R.id.tx_name);
            TextView tx_phone= view.findViewById(R.id.tx_phone);
            Person p = datas.get(position);

            im.setImageResource(p.getId());
            tx_name.setText(p.getName());
            tx_phone.setText(p.getPhone());

            return view;
        }
    }

    public void setList(ArrayList<Person>persons){

        PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);
    }

    public void getData(){

        persons = new ArrayList<>();
        persons.add(new Person(R.drawable.m1,"Lee","010-1111"));
        persons.add(new Person(R.drawable.m2,"gee","010-1111"));
        persons.add(new Person(R.drawable.m3,"qee","010-1111"));
        persons.add(new Person(R.drawable.m4,"wee","010-1111"));
        persons.add(new Person(R.drawable.m5,"eee","010-1111"));
        persons.add(new Person(R.drawable.m6,"ree","010-1111"));
        persons.add(new Person(R.drawable.m7,"tee","010-1111"));
        persons.add(new Person(R.drawable.m8,"vee","010-1111"));
        persons.add(new Person(R.drawable.m9,"cee","010-1111"));
        setList(persons);


    }

    public void ckbt(View v){
        getData();
    }
}