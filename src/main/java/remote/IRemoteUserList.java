package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRemoteUserList extends Remote {

    public void addUser(String userName) throws RemoteException;

    List<String> getUserNames() throws RemoteException;

    String getManagerName() throws RemoteException;

    void setManagerName(String managerName) throws RemoteException;

}
