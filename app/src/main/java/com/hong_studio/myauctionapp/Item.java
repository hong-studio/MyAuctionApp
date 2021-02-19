package com.hong_studio.myauctionapp;

public class Item {
    String memberName;
    String productName;
    String category;
    String price;
    String msg;
    String productImg;
    String profileImg;
    String time;
    int favor;

    public Item() {
    }

    public Item(String memberName, String productName, String category, String price, String msg, String productImg, String profileImg, String time, int favor) {
        this.memberName = memberName;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.msg = msg;
        this.productImg = productImg;
        this.profileImg = profileImg;
        this.time = time;
        this.favor = favor;
    }
}
