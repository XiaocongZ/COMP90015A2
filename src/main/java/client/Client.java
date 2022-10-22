package client;

import remote.IRemoteUserList;

import javax.swing.*;
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

    private final String name;

    private ClientUserList clientUserList;


    public static void main(String[] args){
        Client myClient =new Client("Cname");


    }

    public Client(String name){
        this.name =name;

        IRemoteUserList remoteUserList = null;
        String IPaddress = "127.0.0.1";

        try{
            String remoteUserListURL =
                    "rmi://" + IPaddress +
                    ":" +
                    String.valueOf(serverPort) + "/" + remoteUserListName;

            System.out.println(remoteUserListURL);
            Registry registry = LocateRegistry.getRegistry(serverPort);
            remoteUserList = (IRemoteUserList) registry.lookup(remoteUserListURL);


            System.out.println("Successfully retrieved remote user list");

        } catch (NotBoundException | RemoteException e) {
            System.out.println("Exception when retrieving remote user list: " + e);
        }

        DefaultListModel listModel = new DefaultListModel();
        this.clientUserList = new ClientUserList(listModel, remoteUserList);
        ClientUI myUI = new ClientUI(clientUserList);
    }

}
