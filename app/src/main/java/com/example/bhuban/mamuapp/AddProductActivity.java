package com.example.bhuban.mamuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class AddProductActivity extends AppCompatActivity
{
    EditText editTextproductName;
    EditText editTextproductName3;
    EditText editTextproductName4;
    EditText editTextproductName5;
    EditText editTextproductName6;
    EditText editTextproductName7;
    EditText editTextproductName8;

    EditText editTextproductPrice;
    EditText editTextproductPrice3;
    EditText editTextproductPrice4;
    EditText editTextproductPrice5;
    EditText editTextproductPrice6;
    EditText editTextproductPrice7;
    EditText editTextproductPrice8;

    double productPrice = 0;
    double productPrice3 = 0;
    double productPrice4 = 0;
    double productPrice5 = 0;
    double productPrice6 = 0;
    double productPrice7 = 0;
    double productPrice8 = 0;
    double productTotalPrice = 0;
    int totalProduct = 0;

    TextView customerNameText;
    TextView totalPrice;
    private ImageButton addDbButton;
    private ImageButton addListButton;

    String customerName;
    String custoemrID;
    String shopkeeperName;
    String shopekeeperId;
    String fireID;

    ListView listViewProduct;
    Product product;

    DatabaseReference databaseTransaction;

    @Override
    public void onBackPressed()
    {
        this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        totalPrice = findViewById(R.id.totalprice);

        editTextproductName = findViewById(R.id.productName);
        editTextproductName3 = findViewById(R.id.productName3);
        editTextproductName4 = findViewById(R.id.productName4);
        editTextproductName5 = findViewById(R.id.productName5);
        editTextproductName6 = findViewById(R.id.productName6);
        editTextproductName7 = findViewById(R.id.productName7);
        editTextproductName8 = findViewById(R.id.productName8);

        editTextproductPrice = findViewById(R.id.productPrice);
        editTextproductPrice3 = findViewById(R.id.productPrice3);
        editTextproductPrice4 = findViewById(R.id.productPrice4);
        editTextproductPrice5 = findViewById(R.id.productPrice5);
        editTextproductPrice6 = findViewById(R.id.productPrice6);
        editTextproductPrice7 = findViewById(R.id.productPrice7);
        editTextproductPrice8 = findViewById(R.id.productPrice8);

        addDbButton = findViewById(R.id.addDbBtn);
        addListButton = findViewById(R.id.addListBtn);
        listViewProduct = findViewById(R.id.listViewProduct);
        customerNameText =  findViewById(R.id.customerNameText);
        product = new Product();

        Intent intent = getIntent();

         customerName = intent.getStringExtra(CustomerActivity.Customer_Name);
         custoemrID =  intent.getStringExtra(CustomerActivity.Customer_ID);
         shopkeeperName = intent.getStringExtra(CustomerActivity.Shopkeeper_Name);
         shopekeeperId = intent.getStringExtra(CustomerActivity.Shopkeeper_ID);
         fireID = intent.getStringExtra(CustomerActivity.FIRE_ID);

        customerNameText.setText(customerName);

        databaseTransaction = FirebaseDatabase.getInstance().getReference("Transaction");
        addDbButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveTransaction();
            }
        });

    }

    private void saveTransaction()
    {
        String edittextproductName = editTextproductName.getText().toString();

        if(!edittextproductName.matches(""))
            totalProduct+=1;

        String edittextproductName3 = editTextproductName3.getText().toString();

        if(!edittextproductName3.matches(""))
            totalProduct+=1;
        String edittextproductName4 = editTextproductName4.getText().toString();

        if(!edittextproductName4.matches(""))
            totalProduct+=1;

        String edittextproductName5 = editTextproductName5.getText().toString();

        if(!edittextproductName5.matches(""))
            totalProduct+=1;

        String edittextproductName6 = editTextproductName6.getText().toString();

        if(!edittextproductName6.matches(""))
            totalProduct+=1;

        String edittextproductName7 = editTextproductName7.getText().toString();

        if(!edittextproductName7.matches(""))
            totalProduct+=1;

        String edittextproductName8 = editTextproductName8.getText().toString();

        if(!edittextproductName8.matches(""))
            totalProduct+=1;



        String edittextproductPrice = editTextproductPrice.getText().toString();

        if(!edittextproductPrice.matches(""))
            productPrice =  Double.parseDouble(editTextproductPrice.getText().toString());

        String edittextproductPrice3 = editTextproductPrice3.getText().toString();

        if(!edittextproductPrice3.matches(""))
            productPrice3 =  Double.parseDouble(editTextproductPrice3.getText().toString());

        String edittextproductPrice4 = editTextproductPrice4.getText().toString();

        if(!edittextproductPrice4.matches(""))
            productPrice4 =  Double.parseDouble(editTextproductPrice4.getText().toString());

        String edittextproductPrice5 = editTextproductPrice5.getText().toString();

        if(!edittextproductPrice5.matches(""))
            productPrice5 =  Double.parseDouble(editTextproductPrice5.getText().toString());

        String edittextproductPrice6 = editTextproductPrice6.getText().toString();

        if(!edittextproductPrice6.matches(""))
            productPrice6 =  Double.parseDouble(editTextproductPrice6.getText().toString());

        String edittextproductPrice7 = editTextproductPrice7.getText().toString();

        if(!edittextproductPrice7.matches(""))
            productPrice7 =  Double.parseDouble(editTextproductPrice7.getText().toString());

        String edittextproductPrice8 = editTextproductPrice8.getText().toString();

        if(!edittextproductPrice8.matches(""))
            productPrice8 =  Double.parseDouble(editTextproductPrice8.getText().toString());

        productTotalPrice = productPrice + productPrice3 +productPrice4 + productPrice5 + productPrice6 + productPrice7 + productPrice8;




        if (!TextUtils.isEmpty(edittextproductName) && !TextUtils.isEmpty(edittextproductPrice))
        {
            String transactionId =  databaseTransaction.push().getKey();

            product = new Product(shopkeeperName,shopekeeperId,customerName,custoemrID,fireID,System.currentTimeMillis());

            product.setProductName(edittextproductName);
            product.setProductName3(edittextproductName3);
            product.setProductName4(edittextproductName4);
            product.setProductName5(edittextproductName5);
            product.setProductName6(edittextproductName6);
            product.setProductName7(edittextproductName7);
            product.setProductName8(edittextproductName8);

            product.setProductPrice(edittextproductPrice);
            product.setProductPrice3(edittextproductPrice3);
            product.setProductPrice4(edittextproductPrice4);
            product.setProductPrice5(edittextproductPrice5);
            product.setProductPrice6(edittextproductPrice6);
            product.setProductPrice7(edittextproductPrice7);
            product.setProductPrice8(edittextproductPrice8);

            product.setTransactionStatus(false);
            product.setPaymentStatus(false);
            product.setTotalProduct(totalProduct);
            product.setTotalProductPrice(productTotalPrice);

            /* last 5 days long daysInPast= new Date().getTime() - TimeUnit.MILLISECONDS.convert(5, TimeUnit.DAYS); */

            Log.d("TIME", ServerValue.TIMESTAMP.toString());





            Log.d("Total Product", String.valueOf(totalProduct));
            Log.d("Total Price", String.valueOf(productTotalPrice));
            databaseTransaction.child(transactionId).setValue(product);


            Toast.makeText(this, "Transaction Saved",Toast.LENGTH_LONG).show();
            finish();

        }
        else
        {
            Toast.makeText(this, "Product name or Product Price should not be Empty",Toast.LENGTH_LONG).show();

        }
    }
}
