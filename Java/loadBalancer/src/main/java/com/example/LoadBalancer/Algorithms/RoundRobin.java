package com.example.LoadBalancer.Algorithms;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Qualifier("roundRobin")
@Configuration
public class RoundRobin extends BalancingAlgorithm{

    private int cur;

    public RoundRobin(){ 
        this.cur = -1;
    }

    @Override
    public String getHost() {
        return "localhost";
    }

    @Override
    public int getPort() {
        this.cur = (this.cur + 1)%this.size;
        System.out.println("Cur: " + this.cur + " Size: " + this.size);
        return servers.get(cur).getPort();
    }
}
