package com.example.bhuban.mamuapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Contacts;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerPermissionActivity extends AppCompatActivity
{

    ListView listViewShopkeeper;

    List<Product> productList;
    List<Transaction> transactionList;
    String name;
    EditText editText4;
    List<Customer> customerList;
    int count;

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
        setContentView(R.layout.activity_customer_permission);

        listViewShopkeeper = findViewById(R.id.listViewShopkeeper);

        productList = new ArrayList<>();
        transactionList = new ArrayList<>();
        Intent intent =  getIntent();
        name = intent.getStringExtra(CustomerHomeActivity.NAME);

        customerList = new ArrayList<>();


        final Query query = FirebaseDatabase.getInstance().getReference("Transaction")
                .orderByChild("customerName")
                .equalTo(name);

        listViewShopkeeper.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Product product = productList.get(i);
                count = i;
                Log.d("count",String.valueOf(count));
                new AlertDialog.Builder(CustomerPermissionActivity.this)
                        .setTitle("Permit Transaction?")
                        .setMessage("Are you sure you want approve this transaction?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1)
                            {
                                Intent intent =  new Intent(CustomerPermissionActivity.this, CustomerHomeActivity.class);
                                startActivity(intent);
                                finish();
                                Query nquery = FirebaseDatabase.getInstance().getReference("Transaction")
                                        .orderByChild("customerName")
                                        .equalTo(name);

                                nquery.addListenerForSingleValueEvent(valueEventListener2);// getting the customer for this shopkeeper



                            }
                        }).create().show();


            }
        });

        Log.d("Name", name);
        query.addListenerForSingleValueEvent(valueEventListener);// getting the customer for this shopkeeper
        ShowTransactionList adapter = new ShowTransactionList(CustomerPermissionActivity.this,productList);// setting the customers to the list view
        listViewShopkeeper.setAdapter(adapter);


    }

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
                    if( first == true && product.getTransactionStatus() == false)
                    {
                        DatabaseReference ref = customerSnapshot.getRef().child("transactionStatus");
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


    @Override
    protected void onStart()
    {
        super.onStart();
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
                if(product.getTransactionStatus() == false)
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
