package com.BookStore;

import java.io.*;

public class Message implements Serializable {
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    private static String LINE_SEPARATOR = System.getProperty("line.separator");

    private String header;
    private String body;

    public Message() {
    }

    public Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public String body() {
        return body;
    }

    public String header() {
        return header;
    }

    @Override
    public String toString() {
        return "Message{" + "header='" + header + '\'' + ", body='" + body + '\'' + '}';
    }

    public void writeTo(OutputStream os) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(header).append(LINE_SEPARATOR).append(body);
        os.write(sb.toString().getBytes());
    }

    public void readFrom(InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            header = br.readLine();
            body = br.readLine();
        }
    }

}
