package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class chatGpt {



    public static String generateText(String prompt, String model, String apiKey) throws IOException {
        URL url = new URL("https://api.openai.com/v1/engines/" + model + "/completions");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + apiKey);
        conn.setDoOutput(true);

        String data = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 60, \"temperature\": 0.5}";
        byte[] postData = data.getBytes(StandardCharsets.UTF_8);
        conn.getOutputStream().write(postData);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
