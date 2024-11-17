package malim.protocol.approach.second;

public class MalimQUITRequestMessage extends MalimMessage{
    public MalimQUITRequestMessage(int simulationTime, String message){
        messageWrapped = MalimUtil.createRequestMessageForQUIT(simulationTime, message);
    }

    public int getSimulationTime(){
        return messageWrapped.getRequestParameters().getQuitRequestParameters().getSimulationTime();
    }

    public String message(){
        return messageWrapped.getRequestParameters().getQuitRequestParameters().getMessage();
    }
}
