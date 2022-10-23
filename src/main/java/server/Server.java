package server;

import remote.RemoteCommands;
import remote.RemoteMessages;
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
    private static final String remoteRemoteCommandsIdentifier = "RemoteCommands";
    private static final String remoteRemoteMessagesIdentifier = "RemoteMessages";

    public static void main(String[] args) {



        try {

            // this calls in a remote object
            RemoteUserList remoteUserList = new RemoteUserList();
            RemoteCommands remoteCommands = new RemoteCommands();
            RemoteMessages remoteMessages = new RemoteMessages();

            Registry registry = LocateRegistry.createRegistry(serverPort);

            String IPaddress = "127.0.0.1";

            String remoteUserListUrl = "rmi://" + IPaddress + ":" +
                    String.valueOf(serverPort) + "/" + remoteUserListServerIdentifier;
            String remoteCommandsUrl = "rmi://" + IPaddress + ":" +
                    String.valueOf(serverPort) + "/" + remoteRemoteCommandsIdentifier;
            String remoteMessagesUrl = "rmi://" + IPaddress + ":" +
                    String.valueOf(serverPort) + "/" + remoteRemoteMessagesIdentifier;

            System.out.println(remoteUserListUrl);

            registry.rebind(remoteUserListUrl, remoteUserList);
            registry.rebind(remoteCommandsUrl, remoteCommands);
            registry.rebind(remoteMessagesUrl, remoteMessages);

            System.out.println("Server is ready");


        } catch (RemoteException e) {

            System.out.println("Server RemoteException: " + e.getMessage());

        }

    }
}
