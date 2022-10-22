package server;

import remote.RemoteUserList;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    private static int serverPort = 5859;

    private static final String remoteUserListServerIdentifier = "RemoteUserList";

    public static void main(String[] args) {



        try {

            // this calls in a remote object
            RemoteUserList remoteUserList = new RemoteUserList();

            Registry registry = LocateRegistry.createRegistry(serverPort);

            String remoteUserListUrl = "rmi://" + "127.0.0.1" + ":" +
                    String.valueOf(serverPort) + "/" + remoteUserListServerIdentifier;

            System.out.println(remoteUserListUrl);

            registry.rebind(remoteUserListUrl, remoteUserList);

            System.out.println("Server is ready");


        } catch (RemoteException e) {

            System.out.println("Server RemoteException: " + e.getMessage());

        }

    }
}
