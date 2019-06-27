package com.example.bhuban.mamuapp;

public class Users

{
        public String username;
        public String email;
        public String userId;
        public String shopName;
        public String shopAddress;
        public String fireId;
        public String userType;
        Product product;

        public Users()
        {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Users(String username, String email, String userId)
        {
            this.username = username;
            this.email = email;
            this.userId = userId;
            this.shopName = shopName;
            this.fireId = fireId;

        }

    public String getUsername()

    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setFireId(String fireId)
    {
        this.fireId = fireId;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }


    public String getShopAddress()
    {
        return shopAddress;
    }


    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public void setShopAddress(String shopAddress)
    {
        this.shopAddress = shopAddress;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }
};

