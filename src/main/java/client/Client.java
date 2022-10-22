package client;

import remote.IRemoteUserList;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * The main class in package @see COMP90015G72.client
 * Boots up several threads for texting, drawing, etc.
 */
public class Client {

    private static final String remoteUserListName = "RemoteUserList";
    private static int serverPort = 5859;

    public static void main(String[] args){

        ClientUI myUI = new ClientUI();
        IRemoteUserList remoteUserList = null;


        try{
            String remoteUserListURL =
                    "rmi://" + "127.0.0.1"+
                    ":" +
                    String.valueOf(serverPort) + "/" + remoteUserListName;

            System.out.println(remoteUserListURL);
            Registry registry = LocateRegistry.getRegistry(serverPort);
            remoteUserList = (IRemoteUserList) registry.lookup(remoteUserListURL);

            
            System.out.println("Successfully retrieved remote user list");

        } catch (NotBoundException | RemoteException e) {
            System.out.println("Exception when retrieving remote user list: " + e);
        }

    }

}
