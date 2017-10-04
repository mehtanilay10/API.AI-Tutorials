package app.tutorialsteam.apiaiclient;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ai.api.android.AIDataService;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;


public class MainActivity extends AppCompatActivity {

    // Controls
    private Button btnQuerySubmit;
    private EditText edtQueryToSubmit;
    private TextView tvPrintQueryResult;
    private ProgressDialog progressDialog;

    // API.AI specific
    private AIService aiService;
    private AIDataService aiDataService;
    private final AIConfiguration config = new AIConfiguration("0a2b60ccc88b48c38779ef6806d76539",
            AIConfiguration.SupportedLanguages.English,
            AIConfiguration.RecognitionEngine.System);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnQuerySubmit = (Button) findViewById(R.id.submitButton);
        edtQueryToSubmit = (EditText) findViewById(R.id.queryBox);
        tvPrintQueryResult = (TextView) findViewById(R.id.chatHistory);

        //Initialize the AI service
        aiDataService = new AIDataService(this, config);
        btnQuerySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Execute Async task for query response
                new SendRequestTask().execute(edtQueryToSubmit.getText().toString());
            }
        });
    }


    // Perform operation
    public class SendRequestTask extends AsyncTask<String, String, AIResponse> {
        @Override
        protected AIResponse doInBackground(String... params) {
            AIRequest aiRequest = new AIRequest();
            AIResponse aiResponse = null;

            try {
                aiRequest.setQuery(params[0]);
                aiResponse = aiDataService.request(aiRequest);
            } catch (AIServiceException e) {
                e.printStackTrace();
            }

            if (aiResponse != null) {
                return aiResponse;
            } else {
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(AIResponse aiResponse) {
            super.onPostExecute(aiResponse);
            Result result = aiResponse.getResult();
            tvPrintQueryResult.append("You: " + result.getResolvedQuery() + "\r\n" +
                    "Bot: " + result.getFulfillment().getSpeech() + "\r\n");
            progressDialog.dismiss();
        }
    }
}
