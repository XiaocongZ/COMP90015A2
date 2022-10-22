package client;

import remote.IRemoteUserList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The main class in package @see COMP90015G72.client
 * Boots up several threads for texting, drawing, etc.
 */
public class Client {

    private static final String remoteUserListURL = "//localhost/serverRMIObject";

    public static void main(String[] args){

        ClientUI myUI = new ClientUI();
        IRemoteUserList remoteUserList = null;

        try{
            remoteUserList = (IRemoteUserList) Naming.lookup(remoteUserListURL);
            System.out.println("Successfully retrieved remote user list");

        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println("Exception when retrieving remote user list: " + e.getMessage());
        }

    }

}
