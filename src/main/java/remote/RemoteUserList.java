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
    public void addUser(String userName) throws RemoteException {
        userNames.add(userName);
        System.out.println(Arrays.toString(userNames.toArray()));
    }

    @Override
    public List<String> getUserNames() throws RemoteException {
        return userNames;
    }

    @Override
    public String getManagerName() throws RemoteException {
        return managerName;
    }

    @Override
    public void setManagerName(String managerName) throws RemoteException {
        this.managerName = managerName;
    }
}
