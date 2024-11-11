package malim.server.stream_socket_single_threaded;

import java.io.IOException;
import java.net.ServerSocket;

public class MalimServer {
    private int portNumber;
    private int connectionQueueMaximumSize;
    private String serverName;
    private ServerSocket serverSocket;

    public MalimServer() throws IOException{
        this(0);
    }

    public MalimServer(int portNumber) throws IOException{
        this(portNumber, 1);
    }

    public MalimServer(int portNumber, int connectionQueueMaximumSize) throws IOException{
        this(portNumber, connectionQueueMaximumSize, "Malim Server Default Name");
    }

    public MalimServer(int portNumber, int connectionQueueMaximumSize, String serverName) throws IOException{
        this.portNumber = portNumber;
        this.connectionQueueMaximumSize = connectionQueueMaximumSize;
        this.serverName = serverName;
        createServerSocket();
    }

    private void createServerSocket() throws IOException{
        if(serverSocket == null){
            serverSocket = new ServerSocket(portNumber, connectionQueueMaximumSize);
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
