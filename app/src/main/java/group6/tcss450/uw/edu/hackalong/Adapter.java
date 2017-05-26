package group6.tcss450.uw.edu.hackalong;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Devin on 5/25/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    ArrayList<String> mDataset = new ArrayList<String>();
    ArrayList<String> eventLocData = new ArrayList<String>();
    ArrayList<String> eventDateData = new ArrayList<String>();
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextViewEvName;
        public TextView mTextViewEvLoc;
        public TextView mTextViewEvDate;

        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextViewEvName = (TextView) v.findViewById(R.id.textViewName);
            mTextViewEvLoc = (TextView) v.findViewById(R.id.textViewLoc);
            mTextViewEvDate = (TextView) v.findViewById(R.id.TextViewDate);




        }
    }

    public Adapter(ArrayList<String> myDataset, ArrayList<String>  eventLocDataa, ArrayList<String>  eventDateDataa) {
        mDataset = myDataset;
        eventLocData = eventLocDataa;
        eventDateData = eventDateDataa;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_events, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(Adapter.MyViewHolder holder, int position) {
        holder.mTextViewEvName.setText(mDataset.get(position));
        holder.mTextViewEvLoc.setText(eventLocData.get(position));
        holder.mTextViewEvDate.setText(eventDateData.get(position));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( v.getContext().getApplicationContext(), "The email is incorrect.",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}