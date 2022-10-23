package remote;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteCommands extends Remote {

    /*
    * This class is used to store/set/retrieve whiteboard commands
    * */

    /*
     * Given a command, the method adds the command to a queue of commands
     * */

    public int commit(String command) throws RemoteException;

    /*
     * Given an index, the method returns all the messages following the index mentioned
     * */
    public DefaultListModel<String> poll(Integer index) throws RemoteException;

}
