package remote;

import javax.naming.CommunicationException;
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
    private List<String> candidateUsers;

    public UserManager() {
        usersIDList = new HashMap<>();
        candidateUsers = new ArrayList<>();
    }

    /*
    *  Add user of username to user list if manager does not exist and if the manager exists then assigns username to
    * manager.
    * */
    public synchronized String addUser(String userName){

        String uid = String.format("%s (%d)", userName, userCounter);

        try{

            if (managerID == null){
                managerID = uid;
                remoteUserList.setManagerName(managerID);
            }else{
                remoteUserList.addUser(uid);
            }

        }catch (RemoteException e){
            System.out.println("UserManager class addUser method Remote Exception: " + e.getMessage());
        }

        return uid;

    }


}
