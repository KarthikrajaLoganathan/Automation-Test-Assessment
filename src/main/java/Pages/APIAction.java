package Pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIAction {
	

    static String addURI;
    static URL url;
    public static HttpURLConnection connection;
    static JsonNode actualDataJsonNode;
    static JsonNode jsonNode;
    static String expectedResponseCode;
    static String getExpectedURL;
    static String payload;
    static OutputStream outputStream;
    static String expectedData;
    static String actualData;

    /**
     * @param endpoint url
     * @throws MalformedURLException Code to set URL
     */
    public static void setServiceEndpoint(String endpoint) throws MalformedURLException {
        addURI = "https://reqres.in"+endpoint;
        url = new URL(addURI);
        System.out.println("Endpoint URL :" + url);
    }

    /**
     * @param requestType method
     * @param apiName api
     */
    public static void setRequestType(String requestType, String apiName) {
        try {
            Thread.sleep(2000);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestType);
            Thread.sleep(4000);
            connection.setRequestProperty("Accept", "application/json");
            if (requestType.equals("POST") && apiName.toUpperCase().equals("CREATE")) {
                connection.setDoOutput(true);
                outputStream = connection.getOutputStream();
                payload = "{\"name\":\"morpheus\",\"job\":\"leader\"}";
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input);
                outputStream.flush();
                outputStream.close();
            } else if
            (requestType.equals("POST") && apiName.equals("REGISTER_USER")) {
                connection.setDoOutput(true);
                outputStream = connection.getOutputStream();
                payload = "{\"email\":\"eve.holt@reqres.in\",\"password\":\"pistol\"}";
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input);
                outputStream.flush();
                outputStream.close();
            } else if (requestType.equals("UPDATE")) {
                connection.setDoOutput(true);
                outputStream = connection.getOutputStream();
                payload = "{\"name\":\"morpheus\",\"job\":\"Tester\"}";
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input);
                outputStream.flush();
                outputStream.close();
            } else if (requestType.equals("PATCH") || requestType.equals("PATCH_UPDATE")) {
                connection.setDoOutput(true);
                outputStream = connection.getOutputStream();
                payload = "{\"name\":\"morpheus\",\"job\":\"zion resident\"}";
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input);
                outputStream.flush();
                outputStream.close();
            }
            else if (requestType.equals("GET") && apiName.toUpperCase().equals("SINGLE USER")) {
                connection.setDoOutput(true);
                outputStream = connection.getOutputStream();
                System.out.println("Request Type is: "+requestType);
                System.out.println("API Name is: "+apiName);
               // payload = "{\"name\":\"morpheus\",\"job\":\"zion resident\"}";
                //byte[] input = payload.getBytes(StandardCharsets.UTF_8);
               // outputStream.write(input);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("setRequestType failed " + e);
        }

    }
    /**
     * @throws IOException code to check response code
     */
    public static void checkStatusCode(String apiName) throws IOException {
        try {
          //  String actualResponseCode = String.valueOf(connection.getResponseCode());
            
            switch (apiName) {
                case "SINGLE_USER":
                        expectedResponseCode = String.valueOf((getJsonMapData().get("single user").get("Response")));
                case "REGISTER_USER":
                        expectedResponseCode = String.valueOf((getJsonMapData().get("register_user").get("Response")));
                case "CREATE":
                        expectedResponseCode = String.valueOf((getJsonMapData().get("create").get("Response")));
                case "UPDATE_USER":
                        expectedResponseCode = String.valueOf((getJsonMapData().get("update_user").get("Response")));
                case "PATCH_UPDATE":
                        expectedResponseCode = String.valueOf((getJsonMapData().get("patch_update").get("Response")));
                case "DELETE":
                        expectedResponseCode = String.valueOf((getJsonMapData().get("delete").get("Response")));
                default: throw new AssertionError("No API present with this name!");
            }
            /*System.out.println("Expected ResponseCode : " + expectedResponseCode);
            System.out.println("Actual ResponseCode : " + actualResponseCode);
            Assert.assertTrue("ResponseCode verified", expectedResponseCode.equalsIgnoreCase(actualResponseCode));*/
        } catch (Exception e) {
            connection.disconnect();
            System.out.println("checkStatusCode failed " + e);
        }
    }

    /**
     * @throws IOException Code to check the response body
     */
    public static void verifyResponseBody(String apiName) throws IOException {
        try {
            switch (apiName) {
                case "SINGLE USER": {
                	expectedData = String.valueOf(getJsonMapData().get("single_user").get("ResponseData").get("data").get(0).get("data").get("first_name")).replaceAll("\"", "");
                    System.out.println("Expected Data: "+expectedData);
                    actualData = String.valueOf(getApiResponseBody().get("ResponseData")).replaceAll("\"", "");
                    System.out.println("Actual Data: "+actualData);
                }
                case "REGISTER_USER": {
                    expectedData = String.valueOf((getJsonMapData().get("register_user").get("RequestData").get("email"))).replaceAll("\"", "");
                    actualData = String.valueOf(getApiResponseBody().get("ResponseData").get("id")).replaceAll("\"", "");
                }
                case "CREATE": {
                    expectedData = String.valueOf((getJsonMapData().get("create").get("RequestData")).get("name")).replaceAll("\"", "");
                    System.out.println("Expected Data: "+expectedData);
                    actualData = String.valueOf(getApiResponseBody().get("create").get("ResponseData").get("name")).replaceAll("\"", "");
                    System.out.println("Actual Data: "+actualData);

                }
                case "UPDATE_USER": {
                    expectedData = String.valueOf((getJsonMapData().get("update_user").get("ResponseData")).get("name")).replaceAll("\"", "");
                    actualData = String.valueOf(getApiResponseBody().get("name")).replaceAll("\"", "");
                }
                case "PATCH_UPDATE": {
                    expectedData = String.valueOf((getJsonMapData().get("patch_update").get("ResponseData")).get("name")).replaceAll("\"", "");
                    actualData = String.valueOf(getApiResponseBody().get("name")).replaceAll("\"", "");
                }
                default: {
                    throw new AssertionError("No API present with this name!");
                }
            }
            /*System.out.println("Expected data : " + expectedData);
            System.out.println("Actual data : " + actualData);
            Assert.assertTrue("- ResponseCode verification test -", expectedData.equalsIgnoreCase(actualData));*/
        } catch (Exception e) {
            connection.disconnect();
            System.out.println("Body check failed " + e);
            Assert.fail();
        }

    }

    private static JsonNode getApiResponseBody() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String api = response.toString();
        ObjectMapper actualDataMap = new ObjectMapper();
        actualDataJsonNode = actualDataMap.readTree(api);
        return actualDataJsonNode;
    }

    private static JsonNode getJsonMapData() throws JsonProcessingException {
        JSONObject data = Objects.requireNonNull(TestDataReader.testData()).getJSONObject("data");
        ObjectMapper objectMapper = new ObjectMapper();
        jsonNode = objectMapper.readTree(String.valueOf(data));
        return jsonNode;
    }

    /**
     * Method to check pathURL from Api and json
     *
     * @param apiName api
     */
    public static void checkPathURL(String apiName) throws JsonProcessingException {
        try {
            String actualResponseCode = String.valueOf(connection.getURL());
            switch (apiName) {
                case "SINGLE USER":
                        getExpectedURL = String.valueOf((getJsonMapData().get("single user").get("requestURL"))).replaceAll("\"", "");
                case "REGISTER_USER":
                        getExpectedURL = String.valueOf((getJsonMapData().get("register_user").get("requestURL"))).replaceAll("\"", "");
                case "CREATE":
                        getExpectedURL = String.valueOf((getJsonMapData().get("create").get("requestURL"))).replaceAll("\"", "");
                case "UPDATE_USER":
                        getExpectedURL = String.valueOf((getJsonMapData().get("update_user").get("requestURL"))).replaceAll("\"", "");
                case "DELETE_USER":
                        getExpectedURL = String.valueOf((getJsonMapData().get("delete_user").get("requestURL"))).replaceAll("\"", "");
                case "PATCH_UPDATE":
                        getExpectedURL = String.valueOf((getJsonMapData().get("patch_update").get("requestURL"))).replaceAll("\"", "");
                default: throw new AssertionError("No API present with this name!");
            }
            /*System.out.println("Expected ResponseCode : " + getExpectedURL);
            System.out.println("Actual ResponseCode : " + actualResponseCode);
            Assert.assertTrue("ResponseCode verified", actualResponseCode.contains(getExpectedURL));*/
        } catch (Exception e) {
            connection.disconnect();
            System.out.println("checkPathURL failed " + e);
        }
    }

}
