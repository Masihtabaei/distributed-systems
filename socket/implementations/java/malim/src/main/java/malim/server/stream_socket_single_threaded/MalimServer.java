package malim.server.stream_socket_single_threaded;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MalimServer {
    private boolean continueWork;
    private int portNumber;
    private String serverName;
    private ServerSocket serverSocket;
    private MalimServerQueueManager serverSocketQueueManager;
    public MalimServer(){
        this(0);
    }

    public MalimServer(int portNumber){
        this(portNumber, 1);
    }

    public MalimServer(int portNumber, int connectionQueueMaximumSize){
        this(portNumber, connectionQueueMaximumSize, "Malim Server Default Name");
    }

    public MalimServer(int portNumber, int connectionQueueMaximumSize, String serverName){
        continueWork = true;
        this.portNumber = portNumber;
        this.serverName = serverName;
        serverSocketQueueManager = new MalimServerQueueManager(connectionQueueMaximumSize);
        setupServerSocket();
        acceptSocketClient();
    }

    private void setupServerSocket(){
        if(serverSocket == null){
            try {
                serverSocket = new ServerSocket(portNumber, serverSocketQueueManager.getQueueMaximumSize());
            } catch (IOException exceptionCaught) {
                System.err.println(exceptionCaught.getMessage());
            }
        }
    }

    private void acceptSocketClient(){
            try (Socket acceptedSocketClient = serverSocket.accept()) {
                int clientAcceptedId = serverSocketQueueManager.addClient(acceptedSocketClient);
                startCommunicationWithSocketClient(clientAcceptedId);
            } catch (IOException exceptionCaught) {
                System.err.println(exceptionCaught.getMessage());
            }

    }

    private void startCommunicationWithSocketClient(int clientInQueueToCommunicateId){
        while (continueWork) {
            
        }

    }

    public int getPortNumber(){
        return portNumber == 0 ? serverSocket.getLocalPort() : portNumber;
    }

    public String getServerName(){
        return serverName;
    }

    public void stopServer(){

    }
}
