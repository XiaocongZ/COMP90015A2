package remote;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRemoteMessages extends Remote {

    /*
    * Given Message message, the method adds the message to a queue of messages
    * */

    public int commit(String message) throws RemoteException;

    /*
    * Given an index, the method returns messages starting from the index
    * */
    public List<String> poll(Integer index) throws RemoteException;

}
