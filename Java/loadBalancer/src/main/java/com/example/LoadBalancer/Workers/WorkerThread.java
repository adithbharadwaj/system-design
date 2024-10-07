package com.example.LoadBalancer.Workers;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class WorkerThread implements Runnable{

    private Socket to, from;

    public WorkerThread(Socket from, Socket to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[4096];
        int bytesRead;
        try{
            InputStream in = this.from.getInputStream();
            OutputStream out = this.to.getOutputStream();

            while((bytesRead = in.read(buffer)) != -1){
                out.write(buffer, 0, bytesRead);
                out.flush();
            }
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
