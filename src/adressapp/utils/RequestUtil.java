package adressapp.utils;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RequestUtil implements Runnable {

    private static final String ROOT_URL = "http://localhost:8080";
    private static final int TIMEOUT = 5000;
    private Boolean disconnect = false;
    private Map<String, String> params;
    private final String method;
    private String json;
    private String response;
    public Thread thread;
    private URL url;

    @Override
    public void run() {
        System.out.println("Name thread: " + thread.getName());
        response = send(thread.getName());
    }

    public RequestUtil(String url, String method) {
        this.method = method;
        this.thread = new Thread(this, url);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String send(String url) {
        try {
            System.out.println(ROOT_URL + url);
            this.url = new URL(ROOT_URL + url);
            HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty( "Accept", "application/json");
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            if (method.equals("POST") || method.equals("PUT")) {
                try(OutputStream os = conn.getOutputStream()) {
                    byte[] input = json.getBytes("utf-8");
                    os.write(input);
                }
            }


//            if (method.equals("POST")) {
//                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//                wr.writeBytes(json);
//                wr.write(json);
//            }

            System.out.println(conn.getResponseCode());

            return readInputStream(conn);
        } catch (ConnectException e) {
            setDisconnect(true);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void setDisconnect(Boolean disconnect) {
        this.disconnect = disconnect;
    }

    public void setParams(Map<String, String> params) { this.params = params; }

    public String getResponse() { return response; }

    public Boolean getDisconnect() { return disconnect; }

    public static int getTIMEOUT() { return TIMEOUT; }

    public URL getUrl() { return url; }


    private static String readInputStream(HttpURLConnection conn) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            conn.disconnect();
            return content.toString();
        } catch (IOException e) {
            conn.disconnect();
            return null;
        }
    }
}
