package malim.protocol;

public record MalimERRORMessage(MalimMessageOperationType causingOperationType, int code, String message) {}
