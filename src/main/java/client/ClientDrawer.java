package client;

import remote.IRemoteCP;

import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientDrawer {

    private ClientUI clientUI;
    private IRemoteCP<String[]> iRemoteCommand;

    private Lock graphicsLock;

    private int ack;

    public ClientDrawer(ClientUI clientUI, IRemoteCP<String[]> iRemoteCommand){
        this.clientUI = clientUI;
        this.iRemoteCommand = iRemoteCommand;
        graphicsLock = new ReentrantLock();
        ack = 0;
    }

    public void commit(String[] command) throws RemoteException {
        /*
        chatAreaLock.lock();
        String text = chatArea.getText() + msg + "\n";
        chatArea.setText(text);
        chatAreaLock.unlock();
        */
        int ack_ = iRemoteCommand.commit(command);
        // in case of exception
        ack = ack_;
    }

    public void poll() throws RemoteException {
        List<String[]> commandList = iRemoteCommand.poll(ack);
        if(commandList == null){
            return;
        }
        graphicsLock.lock();

        for(String[] s: commandList){
            clientUI.draw(s);
        }

        ack += commandList.size();
        graphicsLock.unlock();
    }
}
