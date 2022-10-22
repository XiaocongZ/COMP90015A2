package client;

import remote.IRemoteUserList;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.List;

public class ClientUserList {
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

    public void add(String name) throws RemoteException {
        listModel.addElement(name);
        remoteUserList.addUser(name);
    }

    public void removeElement(String name) throws RemoteException {
        listModel.removeElement(name);
        remoteUserList.removeUser(name);
    }

    public void update() throws RemoteException {
        List<String> users = remoteUserList.getUserNames();
        listModel.removeAllElements();
        listModel.addAll(users);
    }
}
