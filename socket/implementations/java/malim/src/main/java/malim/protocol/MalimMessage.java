package malim.protocol;

public abstract class MalimMessage {

    protected MalimProtos.MalimMessage messageWrapped;

    public MalimMessageType getType() {
        return MalimUtil.parseMalimMessageType(messageWrapped);
    }

    public MalimMessageOperationType getOperationType() {
        return MalimUtil.parseMalimMessageOperationType(messageWrapped);
    }
}
