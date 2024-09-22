package com.example.LoadBalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SocketServer implements CommandLineRunner{

    @Autowired
    public PacketForwarder packetForwarder;

    @Override
    public void run(String... args) throws Exception {
        packetForwarder.forwardPackets();
    }
}