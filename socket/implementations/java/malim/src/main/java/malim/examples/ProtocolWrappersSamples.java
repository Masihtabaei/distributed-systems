package malim.examples;


import java.util.ArrayList;

import malim.protocol.MalimUtil;

public class ProtocolWrappersSamples {
    public static void main(String[] args) {
        var valuesTOSUM = new ArrayList<Integer>();
        valuesTOSUM.add(1);
        valuesTOSUM.add(2);
        var requestMessage = MalimUtil.createRequestMessageForSUM(1000, valuesTOSUM);
        System.out.println(requestMessage);
    }
}
