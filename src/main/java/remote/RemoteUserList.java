package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RemoteUserList extends UnicastRemoteObject implements IRemoteUserList{

    private String managerName = null;

    private List<String> userNames = null;

    private int userCounter = 0;

    // mapping for user: clientsID list
    private HashMap<String, Integer> usersIDList;

    // whiteboard managers ID
    private String managerID = null;

    // remote user list object
    private IRemoteUserList remoteUserList;

    // list of candidate users (don't know the purpose of this yet)
    // none of the candidate user functions will be implemented
    private List<String> candidateUsers;

    public RemoteUserList() throws RemoteException{
        userNames = new ArrayList<>();

        usersIDList = new HashMap<>();
        candidateUsers = new ArrayList<>();
    }


    /*
     *  Add user of username to user list if manager does not exist and if the manager exists then assigns username to
     * manager.
     * */
    synchronized public String registerUser(String userName){

        System.out.println("Register user function entered");

        String userID = String.format("%s (%d)", userName, userCounter);
        userCounter++;
        try{

            if (managerID == null){
                System.out.println(" this is a manager registered");
                managerID = userID;
                remoteUserList.setManagerName(managerID);
            }else{
                System.out.println("This is a client registered");
                remoteUserList.addUser(userID);
            }

        }catch (RemoteException e){
            System.out.println("UserManager class addUser method Remote Exception: " + e.getMessage());
        }

        return userID;

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
