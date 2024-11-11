package malim.server.stream_socket_single_threaded;

import java.io.IOException;
import java.net.ServerSocket;

public class MalimServer {
    private int portNumber;
    private int connectionQueueMaximumSize;
    private String serverName;
    private ServerSocket serverSocket;

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
        this.portNumber = portNumber;
        this.connectionQueueMaximumSize = connectionQueueMaximumSize;
        this.serverName = serverName;
        createServerSocket();
    }

    private void createServerSocket(){
        if(serverSocket == null){
            try {
                serverSocket = new ServerSocket(portNumber, connectionQueueMaximumSize);
            } catch (IOException exceptionCaught) {
                System.err.println(exceptionCaught.getMessage());
            }
        }
    }

    public int getPortNumber(){
        return portNumber;
    }

    public int getConnectionQueueMaximumSize(){
        return connectionQueueMaximumSize;
    }

    public String getServerName(){
        return serverName;
    }
}
