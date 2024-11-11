package malim.protocol;

import java.util.ArrayList;

public record MalimSUMRequestMesssage(int simulationTime, ArrayList<Integer> values) {}
