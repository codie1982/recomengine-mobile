package com.grnt.chatbot_test.model;

import java.util.List;

public class DataModel {
    private List<Message> data;

    // Getter ve Setter metodları
    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }

    // Constructor
    public DataModel(List<Message> data) {
        this.data = data;
    }

    // Parametresiz Constructor (Gson veya Jackson gibi kütüphanelerde kullanışlı olabilir)
    public DataModel() {
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "data=" + data +
                '}';
    }
}