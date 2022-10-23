package remote;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RemoteMessages extends UnicastRemoteObject implements IRemoteMessages {

    private ArrayList<String> messageArray;

    private Lock messageLock;

    public RemoteMessages() throws RemoteException {
        super();
        this.messageArray = new ArrayList<String>();
        messageLock = new ReentrantLock();
    }

    @Override
    public int commit(String message) throws RemoteException {
        System.out.println("Adding elements to message Queue");
        messageLock.lock();
        messageArray.add(message);
        int index = messageArray.size() - 1;
        messageLock.unlock();
        System.out.println(messageArray.toString());
        //return the index
        return index;
    }

    /**
     * No locking design for poll, for now
     * @param index
     * @return
     * @throws RemoteException
     */
    @Override
    public List<String> poll(Integer index) throws RemoteException {

        System.out.println("Polling " + index);

        if (index >= messageArray.size()) {
            System.out.println("(RemoteMessaging class) (Poll method) Index already updated");
            return null;
        }
        ArrayList<String> res = new ArrayList<String>(messageArray.subList(index, messageArray.size()));
        return res;
    }
}
