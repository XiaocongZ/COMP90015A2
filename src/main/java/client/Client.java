package client;

import remote.IRemoteUserList;

import javax.swing.*;
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

    private final String name;

    private ClientUserList clientUserList;


    public static void main(String[] args){
        Client myClient =new Client("Cname");


    }

    public Client(String name){
        this.name =name;
        ClientUI myUI = new ClientUI();

        DefaultListModel listModel = myUI.getListModel();
        IRemoteUserList remoteUserList = null;

        try{
            remoteUserList = (IRemoteUserList) Naming.lookup(remoteUserListURL);
            System.out.println("Successfully retrieved remote user list");

        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println("Exception when retrieving remote user list: " + e.getMessage());
        }

        clientUserList = new ClientUserList(listModel, remoteUserList);
    }

}
