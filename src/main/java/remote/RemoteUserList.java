package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoteUserList extends UnicastRemoteObject implements IRemoteUserList{

    private String managerName = null;

    private List<String> userNames = null;

    public RemoteUserList() throws RemoteException{
        userNames = new ArrayList<>();
    }

    /*
    *  Adds user to a list
    * */
    @Override
    synchronized public void addUser(String userName) throws RemoteException {
        userNames.add(userName);
        System.out.println(Arrays.toString(userNames.toArray()));
    }

    synchronized public void removeUser(String userName) throws RemoteException {
        userNames.remove(userName);
        System.out.println(Arrays.toString(userNames.toArray()));
    }

    @Override
    synchronized public List<String> getUserNames() throws RemoteException {
        return userNames;
    }

    @Override
    synchronized public String getManagerName() throws RemoteException {
        return managerName;
    }

    @Override
    synchronized public void setManagerName(String managerName) throws RemoteException {
        this.managerName = managerName;
    }
}
