import java.io.BufferedReader;
import java.io.InputStreamReader;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;


public class Program {

    public static void main(String[] args)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        AIDataService dataService = new AIDataService(
                new AIConfiguration("00221a3fbfd74316ad3e76dcf86d5b8f"));
        try
        {
            while(true)
            {
                System.out.print("You: ");
                String line = reader.readLine();

                AIRequest request = new AIRequest(line);
                AIResponse response = dataService.request(request);

                if (response.getStatus().getCode() == 200) {
                    System.out.println("Bot: "
                            + response.getResult().getFulfillment().getSpeech());
                } else {
                    System.err.println(response.getStatus().getErrorDetails());
                }
            }
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
