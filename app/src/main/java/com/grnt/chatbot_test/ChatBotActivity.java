package com.grnt.chatbot_test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grnt.chatbot_test.adapter.ChatAdapter;
import com.grnt.chatbot_test.model.DataModel;
import com.grnt.chatbot_test.model.Message;
import com.grnt.chatbot_test.viewmodel.LLMViewModel;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ChatBotActivity extends AppCompatActivity {

    LLMViewModel llmViewModel;
    EditText edChatHumanMessage;
    Button btnSendHumanMessage;
    RecyclerView rvChatFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_bot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edChatHumanMessage = findViewById(R.id.edChatHumanMessage);
        btnSendHumanMessage = findViewById(R.id.btnSendHumanMessage);
        rvChatFlow = findViewById(R.id.rvChatFlow);
        initViewModel();
        initRvChatFlow();
        initButton();
    }

    private void initViewModel() {
        llmViewModel = new ViewModelProvider(this).get(LLMViewModel.class);
        llmViewModel.getSystemMessage().observe(this, new Observer<DataModel>() {
            @Override
            public void onChanged(DataModel dataModel) {
                if (dataModel != null) {
                    Toast.makeText(ChatBotActivity.this, "Post created successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatBotActivity.this, "Failed to create post", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initButton() {
        btnSendHumanMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String humanMessage = edChatHumanMessage.getText().toString();
                llmViewModel.fetchHumanMessage(humanMessage);
            }
        });
    }

    private void initRvChatFlow() {
        List<Message> chatMessages = new ArrayList<>();
        try {
            String data = readFakeData();
            // Gson kullanarak JSON'u DataModel nesnesine çevirme
            Gson gson = new GsonBuilder().create();
            DataModel dataModel = gson.fromJson(data, DataModel.class);
           chatMessages = dataModel.getData();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        ChatAdapter chatAdapter = new ChatAdapter(chatMessages);
        rvChatFlow.setAdapter(chatAdapter);
        rvChatFlow.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
    }


    private String readFakeData() throws IOException {
        InputStream inputStream = getApplicationContext().getResources().openRawResource(R.raw.fakedata);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return  new String(buffer);
    }

}