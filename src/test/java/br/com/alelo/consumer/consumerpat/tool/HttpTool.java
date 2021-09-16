package br.com.alelo.consumer.consumerpat.tool;

import lombok.AllArgsConstructor;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@AllArgsConstructor
public class HttpTool {

    private String baseUrl;

    public HttpResponse post(String endpoint, String body) throws IOException {
        return requestWithBody("POST", endpoint, body);
    }

    public HttpResponse put(String endpoint, String body) throws IOException {
        return requestWithBody("PUT", endpoint, body);
    }

    private HttpResponse requestWithBody(String method, String endpoint, String body) throws IOException {
        URL url = new URL(baseUrl + "/" + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
        writer.write(body);
        writer.close();
        int code = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                code > 299 ? con.getErrorStream() : con.getInputStream()
        ));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) content.append(inputLine);
        in.close();
        con.disconnect();
        return new HttpResponse(code, content.toString());
    }

    public HttpResponse get(String endpoint) throws IOException {
        URL url = new URL(baseUrl + "/" + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        int code = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                code > 299 ? con.getErrorStream() : con.getInputStream()
        ));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) content.append(inputLine);
        in.close();
        con.disconnect();
        return new HttpResponse(code, content.toString());
    }
}
