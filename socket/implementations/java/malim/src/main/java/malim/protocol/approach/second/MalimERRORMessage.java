package malim.protocol.approach.second;

public record MalimERRORMessage(MalimMessageOperationType causingOperationType, int code, String message) {}
