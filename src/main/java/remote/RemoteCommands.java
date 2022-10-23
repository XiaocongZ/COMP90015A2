package remote;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteCommands extends UnicastRemoteObject implements IRemoteCommands {

    private DefaultListModel<String> commandsQueue;
    private Integer currentIndex;

    public RemoteCommands() throws RemoteException {
        super();
        this.commandsQueue = new DefaultListModel<String>();
        this.currentIndex = 0;
    }

    @Override
    public int commit(String command) throws RemoteException {
        System.out.println("Adding elements to command Queue");
        this.commandsQueue.add(currentIndex, command);
        currentIndex++;
        return (currentIndex - 1);
    }

    @Override
    public DefaultListModel<String> poll(Integer index) throws RemoteException {

        System.out.println("Polling items out of commandsQueue");

        Integer sizeOfList = this.commandsQueue.size();
        DefaultListModel<String> pollResultList = new DefaultListModel<String>();

        if (index >= sizeOfList) {
            System.out.println("(RemoteMessaging class) (Poll method) Index provided is out of bounds");
            return null;
        }

        for (int i = index; i < sizeOfList; i++) {

            String tempMessage = this.commandsQueue.get(i);
            pollResultList.addElement(tempMessage);

        }

        return pollResultList;

    }
}
