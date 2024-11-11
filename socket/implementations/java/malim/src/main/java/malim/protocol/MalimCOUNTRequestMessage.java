package malim.protocol;

import java.util.ArrayList;

public record MalimCOUNTRequestMessage(int simulationTime, ArrayList<Integer> values) {}
