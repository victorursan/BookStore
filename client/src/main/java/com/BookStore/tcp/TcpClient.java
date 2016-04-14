package com.BookStore.tcp;

import com.BookStore.ControllerServiceException;
import com.BookStore.Message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by victor on 4/13/16.
 */
public class TcpClient {
    private String serviceHost;
    private int servicePort;

    public TcpClient(String serviceHost, int servicePort) {
        this.serviceHost = serviceHost;
        this.servicePort = servicePort;
    }

    public Message sendAndReceive(Message request) {
        OutputStream outputStream = null;
        System.out.println("Connecting to service");
        try (Socket socket = new Socket(serviceHost, servicePort)) {
            outputStream = socket.getOutputStream();
            request.writeTo(outputStream);
            outputStream.flush();
            System.out.println("Request sent: " + request.toString());

            Message response = new Message();
            response.readFrom(socket.getInputStream());
            if (response.header().equalsIgnoreCase(Message.OK)) {
                System.out.println("Response OK: " + response.toString());
                return response;
            } else {
                System.out.println("Response ERROR: " + response.toString());
                throw new ControllerServiceException(response.body());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ControllerServiceException(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
