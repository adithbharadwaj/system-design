package com.example.LoadBalancer.Algorithms;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Qualifier("random")
@Configuration
public class Random extends BalancingAlgorithm{

    @Override
    public String getHost() {
        return "localhost";
    }

    @Override
    public int getPort() {
        int randomInt = (int)(Math.random() * (this.size));
        return this.servers.get(randomInt).getPort();
    }
}
