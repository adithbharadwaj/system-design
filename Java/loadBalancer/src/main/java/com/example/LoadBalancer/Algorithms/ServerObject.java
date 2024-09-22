package com.example.LoadBalancer.Algorithms;

import java.util.ArrayList;

public class ServerObject {

    class Servers{
        private String host;
        private int port;

        public Servers(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public String getHost(){
            return this.host;
        }

        public int getPort(){
            return this.port;
        }
    }

    private ArrayList<Servers> ServerList;

    public ServerObject(){
        this.ServerList = new ArrayList<Servers>();

        this.ServerList.add(new Servers("localhost", 8083));
        this.ServerList.add(new Servers("localhost", 8084));
        this.ServerList.add(new Servers("localhost", 8085));
    }

    public ArrayList<Servers> getServers(){
        return this.ServerList;
    }

}
