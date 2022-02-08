package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class rateMarket extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_market);
        initGoBackButton();
        initSaveButton();

    }

    private void initGoBackButton(){
       Button backButton = findViewById(R.id.button4);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rateMarket.this, SuperMarketList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initSaveButton(){
        Button saveButton = findViewById(R.id.button3);
        RatingBar meat = findViewById(R.id.ratingBarMeat);
        RatingBar produce = findViewById(R.id.ratingBarProduce);
        RatingBar cheese = findViewById(R.id.ratingBarCheese);
        RatingBar liquor = findViewById(R.id.ratingBarLiquor);
        RatingBar checkout = findViewById(R.id.ratingBarCheckout);
        TextView avgRating = findViewById(R.id.textViewAvgRating);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         String avStars = String.valueOf((liquor.getRating() + cheese.getRating() + meat.getRating() + produce.getRating() + checkout.getRating()) / 5);
          avgRating.setText("Rating: " + avStars + " stars");

// add rating to the object
              Intent intent1 = getIntent();
               SuperMarket currentSuperMarket = (SuperMarket) intent1.getSerializableExtra("currentSuperMarket");
                currentSuperMarket.setRating(avStars);


                boolean wasSuccessful;
                int num = 6;

                SuperMarketDataSource ds = new SuperMarketDataSource(rateMarket.this);
                try {
                    ds.open();
                    if (currentSuperMarket.getMarketID() == -1) {
                        wasSuccessful = ds.insertSuperMarket(currentSuperMarket);
                        int newID = ds.getLastMarketID();
                        currentSuperMarket.setMarketID(newID);
                        System.out.println(currentSuperMarket.getMarketID());

                    }
                    else {
                        wasSuccessful = ds.updateSuperMarket(currentSuperMarket);
                    }
                    ds.close();
                }
                catch (Exception e ){
                    wasSuccessful = false;
                }



            }



        });

    }
}