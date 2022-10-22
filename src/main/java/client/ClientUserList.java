package client;

import remote.IRemoteUserList;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class ClientUserList {

    private Lock userlistLock = new ReentrantLock();

    //acquire when modifying listModel
    private DefaultListModel listModel;
    private IRemoteUserList remoteUserList;

    public DefaultListModel getListModel(){
        return listModel;
    }

    public void setListModel(DefaultListModel listModel){
        this.listModel = listModel;
    }

    public ClientUserList(DefaultListModel listModel, IRemoteUserList remoteUserList){
        if(listModel!=null) {
            this.listModel = listModel;
        }else{
            this.listModel = new DefaultListModel();
        }
        this.remoteUserList = remoteUserList;

    }

    /**
     * Only call once when log into server, after first update()
     * @param name
     * @throws RemoteException
     */
    synchronized public void add(String name) throws RemoteException {

        listModel.addElement(name);
        userlistLock.unlock();

        remoteUserList.addUser(name);

    }

    synchronized public void removeElement(String name) throws RemoteException {
        userlistLock.lock();
        listModel.removeElement(name);
        userlistLock.unlock();
        remoteUserList.removeUser(name);
    }

    /**
     * Only invoked by updating thread, not by user's event
     * @throws RemoteException
     */
    public void update() throws RemoteException {
        List<String> users = remoteUserList.getUserNames();
        userlistLock.lock();
        listModel.removeAllElements();
        listModel.addAll(users);
        userlistLock.unlock();
    }
}
