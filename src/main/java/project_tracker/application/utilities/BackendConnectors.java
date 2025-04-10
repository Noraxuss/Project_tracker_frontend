package project_tracker.application.utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import project_tracker.application.domain.LoginModel;
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

    public static String postRegister(UserModel userModel) {
        String response = "";
        try {
            HttpURLConnection conn = getHttpURLConnection("http://localhost:3306/api/users/register",
                    "POST");

            // Create JSON payload
            // make sure to replace these with actual values when sending the data
            String jsonFilePath = SRC_MAIN_RESOURCES_JSON + "register_user.json";
            String jsonInputString = Files.readString(Paths.get(jsonFilePath));
            jsonInputString = jsonInputString.replace("exampleUser", userModel.getUsername());
            jsonInputString = jsonInputString.replace("examplePassword", userModel.getPassword());
            jsonInputString = jsonInputString.replace("example@example.com", userModel.getEmail());

            // Write JSON payload to output stream
            sendRequest(conn, jsonInputString);

            // Read response
            response = getResponse(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String getResponse(HttpURLConnection conn) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),
                StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = reader.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return responseLine;
        }
    }

    private static HttpURLConnection getHttpURLConnection(String urlToBackend, String crudMethod) throws URISyntaxException, IOException {
        URI uri = new URI(urlToBackend);
        URL url = uri.toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(crudMethod);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        return conn;
    }

    public static String getLogin(LoginModel loginModel) {
        try {
            HttpURLConnection conn = getHttpURLConnection("http://localhost:3306/api/users/login", "GET");

            String jsonFilePath = SRC_MAIN_RESOURCES_JSON + "register_user.json";
            String jsonInputString = Files.readString(Paths.get(jsonFilePath));
            jsonInputString = jsonInputString.replace("exampleUser", loginModel.getUsername());
            jsonInputString = jsonInputString.replace("examplePassword", loginModel.getPassword());

            sendRequest(conn, jsonInputString);

            return getResponse(conn);

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void sendRequest(HttpURLConnection conn, String jsonInputString) throws IOException {
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
    }


}
