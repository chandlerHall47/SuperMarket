package com.example.supermarket;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuperMarketAdapter extends RecyclerView.Adapter {
    final private ArrayList<SuperMarket> superMarketData;
    private View.OnClickListener mOnItemClickListener;
    private boolean isDeleting;
    private Context parentContext;

    public SuperMarketAdapter(ArrayList<SuperMarket> arrayList, Context context){
        superMarketData = arrayList;
        parentContext = context;
    }

    public SuperMarketAdapter(ArrayList<SuperMarket> arrayList){superMarketData = arrayList;}


    public class SuperMarketViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewSuperMarket;
        public TextView textRating;
        public Button deleteButton;
        public SuperMarketViewHolder(@NonNull View itemView){
            super(itemView);
            textViewSuperMarket = itemView.findViewById(R.id.textSuperMarketName);
            textRating = itemView.findViewById(R.id.textAvgRating);
            deleteButton = itemView.findViewById(R.id.buttonDeleteSuperMarket);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
        public TextView getSuperMarketTextView()
        {return textViewSuperMarket;}
        public TextView getRatingTextView()
        {return textRating;}
        public TextView getDeleteButton(){return deleteButton;}

    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new SuperMarketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
    SuperMarketViewHolder svh = (SuperMarketViewHolder) holder;

    //Changed the position to get the holder position
        svh.getSuperMarketTextView().setText(superMarketData.get(position).getName());
        svh.getRatingTextView().setText("Rating: " + superMarketData.get(position).getRating());

        if(isDeleting){
            svh.getDeleteButton().setVisibility(View.VISIBLE);
            svh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Changed the position to get the holder position
                    deleteItem(position);
                }
            });
        }
        else{
            svh.getDeleteButton().setVisibility(View.INVISIBLE);
        }
    }

    public void setDelete(boolean b) {isDeleting = b;}

    private void deleteItem(int position){
        SuperMarket superMarket = superMarketData.get(position);
        SuperMarketDataSource ds = new SuperMarketDataSource(parentContext);
        try{
            ds.open();
            boolean didDelete = ds.deleteSupermarket(superMarket.getMarketID());
            ds.close();
            if(didDelete){
                superMarketData.remove(position);
            }
            else{
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public int getItemCount() {
        return superMarketData.size();
    }
}
