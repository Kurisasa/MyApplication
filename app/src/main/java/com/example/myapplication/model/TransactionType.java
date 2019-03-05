package com.example.myapplication.model;

public class TransactionType {

    private int TransactionTypeId;
    private String Name;
    private String Description;

    public TransactionType() {

    }

    public int getTransactionTypeId() {
        return TransactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        TransactionTypeId = transactionTypeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
