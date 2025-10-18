package com.haloofwar.network.components;

import java.net.InetAddress;

import com.haloofwar.common.enumerators.PlayerType;

public class Client {
    private InetAddress ip;
    private int port;
    public PlayerType playerType;

    public Client(InetAddress ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public InetAddress getIp() {
        return this.ip;
    }

    public int getPort() {
        return this.port;
    }

    public String getKey(){
        return this.ip.getHostAddress() + ":" + this.port;
    }
}
