package com.grnt.chatbot_test.api;

import com.grnt.chatbot_test.model.DataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LLMService {
    @POST("/llm/message/")
    Call<DataModel> getSystemMessage(@Body String humanMessage);
}
