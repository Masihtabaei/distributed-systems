package malim.server.stream_socket_single_threaded.approach.second;

import java.net.Socket;

public class MalimClientInQueue {

    private final int id;
    private final Socket socket;

    public MalimClientInQueue(int id, Socket clientSocketToWrap) {
        this.id = id;
        socket = clientSocketToWrap;
    }

    public int getId(){
        return id;
    }

    public void recieveMyReply(){
        
    }

    public void sendMeRequest(){

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object malimClientInQueue) {
        if (this == malimClientInQueue) {
            return true;
        }
        if (malimClientInQueue == null) {
            return false;
        }
        if (malimClientInQueue.getClass() != getClass()) {
            return false;
        }

        return this.id == (((MalimClientInQueue) malimClientInQueue).id);
    }

}
