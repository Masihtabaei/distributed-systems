package malim.protocol.approach.first;

public class FolderRequest {

    private int operationType;
    private int simulationTime;
    private int numberOfParameter;
    private int[] parameterList; // Array or List
    private int messageLength;
    private String message;


    public FolderRequest() {
    }

    public FolderRequest(int operationType, int simulationTime) {
        this.operationType = operationType;
        this.simulationTime = simulationTime;
    }

    public FolderRequest(int operationType, int simulationTime, int numberOfParameter, int[] parameterList) {
        this.operationType = operationType;
        this.simulationTime = simulationTime;
        this.numberOfParameter = numberOfParameter;
        this.parameterList = parameterList;
    }

    public FolderRequest(int operationType, int simulationTime, int messageLength, String message) {
        this.operationType = operationType;
        this.simulationTime = simulationTime;
        this.messageLength = messageLength;
        this.message = message;
    }

    public int getOperationType() {
        return operationType;
    }

    public int[] getParameterList() {
        return parameterList;
    }

    public int getNumberOfParameter() {
        return numberOfParameter;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public String getMessage() {
        return message;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public void setNumberOfParameter(int numberOfParameter) {
        this.numberOfParameter = numberOfParameter;
    }

    public void setParameterList(int[] parameterList) {
        this.parameterList = parameterList;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
