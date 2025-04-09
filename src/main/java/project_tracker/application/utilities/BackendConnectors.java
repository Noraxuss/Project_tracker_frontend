package project_tracker.application.utilities;

import project_tracker.application.domain.UserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BackendConnectors {


    public static final String SRC_MAIN_RESOURCES_JSON = "src/main/resources/json/";

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
            HttpURLConnection conn = getHttpURLConnection();

            // Create JSON payload
            // make sure to replace these with actual values when sending the data
            String jsonFilePath = SRC_MAIN_RESOURCES_JSON + "user.json";
            String jsonInputString = Files.readString(Paths.get(jsonFilePath));
            jsonInputString = jsonInputString.replace("exampleUser", userModel.getUsername());
            jsonInputString = jsonInputString.replace("examplePassword", userModel.getPassword());
            jsonInputString = jsonInputString.replace("example@example.com", userModel.getEmail());

            // Write JSON payload to output stream
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),
                    StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = reader.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HttpURLConnection getHttpURLConnection() throws URISyntaxException, IOException {
        URI uri = new URI("http://localhost:3306/api/users/register");
        URL url = uri.toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        return conn;
    }

}
