package com.bs.bookStore.constants;

public class StoreConstants {
    private StoreConstants() {
    }

    public static final String STATUS_201 = "201";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";
    public static final String  STATUS_400 = "400";
    public static final String WEB_MESSAGE = "Payment purchased via web";
    public static final String USSD_MESSAGE = "Payment purchased via USSD";
    public static final String TRANSFER_MESSAGE = "Payment purchased via Bank transfer";
    public static final String  MESSAGE_400= "Invalid Payment method.";


    public static final String  MESSAGE_201 = "Book added successfully";
    public static String getBookAddedMessage(String bookName) {
        return bookName + " added successfully";
    }




}
