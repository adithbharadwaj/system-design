package com.example.LoadBalancer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.LoadBalancer.Algorithms.BalancingAlgorithm;

@Component
public class PacketForwarder{

    private BalancingAlgorithm balancingAlgo;

    @Autowired
    public PacketForwarder(@Qualifier("random") BalancingAlgorithm balancingAlgo){
        this.balancingAlgo = balancingAlgo;
    }
   
    @Bean
    public void forwardPackets() throws IOException, InterruptedException{

        ServerSocket socket = new ServerSocket(ApplicationConfig.CLIENT_PORT);
        System.out.println("Listening on port " + ApplicationConfig.CLIENT_PORT);

        try{
            while(true){
                Socket clientSocket = socket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                String host = this.balancingAlgo.getHost();
                int port = this.balancingAlgo.getPort();

                Socket serverSocket = new Socket(host, port);
                System.out.println("Server connected: " + serverSocket.getInetAddress() + " Port: " + serverSocket.getPort());

                Thread clientThread = new Thread(() -> {
                    handleForwarding(clientSocket, serverSocket);
                });

                Thread serverThread = new Thread(() -> {
                    handleForwarding(serverSocket, clientSocket);
                });

                clientThread.start();
                serverThread.start();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            socket.close();
        }
    }

    public void handleForwarding(Socket from, Socket to){
        byte[] buffer = new byte[4096];
        int bytesRead;
        try{
            InputStream in = from.getInputStream();
            OutputStream out = to.getOutputStream();

            while((bytesRead = in.read(buffer)) != -1){
                out.write(buffer, 0, bytesRead);
                out.flush();
            }
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
