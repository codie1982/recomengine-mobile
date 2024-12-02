package com.grnt.chatbot_test.constant;

public enum DataType {
    SYSTEM_MESSAGE("system_message"),
    HUMAN_MESSAGE("human_message");
    private final String description;

    DataType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
