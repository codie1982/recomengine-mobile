package com.grnt.chatbot_test.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grnt.chatbot_test.api.Client;
import com.grnt.chatbot_test.api.LLMService;
import com.grnt.chatbot_test.model.DataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LLMViewModel extends ViewModel {
    private final MutableLiveData<DataModel> systemMessage = new MutableLiveData<>();
    private final LLMService llmService;

    public LLMViewModel() {
        llmService = Client.getRetrofitInstance().create(LLMService.class);
    }

    public LiveData<DataModel> getSystemMessage() {
        return systemMessage;
    }

    public void fetchHumanMessage(String humanMessage) {
        llmService.getSystemMessage(humanMessage).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    systemMessage.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                systemMessage.setValue(null);
            }
        });
    }
}
