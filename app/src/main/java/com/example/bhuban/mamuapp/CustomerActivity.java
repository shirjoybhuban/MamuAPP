package com.example.bhuban.mamuapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CustomerActivity extends AppCompatActivity
{
    private FirebaseAuth mauth;
    FirebaseAuth mAuth;
    EditText editTextName;
    Button buttonAdd;
    DatabaseReference databaseCustomer;
    String ShopkeeperID;
    String customerID;
    String nameShopkeeper;
    String nameCustomer;
    ListView listViewCustomer;
    TextView test;
    List<Customer> customerList;
    String uid;
    Customer customer = new Customer();
    public static  final  String Customer_Name = "customerName";
    public static  final  String Customer_ID = "customerId";
    public static  final  String Shopkeeper_Name = "shopkeeperName";
    public static  final  String Shopkeeper_ID = "shopkeeperId";
    public static  final  String FIRE_ID = "fireID";
    public static  final  String USER_TYPE = "userType";


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

        databaseCustomer = FirebaseDatabase.getInstance().getReference("customer");

        setContentView(R.layout.activity_customer);

        editTextName = findViewById(R.id.editTextName);

        buttonAdd = findViewById(R.id.buttonAddCustomer);

        listViewCustomer = findViewById(R.id.listViewCustomer);

        customerList = new ArrayList<>();

        test = findViewById(R.id.textViewtest);

        buttonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addCustomer();
            }
        });

        listViewCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Customer customer = customerList.get(i);
                Intent intent = new Intent(getApplicationContext(),AddProductActivity.class);
                intent.putExtra(Customer_Name,customer.getCustomerName());
                intent.putExtra(Customer_ID,customer.getCustomerId());
                intent.putExtra(Shopkeeper_Name,customer.getShopkeeperName());
                intent.putExtra(Shopkeeper_ID,customer.getShopkeeperID());
                intent.putExtra(FIRE_ID,customer.getFireID());
                //intent.putExtra(USER_TYPE,customer.get)

                startActivity(intent);


            }
        });

        Query query = FirebaseDatabase.getInstance().getReference("customer")
                .orderByChild("fireID")
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

        query.addValueEventListener(valueEventListener);// getting the customer for this shopkeeper

        CustomerList adapter = new CustomerList(CustomerActivity.this, customerList);// setting the customers to the list view
        listViewCustomer.setAdapter(adapter);
        //Log.d("Customer_List", customer.getCustomerName());




    }

    ValueEventListener valueEventListener = new ValueEventListener()
    {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {
            customerList.clear();

            for(DataSnapshot customerSnapshot: dataSnapshot.getChildren())
            {
                Customer customer = customerSnapshot.getValue(Customer.class);

                customerList.add(customer);

                Log.d("Shopkeeper_name", customer.getShopkeeperName().toString());
                Log.d("Shopkeeper_ID", customer.getShopkeeperID().toString());
                Log.d("Customer_name", customer.getCustomerName().toString());
                Log.d("Customer_ID", customer.getCustomerId().toString());
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

    private void addCustomer() // adding data of customer
    {
        String name = editTextName.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)                                               //getting shopkeeper Data.
        {
            for (UserInfo profile : user.getProviderData())
            {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                ShopkeeperID = profile.getUid();

                // Name, email address, and profile photo Url
                nameShopkeeper = profile.getDisplayName();
                String email = profile.getEmail();
            }
        }
        if(!TextUtils.isEmpty(name))                                    // storing customer data to database
        {
            customerID = databaseCustomer.push().getKey();
            customer.setCustomerId(customerID);
            customer.setCustomerName(name);
            customer.setShopkeeperName(nameShopkeeper);
            customer.setShopkeeperID(ShopkeeperID);
            customer.setFireID(user.getUid());
            databaseCustomer.child(customerID).setValue(customer);
            Toast.makeText(this,"Customer Added,",Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(this,"You Should enter the customer name",Toast.LENGTH_LONG).show();

        }
    }
}
