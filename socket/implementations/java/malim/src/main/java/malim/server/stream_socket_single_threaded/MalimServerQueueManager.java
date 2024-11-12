package malim.server.stream_socket_single_threaded;

import java.net.Socket;

public class MalimServerQueueManager {

    private int clientCounter;
    private MalimClientInQueue[] clientSockets;

    public MalimServerQueueManager() {
        this(1);
    }

    public MalimServerQueueManager(int maximumQueueSize) {
        clientCounter = 0;
        clientSockets = new MalimClientInQueue[maximumQueueSize];
    }

    public int addClient(Socket clientToAdd) {
        MalimClientInQueue clientToAddInQueue = new MalimClientInQueue(clientCounter, clientToAdd);
        clientSockets[clientToAddInQueue.getId()] = clientToAddInQueue;
        clientCounter++;
        return clientToAddInQueue.getId();
    }

    public void removeClient(int clientToRemoveId) {
        clientSockets[clientToRemoveId] = null;
        clientCounter--;
    }

    public Socket getClientSocket(int clientSocketToRetrieveId){
        return clientSockets[clientSocketToRetrieveId].getSocket();
    }

    public int getQueueMaximumSize(){
        return clientSockets.length;
    }

}
