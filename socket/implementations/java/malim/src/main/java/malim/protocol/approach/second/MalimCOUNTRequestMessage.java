package malim.protocol.approach.second;

import java.util.ArrayList;

public record MalimCOUNTRequestMessage(int simulationTime, ArrayList<Integer> values) {}
