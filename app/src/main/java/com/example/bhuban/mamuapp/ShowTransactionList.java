package com.example.bhuban.mamuapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ShowTransactionList extends ArrayAdapter<Product>
{
    private Activity context;
    private List<Product> productList;

    public ShowTransactionList(Activity context,List<Product> productList)
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

        View listViewItem = inflater.inflate(R.layout.show_transaction_list,null, true);

        TextView textViewShopkeeperName = listViewItem.findViewById(R.id.textViewShopkeeperName);
        TextView textViewTotalProduct =  listViewItem.findViewById(R.id.textViewTotalProduct);
        TextView textViewProductPrice = listViewItem.findViewById(R.id.textViewProductPrice);
        TextView textViewPaymentStatus =  listViewItem.findViewById(R.id.textViewPaymentStatus);
        TextView textViewDate = listViewItem.findViewById(R.id.textViewDate);

        Product product = productList.get(position);

        long time = product.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:MM");
        Date resultDate = new Date(time);

        textViewDate.setText(sdf.format(resultDate));
        textViewShopkeeperName.setText(product.getShopkeeperName());
        textViewTotalProduct.setText(String.valueOf(product.totalProduct));
        textViewProductPrice.setText((String.valueOf(product.totalProductPrice)));
        if(!product.getPaymentStatus())
            textViewPaymentStatus.setText("Unpaid");
        else
            textViewPaymentStatus.setText("Paid");








        return listViewItem;



    }
}
