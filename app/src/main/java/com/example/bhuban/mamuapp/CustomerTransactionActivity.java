package com.example.bhuban.mamuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerTransactionActivity extends AppCompatActivity
{

    ListView showTransactionList;

    List<Product> productList;
    String name;
    List<Customer> customerList;




    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, CustomerHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_transaction);

        showTransactionList = findViewById(R.id.listViewTransactions);
        productList = new ArrayList<>();
        Intent intent =  getIntent();
        name = intent.getStringExtra(CustomerHomeActivity.NAME);
        customerList = new ArrayList<>();

        final Query query = FirebaseDatabase.getInstance().getReference("Transaction")
                .orderByChild("customerName")
                .equalTo(name);


        query.addListenerForSingleValueEvent(valueEventListener);// getting the customer for this shopkeeper
        ShowTransactionList adapter = new ShowTransactionList(CustomerTransactionActivity.this,productList);// setting the customers to the list view
        showTransactionList.setAdapter(adapter);

    }


    ValueEventListener valueEventListener = new ValueEventListener()
    {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {

            customerList.clear();
            productList.clear();

            for(DataSnapshot customerSnapshot: dataSnapshot.getChildren())
            {
                // Transaction transaction = customerSnapshot.getValue(Transaction.class);

                Product product = customerSnapshot.getValue(Product.class);
                //transactionList.add(transaction);
                if(product.getTransactionStatus() == true)
                {
                    productList.add(product);
                }

                //Customer customer = customerSnapshot.getValue(Customer.class);
                Log.d("customer name shown", product.getCustomerName());
                Log.d("shopkeeper name shown", product.getShopkeeperName());
                //customerList.add(customer);



            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError)
        {

        }
    };
}
