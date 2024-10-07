package com.example.LoadBalancer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.catalina.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.LoadBalancer.Algorithms.BalancingAlgorithm;
import com.example.LoadBalancer.Workers.WorkerThread;

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

                Thread clientThread = new Thread(new WorkerThread(clientSocket, serverSocket));
                Thread serverThread = new Thread(new WorkerThread(serverSocket, clientSocket));

                clientThread.start();
                serverThread.start();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            socket.close();
        }
    }
}
