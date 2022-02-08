package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.SwitchCompat$InspectionCompanion;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class SuperMarketList extends AppCompatActivity {
RecyclerView superMarketList;
SuperMarketAdapter superMarketAdapter;
ArrayList<SuperMarket> superMarkets;

    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int marketID = superMarkets.get(position).getMarketID();
            Intent intent = new Intent(SuperMarketList.this, MainActivity.class);
            intent.putExtra("marketID", marketID);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_market_list);
        initAddSuperMarketButton();
        initDeleteSwitch();


    }

    @Override
    public void onResume(){
        super.onResume();

        String sortBy = getSharedPreferences("SuperMarketPreferences",
         Context.MODE_PRIVATE).getString("sortfield", "superMarketName");
        String sortOrder = getSharedPreferences("SuperMarketPreferences",
                Context.MODE_PRIVATE).getString("sortorder", "ASC");
        SuperMarketDataSource ds = new SuperMarketDataSource(this);

        try {
            ds.open();
            superMarkets = ds.getSuperMarkets(sortBy, sortOrder);
            ds.close();

            if(superMarkets.size() > 0){
                RecyclerView superMarketList = findViewById(R.id.rvSuperMarkets);
                superMarketAdapter = new SuperMarketAdapter(superMarkets, this);
                superMarketAdapter.setOnItemClickListener(onItemClickListener);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                superMarketList.setLayoutManager(layoutManager);
                superMarketList.setAdapter(superMarketAdapter);
            }else {
               Intent intent = new Intent(SuperMarketList.this, MainActivity.class);
               startActivity(intent);
            }
        }
        catch(Exception e){
            Toast.makeText(this, "Error retrieving super markets", Toast.LENGTH_SHORT).show();
        }

    }

    private void initAddSuperMarketButton(){
        Button newSuperMarket = findViewById(R.id.buttonAddSuperMarket);
        newSuperMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuperMarketList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDeleteSwitch(){
        Switch s = findViewById(R.id.switchDelete);

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean status = compoundButton.isChecked();
                superMarketAdapter.setDelete(status);
                superMarketAdapter.notifyDataSetChanged();
            }
        });

    }
}