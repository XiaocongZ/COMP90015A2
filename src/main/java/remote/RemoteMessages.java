package remote;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteMessages extends UnicastRemoteObject implements IRemoteMessages {

    private DefaultListModel<String> messageQueue;
    private Integer currentIndex;

    public RemoteMessages() throws RemoteException {
        super();
        this.messageQueue = new DefaultListModel<String>();
        this.currentIndex = 0;
    }

    @Override
    public int commit(String message) throws RemoteException {
        System.out.println("Adding elements to message Queue");
        messageQueue.add(currentIndex, message);
        currentIndex++;
        return (currentIndex - 1);
    }

    @Override
    public DefaultListModel<String> poll(Integer index) throws RemoteException {

        System.out.println("Polling items out of messageQueue");

        Integer sizeOfList = messageQueue.size();
        DefaultListModel<String> pollResultList = new DefaultListModel<String>();

        if (index >= sizeOfList) {
            System.out.println("(RemoteMessaging class) (Poll method) Index provided is out of bounds");
            return null;
        }

        for (int i = index; i < sizeOfList; i++) {

            String tempMessage = messageQueue.get(i);
            pollResultList.addElement(tempMessage);

        }

        return pollResultList;

    }
}
