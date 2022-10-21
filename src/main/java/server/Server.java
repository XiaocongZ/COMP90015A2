package server;

import remote.RemoteUserList;

import java.rmi.RemoteException;

public class Server {

    private static int serverPort;
    public static void main(String[] args) {

        try {

            RemoteUserList remoteUserList = new RemoteUserList();

        } catch (RemoteException e) {

            System.out.println("Server RemoteException: " + e.getMessage());

        }

    }
}
