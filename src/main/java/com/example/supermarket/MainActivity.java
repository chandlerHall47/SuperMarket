package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SuperMarket currentSuperMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            initSuperMarket(extras.getInt("marketID"));
        }else {
            currentSuperMarket = new SuperMarket();
        }
        initTextChangedEvents();

        initRateButton();

        initButtonList();



    }

    private void initTextChangedEvents() {
        final EditText etSuperMarketName = findViewById(R.id.editTextName);
        etSuperMarketName.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence arg0, int i, int i1, int i2) {
                currentSuperMarket.setName(etSuperMarketName.getText().toString());            }


            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                currentSuperMarket.setName(etSuperMarketName.getText().toString());            }


            public void afterTextChanged(Editable s) {
            currentSuperMarket.setName(etSuperMarketName.getText().toString());
            }
        });

        final EditText etSuperMarketStreetAddress = findViewById(R.id.editTextAddress);
        etSuperMarketStreetAddress.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence arg0, int i, int i1, int i2) {
                //auto generated
            }


            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //auto generated
            }


            public void afterTextChanged(Editable s) {
                currentSuperMarket.setStreetAddress(etSuperMarketStreetAddress.getText().toString());
            }
        });

        final EditText etSuperMarketCity = findViewById(R.id.editTextCity);
        etSuperMarketCity.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence arg0, int i, int i1, int i2) {
                //auto generated
            }


            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //auto generated
            }


            public void afterTextChanged(Editable s) {
                currentSuperMarket.setCity(etSuperMarketCity.getText().toString());
            }
        });
        final EditText etSuperMarketState = findViewById(R.id.editTextState);
        etSuperMarketState.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence arg0, int i, int i1, int i2) {
                //auto generated
            }


            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //auto generated
            }


            public void afterTextChanged(Editable s) {
                currentSuperMarket.setState(etSuperMarketState.getText().toString());
            }
        });
        final EditText etSuperMarketZipCode = findViewById(R.id.editTextZipCode);
        etSuperMarketZipCode.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence arg0, int i, int i1, int i2) {
                //auto generated
            }


            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //auto generated
            }


            public void afterTextChanged(Editable s) {
                currentSuperMarket.setZipCode(etSuperMarketZipCode.getText().toString());
            }
        });


    }

    private void initRateButton(){
        Button rateButton = findViewById(R.id.buttonRate);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, rateMarket.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("currentSuperMarket", currentSuperMarket );
                startActivity(intent);
            }
        });
    }

    private void initSuperMarket(int id){
        SuperMarketDataSource ds = new SuperMarketDataSource(MainActivity.this);
        try {
            ds.open();
            currentSuperMarket = ds.getSpecificSuperMarket(id);
            ds.close();
        }
        catch (Exception e) {
            Toast.makeText(this, "Load SuperMarket failed", Toast.LENGTH_LONG).show();
        }
        EditText editName = findViewById(R.id.editTextName);
        EditText editAddress = findViewById(R.id.editTextAddress);
        EditText editCity = findViewById(R.id.editTextCity);
        EditText editState = findViewById(R.id.editTextState);
        EditText editZipCode = findViewById(R.id.editTextZipCode);

        editName.setText(currentSuperMarket.getName());
        editAddress.setText(currentSuperMarket.getStreetAddress());
        editCity.setText(currentSuperMarket.getCity());
        editState.setText(currentSuperMarket.getState());
        editZipCode.setText(currentSuperMarket.getZipCode());


    }

    private void initButtonList(){
        Button listButton = findViewById(R.id.buttonList);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SuperMarketList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });
    }
}