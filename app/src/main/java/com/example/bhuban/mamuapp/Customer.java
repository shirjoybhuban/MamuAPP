package com.example.bhuban.mamuapp;

public class Customer
{
    String customerName;
    String customerId;
    String customerphoneNummer;
    String shopkeeperID;
    String shopkeeperName;
    String fireID;

    public Customer()
    {

    }

    public Customer(String customerName, String customerId,String shopkeeperName,String shopkeeperID)
    {
        this.customerName = customerName;
        this.customerId = customerId;
        this.shopkeeperName = shopkeeperName;
        this.shopkeeperID = shopkeeperID;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public String getCustomerphoneNummer()
    {
        return customerphoneNummer;
    }

    public String getShopkeeperID()
    {
        return shopkeeperID;
    }

    public String getShopkeeperName()
    {
        return shopkeeperName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public void setShopkeeperID(String shopkeeperID)
    {
        this.shopkeeperID = shopkeeperID;
    }

    public void setShopkeeperName(String shopkeeperName)
    {
        this.shopkeeperName = shopkeeperName;
    }

    public String getFireID()
    {
        return fireID;
    }

    public void setFireID(String fireID)
    {
        this.fireID = fireID;
    }
}
