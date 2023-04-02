package ro.ubb.message;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Message {

    public static final String OK = "OK";
    public static final String ERR = "ERROR";

    private String header;
    private String body;

    public Message(){}

    public Message(String header, String body){
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public void readMessage(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        header = br.readLine();
        body = br.readLine();
    }

    public void sendMessage(OutputStream outputStream) throws IOException {
        outputStream.write((header + "\n" + body + "\n").getBytes());
    }

    @Override
    public String toString() {
        return "Message{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
