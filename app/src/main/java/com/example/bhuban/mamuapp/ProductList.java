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

public class ProductList extends ArrayAdapter<Product>
{
    private Activity context;
    private List<Product> productList;


    public ProductList(Activity context,List<Product> productList)
    {
        super(context,R.layout.product_list_layout,productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.customer_list_layout,null, true);

        TextView textViewCustomerName = listViewItem.findViewById(R.id.customerNameText);
        TextView textViewShopkeeperName = listViewItem.findViewById(R.id.shopkeeperNameText);

        Product product = productList.get(position);

        textViewCustomerName.setText(product.getCustomerName());
        textViewShopkeeperName.setText(product.getShopkeeperName());
        return listViewItem;



    }
}

