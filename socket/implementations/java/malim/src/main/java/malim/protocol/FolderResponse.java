package malim.protocol;

public class FolderResponse {

    private int operationType;
    private Byte operationStatus;
    private int result;
    private int messageLength;
    private String message;


    public FolderResponse() {
    }

    public FolderResponse(int operationType, Byte operationStatus, int result) {
        this.operationType = operationType;
        this.operationStatus = operationStatus;
        this.result = result;
    }

    public FolderResponse(int operationType, Byte operationStatus,String message, int messageLength) {
        this.operationType = operationType;
        this.message = message;
        this.messageLength = messageLength;
        this.operationStatus = operationStatus;
    }

    public int getOperationType() {
        return operationType;
    }

    public Byte getOperationStatus() {
        return operationStatus;
    }

    public int getResult() {
        return result;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public String getMessage() {
        return message;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public void setOperationStatus(Byte operationStatus) {
        this.operationStatus = operationStatus;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
