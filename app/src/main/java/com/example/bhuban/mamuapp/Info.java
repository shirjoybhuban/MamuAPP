package com.example.bhuban.mamuapp;

public class Info
{
    String shopkeeperName;
    String customerName;

    public Info()
    {
    }

    public Info(String shopkeeperName, String customerName)
    {
        this.shopkeeperName = shopkeeperName;
        this.customerName = customerName;
    }

    public String getShopkeeperName()
    {
        return shopkeeperName;
    }

    public void setShopkeeperName(String shopkeeperName)
    {
        this.shopkeeperName = shopkeeperName;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }
}
