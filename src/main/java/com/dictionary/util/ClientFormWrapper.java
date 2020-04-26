package com.dictionary.util;

import com.dictionary.models.Words;

import java.util.ArrayList;
import java.util.List;

public class ClientFormWrapper {
    private List<Words> clientList;

    public ArrayList<Words> getClientList() {
        return (ArrayList<Words>) clientList;
    }

    public void setClientList(List<Words> clientList) {
        this.clientList = clientList;
    }
}
