package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRemoteUserList extends Remote {

    // adds user to the user list
    public void addUser(String userName) throws RemoteException;

    // gets the list of users connected to the server as of now
    List<String> getUserNames() throws RemoteException;

    // gets the manager of the server
    String getManagerName() throws RemoteException;

    // only used when createWhiteboard, sets manager name
    void setManagerName(String managerName) throws RemoteException;

}
