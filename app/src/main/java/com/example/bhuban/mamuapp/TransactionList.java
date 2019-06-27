package com.example.bhuban.mamuapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TransactionList  extends ArrayAdapter<Transaction>
{
    private Activity context;
    private List<Transaction> transactionList;

    public TransactionList(Activity context,List<Transaction> transactionList)
    {
        super(context,R.layout.transaction_list_layout,transactionList);
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.customer_list_layout,null, true);

        TextView textViewCustomerName = listViewItem.findViewById(R.id.textViewCustomerName);
        TextView textViewShopkeeperName = listViewItem.findViewById(R.id.ShopkeeperNameText);

        Transaction transaction = transactionList.get(position);

        textViewCustomerName.setText(transaction.getCustomerName());
        textViewShopkeeperName.setText(transaction.getShopkeeperName());
        return listViewItem;

    }


}
