package com.example.bhuban.mamuapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class InfoCustomerActivity extends AppCompatActivity
{
    String UID;

    ListView showTransactionList;

    List<Product> productList;
    String name;
    List<Customer> customerList;
    int count;


    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, AccountActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_customer);

        Intent intent = getIntent();
        UID = intent.getStringExtra(AccountActivity.USER_ID);

        showTransactionList = findViewById(R.id.listViewTransactions);
        productList = new ArrayList<>();
        customerList = new ArrayList<>();

        final Query query = FirebaseDatabase.getInstance().getReference("Transaction")
                .orderByChild("shopkeeperId")
                .equalTo(UID);

        query.addListenerForSingleValueEvent(valueEventListener);// getting the customer for this shopkeeper
        final ShowTransactionList adapter = new ShowTransactionList(InfoCustomerActivity.this,productList);// setting the customers to the list view
        showTransactionList.setAdapter(adapter);

        showTransactionList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                Product product = productList.get(i);
                count = i;

                    Log.d("count",String.valueOf(count));
                    new AlertDialog.Builder(InfoCustomerActivity.this)
                            .setTitle("Transaction Complete")
                            .setMessage("Are you sure you want approve this transaction?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1)
                                {
                                    Intent intent =  new Intent(InfoCustomerActivity.this, AccountActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Query nquery = FirebaseDatabase.getInstance().getReference("Transaction")
                                            .orderByChild("shopkeeperId")
                                            .equalTo(UID);

                                    nquery.addListenerForSingleValueEvent(valueEventListener2);// getting the customer for this shopkeeper
                                }
                            }).create().show();
            }
        });

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


    ValueEventListener valueEventListener2 = new ValueEventListener()
    {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {

            customerList.clear();
            productList.clear();

            boolean first = true;
            int counter = 0;
            for(DataSnapshot customerSnapshot: dataSnapshot.getChildren())
            {


                Product product = customerSnapshot.getValue(Product.class);
                Log.d("bool", String.valueOf(first));
                Log.d("boolt", String.valueOf(product.getTransactionStatus()));
                Log.d("Counter", String.valueOf(counter));
                if( first == true && product.getPaymentStatus() == false)
                {
                    DatabaseReference ref = customerSnapshot.getRef().child("paymentStatus");
                    Log.d("REF", ref.toString());
                    ref.setValue(true);
                    first = false;
                    Log.d("Counter", String.valueOf(counter));


                }
                else
                {

                }
                counter++;

            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError)
        {

        }
    };
}
