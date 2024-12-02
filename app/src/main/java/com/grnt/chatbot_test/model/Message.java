package com.grnt.chatbot_test.model;

public class Message {
    private String type;
    private String message;

    // Getter ve Setter metodları
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Constructor
    public Message(String type, String message) {
        this.type = type;
        this.message = message;
    }

    // Parametresiz Constructor (Gson veya Jackson gibi kütüphanelerde kullanışlı olabilir)
    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
