package com.example.arraylist_add;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView ;
    ArrayList<Person> persons;
    LinearLayout layout;
    Button button;
    Button button2;
    EditText Name;
    EditText Id;
    EditText Number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        layout = findViewById(R.id.layout);
        button = findViewById(R.id.button);
        button2= findViewById(R.id.button2);
        Name = findViewById(R.id.Name);
        Id = findViewById(R.id.Id);
        Number= findViewById(R.id.Number);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=
                        new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("HELLO");

                //클릭한 것이 어떤 것인지도 함께 출력해
                builder.setMessage("Are you sure to delete your information?:"
                        + persons.get(position).getId());

                 builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         persons.remove(position);



                     }
                 });
                 builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                     }
                 });
                 builder.show();
            }
        });
    }





   class PersonAdapter extends BaseAdapter{

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
           return datas.get(position);
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {

            View view = null;
           LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view = inflater.inflate(
                   R.layout.person,
                   layout,
                   true
           );

           TextView tx_name=view.findViewById(R.id.tx_name);
           TextView tx_id=view.findViewById(R.id.tx_id);
           TextView tx_number=view.findViewById(R.id.tx_number);
           Person p = datas.get(position);

           tx_name.setText(p.getName());
           tx_id.setText(p.getId());
           tx_number.setText(p.getNumber()+"");


           return view;
       }
   }

    public void setList(ArrayList<Person> persons){
        PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);

    }

    public void getData(){
        persons = new ArrayList<>();
        persons.add(new Person("Lee","id01",5959));
        persons.add(new Person("fee","010",2525));
        persons.add(new Person("ree","012",1313));
        persons.add(new Person("eee","013",2555));
        persons.add(new Person("wee","014",2537));
        setList(persons);


    }

    public void bt(View v){

        getData();
    }




}