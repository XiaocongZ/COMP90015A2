package client;

import remote.IRemoteUserList;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The main class in package @see COMP90015G72.client
 * Boots up several threads for texting, drawing, etc.
 */
public class Client {

    private static final String remoteUserListName = "remoteUserList";
    private static int serverPort = 5859;

    public static void main(String[] args){

        ClientUI myUI = new ClientUI();
        IRemoteUserList remoteUserList = null;


        try{
            String remoteUserListURL =
                    "rmi://" +
                    InetAddress.getLocalHost().getHostAddress()+
                    ":" +
                    String.valueOf(serverPort) + "/" + remoteUserListName;

            System.out.println(remoteUserListURL);

            remoteUserList = (IRemoteUserList) Naming.lookup(remoteUserListURL);

            System.out.println("completed");

            System.out.println(remoteUserList.getUserNames());
            System.out.println("Successfully retrieved remote user list");

        } catch (MalformedURLException | NotBoundException | RemoteException | UnknownHostException e) {
            System.out.println("Exception when retrieving remote user list: " + e);
        }

    }

}
