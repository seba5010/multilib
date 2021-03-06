package com.aaronjwood.multilib.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public final class Post extends HttpRequest {

    private String data;
    private boolean ssl;

    /**
     * Sets the data to be sent along with the request and specifies if the
     * connection should be secure or not
     *
     * @param data The data to be sent along with the request
     * @param ssl Indicates if the connection should be secure or not
     * @throws java.io.UnsupportedEncodingException
     */
    public Post(Map<String, String> data, boolean ssl) throws UnsupportedEncodingException {
        this.data = this.setData(data);
        this.ssl = ssl;
    }

    /**
     * Sends a POST request to the specified URL
     *
     * @param url The URL to post to
     * @return The response data
     * @throws IOException
     */
    @Override
    public String sendRequest(String url) throws IOException {
        HttpURLConnection connection;

        //Do we need a secure connection or not?
        if (this.ssl == true) {
            connection = (HttpsURLConnection) new URL(url).openConnection();
        }
        else {
            connection = (HttpURLConnection) new URL(url).openConnection();
        }

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        StringBuilder response;
        try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
            writer.write(this.data);
            writer.flush();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
        }

        return response.toString();
    }

    /**
     * Processes the map being passed and returns a properly formatted string
     * for the POST request
     *
     * @param data The map containing the key/value pairs
     * @return A properly formatted string of data for the POST request
     * @throws java.io.UnsupportedEncodingException
     */
    @Override
    protected String setData(Map<String, String> data) throws UnsupportedEncodingException {
        StringBuilder requestString = new StringBuilder();
        int counter = 0;
        for (Map.Entry<String, String> value : data.entrySet()) {
            counter++;
            requestString.append(URLEncoder.encode(value.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(value.getValue(), "UTF-8"));
            if (counter < data.size()) {
                requestString.append("&");
            }
        }
        return requestString.toString();
    }
}
