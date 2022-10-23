package client;

import remote.IRemoteMessages;
import remote.IRemoteUserList;

import javax.swing.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * The main class in package @see COMP90015G72.client
 * Boots up several threads for texting, drawing, etc.
 */
public class Client {

    private static final String remoteUserListName = "RemoteUserList";
    private static final String remoteCommandsIdentifier = "RemoteCommands";
    private static final String remoteMessagesIdentifier = "RemoteMessages";
    private static int serverPort = 5859;

    private final String name;

    private ClientUserList clientUserList;

    private ClientMessenger clientMessenger;


    public static void main(String[] args){
        Client myClient =new Client("Cname");


    }

    private Remote lookupRemote(String remoteName){
        String IPaddress = "127.0.0.1";
        try{
            String remoteUserListURL =
                    "rmi://" + IPaddress +
                            ":" +
                            String.valueOf(serverPort) + "/" + remoteName;

            System.out.println(remoteUserListURL);
            Registry registry = LocateRegistry.getRegistry(serverPort);
            Remote remote = registry.lookup(remoteUserListURL);

            if(remote!=null) {
                System.out.println("Successfully retrieved remote user list");
                return remote;
            }

        } catch (NotBoundException | RemoteException e) {
            System.err.println("Exception retrieving rmi remote: " + e);
        }

        return null;
    }

    public Client(String name){
        this.name =name;

        IRemoteUserList remoteUserList = (IRemoteUserList) lookupRemote(remoteUserListName);
        IRemoteMessages remoteMessages = (IRemoteMessages) lookupRemote(remoteMessagesIdentifier);


        //The order of constructing is intertwined
        DefaultListModel listModel = new DefaultListModel();
        this.clientUserList = new ClientUserList(listModel, remoteUserList);
        ClientUI myUI = new ClientUI(clientUserList);

        Thread userUpdator = new Thread(new UserThread(clientUserList));
        userUpdator.setDaemon(true);
        userUpdator.start();

        this.clientMessenger = myUI.getClientMessenger();
        this.clientMessenger.setIRemoteMessages(remoteMessages);
        Thread messengerUpdator = new Thread(new MessengerThread(clientMessenger));
        messengerUpdator.setDaemon(true);
        messengerUpdator.start();


    }

}
