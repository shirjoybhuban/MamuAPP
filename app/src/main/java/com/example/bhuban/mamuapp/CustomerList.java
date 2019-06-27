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

public class CustomerList extends ArrayAdapter<Customer>
{

    private Activity context;
    private List<Customer> customerList;


    public CustomerList(Activity context, List<Customer> customerList)
    {
        super(context,R.layout.customer_list_layout,customerList);
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.customer_list_layout,null, true);

        TextView textViewCustomerName = listViewItem.findViewById(R.id.customerNameText);
        TextView textViewCustomerID = listViewItem.findViewById(R.id.textViewCustomerID);
        TextView textViewShopkeeperName = listViewItem.findViewById(R.id.shopkeeperNameText);
        TextView textViewShopkeeperId = listViewItem.findViewById(R.id.textViewShopKeeperID);

        Customer customer = customerList.get(position);

        textViewCustomerName.setText(customer.getCustomerName());
        textViewCustomerID.setText(customer.getCustomerId());
        textViewShopkeeperName.setText(customer.getShopkeeperName());
        textViewShopkeeperId.setText(customer.getShopkeeperID());
        return listViewItem;



    }
}

