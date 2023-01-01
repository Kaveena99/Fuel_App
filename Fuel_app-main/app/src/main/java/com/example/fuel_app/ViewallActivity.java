package com.example.fuel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
//this class is to handle all business logics
public class ViewallActivity extends AppCompatActivity {
    ListView typeList;
    FuelTypeBasedAdapter listAdapter;
    String Username,usertype;
    static ViewallActivity INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        INSTANCE=this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        Intent intent = this.getIntent();
        Username = intent.getStringExtra("IDDU");
        typeList=(ListView) findViewById(R.id.newListView);
        FueltypeDetailsService fueltypeDetailsService=new FueltypeDetailsService(ViewallActivity.this);

        fueltypeDetailsService.getAllTypes(new FueltypeDetailsService.VolleyResponseListener() {
                @Override
                public void onResponse (ArrayList < FuelTypeModel > typeModel) {
                System.out.println("Type Data ss" + typeModel);

                listAdapter = new FuelTypeBasedAdapter(getApplicationContext(), typeModel);
                typeList.setAdapter(listAdapter);
                typeList.setClickable(true);
                typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        System.out.println("Clicked One" + i);
                        Intent intent = new Intent(getApplicationContext(), ViewcountActivity.class);
                        intent.putExtra("ID", typeModel.get(i).getStationID());
                        intent.putExtra("ATime", typeModel.get(i).getArrivalTime());
                        intent.putExtra("Pet92", typeModel.get(i).getPetrol92());
                        intent.putExtra("Pet95", typeModel.get(i).getPetrol95());
                        intent.putExtra("Dies", typeModel.get(i).getDiesel());
                        intent.putExtra("SuperDies", typeModel.get(i).getSuperDiesel());
                        intent.putExtra("EndTime", typeModel.get(i).getFinishTime());
                        intent.putExtra("TwoW", typeModel.get(i).getNoOfTwoweel());
                        intent.putExtra("ThreeW", typeModel.get(i).getNoOfThreeweel());
                        intent.putExtra("FourW", typeModel.get(i).getNoOfFourweel());
                        intent.putExtra("SixW", typeModel.get(i).getNoOfSixweel());

                        startActivity(intent);
                    }
                });}


                @Override
                public void onError (String message){
                Toast.makeText(ViewallActivity.this, message, Toast.LENGTH_LONG).show();

            }

        });
    }


}