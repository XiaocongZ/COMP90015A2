package remote;

import javax.naming.CommunicationException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserManager {

    // number of users
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

    public UserManager() {
        usersIDList = new HashMap<>();
        candidateUsers = new ArrayList<>();
    }

    /*
    *  Add user of username to user list if manager does not exist and if the manager exists then assigns username to
    * manager.
    * */
    public synchronized String registerUser(String userName){

        String userID = String.format("%s (%d)", userName, userCounter);

        try{

            if (managerID == null){
                managerID = userID;
                remoteUserList.setManagerName(managerID);
            }else{
                remoteUserList.addUser(userID);
            }

        }catch (RemoteException e){
            System.out.println("UserManager class addUser method Remote Exception: " + e.getMessage());
        }

        return userID;

    }

    public void setRemoteUserList(IRemoteUserList remoteUserList){
        this.remoteUserList = remoteUserList;
    }

    // not very sure of this function yet
    public synchronized void removeUser(String userID){
        try{
            if (remoteUserList.getUserNames().contains(userID)){
                remoteUserList.getUserNames().remove(userID);
            }
        } catch (IOException e){
            System.out.println("UserManager class | removeUser method IOException: " + e.getMessage());
        }

        if (usersIDList.containsKey(userID)){
            usersIDList.remove(userID);
        }

    }


}
