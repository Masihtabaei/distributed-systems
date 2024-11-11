package malim.server.stream_socket_single_threaded;

public class MalimServer {
    private int portNumber;
    private int connectionQueueMaximumSize;
    private String serverName;

    public MalimServer(){
        portNumber = 0;
        connectionQueueMaximumSize = 1;
        serverName = "Malim Server Default Name";
    }

    public MalimServer(int portNumber){
        this();
        this.portNumber = portNumber;
    }

    public MalimServer(int portNumber, int connectionQueueMaximumSize){
        this(portNumber);
        this.connectionQueueMaximumSize = connectionQueueMaximumSize;
    }

    public MalimServer(int portNumber, int connectionQueueMaximumSize, String serverName){
        this(portNumber, connectionQueueMaximumSize);
        this.serverName = serverName;
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
