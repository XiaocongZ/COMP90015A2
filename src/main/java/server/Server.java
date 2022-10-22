package server;

import remote.RemoteUserList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {

    private static int serverPort;

    private static final String remoteUserListServerIdentifier = "serverRMIObject";
    public static void main(String[] args) {



        try {

            // this calls in a remote object
            RemoteUserList remoteUserList = new RemoteUserList();

            Naming.rebind(remoteUserListServerIdentifier, remoteUserList);

            System.out.println("Server is ready");


        } catch (RemoteException e) {

            System.out.println("Server RemoteException: " + e.getMessage());

        } catch (MalformedURLException e) {
            System.out.println(" Server MalformedURLException : " + e.getMessage());
        }

    }
}
