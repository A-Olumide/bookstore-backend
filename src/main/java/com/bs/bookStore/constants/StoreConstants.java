package com.bs.bookStore.constants;

public class StoreConstants {
    private StoreConstants() {
    }

    public static final String STATUS_201 = "201";

    //    public static final String  MESSAGE_201 = "Book added successfully";
    public static String getBookAddedMessage(String bookName) {
        return bookName + " added successfully";
    }

}