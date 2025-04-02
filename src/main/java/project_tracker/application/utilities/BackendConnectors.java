package project_tracker.application.utilities;

import project_tracker.application.domain.UserModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BackendConnectors {


    //This is an example and connects to nothing
    private String getMessageFromBackend() throws Exception {
        URL url = new URL("http://localhost:8080/hello");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader
                (conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();
        return content.toString();
    }

    public static void postRegister(UserModel userModel) {
        try {
            URI uri = new URI("http://localhost:3306/api/users/register");
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create JSON payload
            // make sure to replace these with actual values when sending the data
            String jsonFilePath = "src/main/resources/json/user.json";
            String jsonInputString = Files.readString(Paths.get(jsonFilePath));
            jsonInputString = jsonInputString.replace("{{username}}", userModel.getUsername());
            jsonInputString = jsonInputString.replace("{{password}}", userModel.getPassword());
            jsonInputString = jsonInputString.replace("{{email}}", userModel.getEmail());


            // Write JSON payload to output stream
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),
                    StandardCharsets.UTF_8))) {
                System.out.println("hello");
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
