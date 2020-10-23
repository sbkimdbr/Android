package com.example.p426_recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //1.arraylist



    ListView listView;
    ArrayList<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);



    }
    //3. 받은 데이터를 list로 받아온다
    //4. adaptoer을 사용해서 list 받아오는 걸로 작
   public void setList(final ArrayList<String> datas){


        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        datas

                );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           //4.리스트 항목 클릭시 이벤트 발생
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(MainActivity.this,
                        ""+position,Toast.LENGTH_SHORT).show();



                AlertDialog.Builder builder=
                        new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("HELLO");

                //클릭한 것이 어떤 것인지도 함께 출력해
                builder.setMessage("Are you sure to delete this message?:"
                + datas.get(position));


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datas.remove(position);

                        //어떤 변화가 있을 때 자동으로 새로고침 해주는 구문
                        adapter.notifyDataSetChanged();
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

    //2.데이터를 가져온다
    public void getData(){
            datas = new ArrayList<>();
            for(int i=0;i<=20;i++){
            datas.add("Item:"+i);
        }
            setList(datas);
    }

    public void ckbt(View v){
        Toast.makeText(this, "datas", Toast.LENGTH_SHORT).show();

        //데이터를 가져와서 데이터를 뿌리겠다.
        //데이터들의 묶음->어레이리스

        getData();
    }

}