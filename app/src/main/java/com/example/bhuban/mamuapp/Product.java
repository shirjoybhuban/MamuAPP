package com.example.bhuban.mamuapp;

import java.util.Map;

public class Product
{

    String productName;
    String productName3;
    String productName4;
    String productName5;
    String productName6;
    String productName7;
    String productName8;
    String productPrice;
    String productPrice3;
    String productPrice4;
    String productPrice5;
    String productPrice6;
    String productPrice7;
    String productPrice8;
    Boolean transactionStatus;
    Boolean paymentStatus;
    String shopkeeperName;
    String shopkeeperId;
    String customerName;
    String customerId;
    String fireID;
    int totalProduct;
    double totalProductPrice;
    long time;





    public Product()
    {

    }

    public Product( String shopkeeperName, String shopkeeperId, String customerName, String customerId, String fireID, long time)
    {
        this.shopkeeperName = shopkeeperName;
        this.shopkeeperId = shopkeeperId;
        this.customerName = customerName;
        this.customerId = customerId;
        this.fireID = fireID;
        this.time = time;
    }

    public Boolean getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public int getTotalProduct()
    {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct)
    {
        this.totalProduct = totalProduct;
    }

    public double getTotalProductPrice()
    {
        return totalProductPrice;
    }

    public void setTotalProductPrice(double totalProductPrice)
    {
        this.totalProductPrice = totalProductPrice;
    }

    public String getShopkeeperName()
    {
        return shopkeeperName;
    }

    public String getShopkeeperId()
    {
        return shopkeeperId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setShopkeeperName(String shopkeeperName)
    {
        this.shopkeeperName = shopkeeperName;
    }

    public void setShopkeeperId(String shopkeeperId)
    {
        this.shopkeeperId = shopkeeperId;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public String getFireID()
    {
        return fireID;
    }


    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName3()
    {
        return productName3;
    }

    public void setProductName3(String productName3)
    {
        this.productName3 = productName3;
    }

    public String getProductName4()
    {
        return productName4;
    }

    public void setProductName4(String productName4)
    {
        this.productName4 = productName4;
    }

    public String getProductName5()
    {
        return productName5;
    }

    public void setProductName5(String productName5)
    {
        this.productName5 = productName5;
    }

    public String getProductName6()
    {
        return productName6;
    }

    public void setProductName6(String productName6)
    {
        this.productName6 = productName6;
    }

    public String getProductName7()
    {
        return productName7;
    }

    public void setProductName7(String productName7)
    {
        this.productName7 = productName7;
    }

    public String getProductName8()
    {
        return productName8;
    }

    public void setProductName8(String productName8)
    {
        this.productName8 = productName8;
    }

    public String getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(String productPrice)
    {
        this.productPrice = productPrice;
    }

    public String getProductPrice3()
    {
        return productPrice3;
    }

    public void setProductPrice3(String productPrice3)
    {
        this.productPrice3 = productPrice3;
    }

    public String getProductPrice4()
    {
        return productPrice4;
    }

    public void setProductPrice4(String productPrice4)
    {
        this.productPrice4 = productPrice4;
    }

    public String getProductPrice5()
    {
        return productPrice5;
    }

    public void setProductPrice5(String productPrice5)
    {
        this.productPrice5 = productPrice5;
    }

    public String getProductPrice6()
    {
        return productPrice6;
    }

    public void setProductPrice6(String productPrice6)
    {
        this.productPrice6 = productPrice6;
    }

    public String getProductPrice7()
    {
        return productPrice7;
    }

    public void setProductPrice7(String productPrice7)
    {
        this.productPrice7 = productPrice7;
    }

    public String getProductPrice8()
    {
        return productPrice8;
    }

    public void setProductPrice8(String productPrice8)
    {
        this.productPrice8 = productPrice8;
    }



    public Boolean getTransactionStatus()
    {
        return transactionStatus;
    }

    public void setTransactionStatus(Boolean transactionStatus)
    {
        this.transactionStatus = transactionStatus;
    }

    public long getTime()
    {
       return time;
    }

//    public void setTime(Map time)
    {
       // this.time = time;
    }
}
