package com.example.LoadBalancer.Algorithms;

import java.util.ArrayList;
    
import com.example.LoadBalancer.Algorithms.ServerObject.Servers;

public abstract class BalancingAlgorithm {
    
        public ServerObject serverObject;
        public ArrayList<Servers> servers;
        public int size;
    
        public BalancingAlgorithm(){
            this.serverObject = new ServerObject();
        
            this.servers = serverObject.getServers();
            this.size = servers.size();
        }
    
        public abstract String getHost();
    
        public abstract int getPort();
    
}
