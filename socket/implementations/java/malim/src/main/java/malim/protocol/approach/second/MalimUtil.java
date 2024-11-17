package malim.protocol.approach.second;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import malim.protocol.approach.second.MalimProtos.MessageType;
import malim.protocol.approach.second.MalimProtos.OperationType;

public class MalimUtil {

    public static MalimProtos.MalimMessage createRequestMessageForECHO(
            MalimECHORequestMessage malimECHORequestMessage) {

        var requestMessageForECHO = MalimProtos.MalimMessage.newBuilder();
        requestMessageForECHO.setMessageType(MessageType.REQUEST);
        requestMessageForECHO.setOperationType(OperationType.ECHO);

        var requestParametersForECHO = MalimProtos.EchoRequestParameters.newBuilder();
        requestParametersForECHO.setSimulationTime(malimECHORequestMessage.simulationTime());
        requestParametersForECHO.setMessage(malimECHORequestMessage.messgae());

        var requestParameters = MalimProtos.RequestParameters.newBuilder();
        requestParameters.setEchoRequestParameters(requestParametersForECHO);

        return requestMessageForECHO.build();
    }

    public static MalimProtos.MalimMessage createRequestMessageForSUM(
            MalimSUMRequestMesssage malimSUMRequestMesssage) {
        var requestMessageForSUM = MalimProtos.MalimMessage.newBuilder();
        requestMessageForSUM.setMessageType(MessageType.REQUEST);
        requestMessageForSUM.setOperationType(OperationType.SUM);

        var requestParametersForSUM = MalimProtos.SumRequestParameters.newBuilder();
        requestParametersForSUM.setSimulationTime(malimSUMRequestMesssage.simulationTime());
        requestParametersForSUM.addAllValues(malimSUMRequestMesssage.values());

        var requestParameters = MalimProtos.RequestParameters.newBuilder();
        requestParameters.setSumRequestParameters(requestParametersForSUM);

        requestMessageForSUM.setRequestParameters(requestParameters);

        return requestMessageForSUM.build();
    }

    public static MalimProtos.MalimMessage createRequestMessageForCOUNT(
            MalimCOUNTRequestMessage malimCOUNTRequestMessage) {
        var requestMessageForCOUNT = MalimProtos.MalimMessage.newBuilder();
        requestMessageForCOUNT.setMessageType(MessageType.REQUEST);
        requestMessageForCOUNT.setOperationType(OperationType.COUNT);

        var requestParametersForCOUNT = MalimProtos.CountRequestParameters.newBuilder();
        requestParametersForCOUNT.setSimulationTime(malimCOUNTRequestMessage.simulationTime());
        requestParametersForCOUNT.addAllValues(malimCOUNTRequestMessage.values());

        var requestParameters = MalimProtos.RequestParameters.newBuilder();
        requestParameters.setCountRequestParameters(requestParametersForCOUNT);

        requestMessageForCOUNT.setRequestParameters(requestParameters);

        return requestMessageForCOUNT.build();
    }

    public static MalimProtos.MalimMessage createRequestMessageForDisconnect(
            MalimDISCONNECTRequestMessage malimDISCONNECTRequestMessage
    ) {
        var reuestMessageForDISCONNECT = MalimProtos.MalimMessage.newBuilder();
        reuestMessageForDISCONNECT.setMessageType(MessageType.REQUEST);
        reuestMessageForDISCONNECT.setOperationType(OperationType.DISCONNECT);

        var requestParametersForDISCONNECT = MalimProtos.DisconnectRequestParameters.newBuilder();
        requestParametersForDISCONNECT.setSimulationTime(malimDISCONNECTRequestMessage.simulationTime());
        requestParametersForDISCONNECT.setMessage(malimDISCONNECTRequestMessage.message());

        var requestParameters = MalimProtos.RequestParameters.newBuilder();
        requestParameters.setDisconnectRequestParameters(requestParametersForDISCONNECT);

        reuestMessageForDISCONNECT.setRequestParameters(requestParameters);

        return reuestMessageForDISCONNECT.build();
    }

    public static MalimProtos.MalimMessage createRequestMessageForQUIT(
            int simulationTime, String message
    ) {
        var requestMessageForQUIT = MalimProtos.MalimMessage.newBuilder();
        requestMessageForQUIT.setMessageType(MessageType.REQUEST);
        requestMessageForQUIT.setOperationType(OperationType.QUIT);

        var requestParametersForQUIT = MalimProtos.QuitRequestParameters.newBuilder();
        requestParametersForQUIT.setSimulationTime(simulationTime);
        requestParametersForQUIT.setMessage(message);

        var requestParameters = MalimProtos.RequestParameters.newBuilder();
        requestParameters.setQuitRequestParameters(requestParametersForQUIT);

        requestMessageForQUIT.setRequestParameters(requestParameters);

        return requestMessageForQUIT.build();
    }

    public static MalimProtos.MalimMessage createErrorMessage(
            MalimERRORMessage malimERRORMessage
    ) {
        var errorMessage = MalimProtos.MalimMessage.newBuilder();
        errorMessage.setMessageType(MessageType.ERROR);
        var translatedOperationType = createMessageOperationType(malimERRORMessage.causingOperationType());
        errorMessage.setOperationType(translatedOperationType);

        var parametersForERROR = MalimProtos.ErrorParameters.newBuilder();
        parametersForERROR.setErrorCode(malimERRORMessage.code());
        parametersForERROR.setMessage(malimERRORMessage.message());

        errorMessage.setErrorParameters(parametersForERROR);

        return errorMessage.build();
    }

    public static MalimProtos.MalimMessage createResponseMessageForECHO(
            MalimECHOResponseMessage malimECHOResponseMessage
    ) {
        var responseMessageForECHO = MalimProtos.MalimMessage.newBuilder();
        responseMessageForECHO.setMessageType(MessageType.RESPONSE);
        responseMessageForECHO.setOperationType(OperationType.ECHO);

        var responseParametersForECHO = MalimProtos.EchoResponseParameters.newBuilder();
        responseParametersForECHO.setMessage(malimECHOResponseMessage.message());

        var responseParameters = MalimProtos.ResponseParameters.newBuilder();
        responseParameters.setEchoResponseParameters(responseParametersForECHO);

        responseMessageForECHO.setResponseParameters(responseParameters);

        return responseMessageForECHO.build();
    }

    public static MalimProtos.MalimMessage createResponseMessageForSUM(
            MalimSUMResponseMesssage malimSUMResponseMesssage
    ) {
        var responseMessageForSUM = MalimProtos.MalimMessage.newBuilder();
        responseMessageForSUM.setMessageType(MessageType.RESPONSE);
        responseMessageForSUM.setOperationType(OperationType.SUM);

        var responseParametersForSUM = MalimProtos.SumResponseParameters.newBuilder();
        responseParametersForSUM.setResult(malimSUMResponseMesssage.result());

        var responseParameters = MalimProtos.ResponseParameters.newBuilder();
        responseParameters.setSumResponseParameters(responseParametersForSUM);

        responseMessageForSUM.setResponseParameters(responseParameters);

        return responseMessageForSUM.build();
    }

    public static MalimProtos.MalimMessage createResponseMessageForCOUNT(
            MalimCOUNTResponseMessage malimCOUNTResponseMessage
    ) {
        var responseMessageForCOUNT = MalimProtos.MalimMessage.newBuilder();
        responseMessageForCOUNT.setMessageType(MessageType.RESPONSE);
        responseMessageForCOUNT.setOperationType(OperationType.COUNT);

        var responseParametersForCOUNT = MalimProtos.CountResponseParameters.newBuilder();
        responseParametersForCOUNT.setResult(malimCOUNTResponseMessage.result());

        var responseParameters = MalimProtos.ResponseParameters.newBuilder();
        responseParameters.setCountResponseParameters(responseParametersForCOUNT);

        responseMessageForCOUNT.setResponseParameters(responseParameters);

        return responseMessageForCOUNT.build();
    }

    public static MalimProtos.MalimMessage createResponseMessageForDISCONNECT(
            MalimDISCONNECTResponseMessage malimDISCONNECTResponseMessage
    ) {
        var responseMessageForDISCONNECT = MalimProtos.MalimMessage.newBuilder();
        responseMessageForDISCONNECT.setMessageType(MessageType.RESPONSE);
        responseMessageForDISCONNECT.setOperationType(OperationType.DISCONNECT);

        var responseParametersForDISCONNECT = MalimProtos.DisconnectResponseParameters.newBuilder();
        responseParametersForDISCONNECT.setMessage(malimDISCONNECTResponseMessage.message());

        var responseParameters = MalimProtos.ResponseParameters.newBuilder();
        responseParameters.setDisconnectResponseParameters(responseParametersForDISCONNECT);

        responseMessageForDISCONNECT.setResponseParameters(responseParameters);

        return responseMessageForDISCONNECT.build();

    }

    public static MalimProtos.MalimMessage createResponseMessageForQUIT(
            MalimQUITResponseMessage malimQUITResponseMessage
    ) {
        var responseMessageForQUIT = MalimProtos.MalimMessage.newBuilder();
        responseMessageForQUIT.setMessageType(MessageType.RESPONSE);
        responseMessageForQUIT.setOperationType(OperationType.QUIT);

        var responseParametersForQUIT = MalimProtos.QuitResponseParameters.newBuilder();
        responseParametersForQUIT.setMessage(malimQUITResponseMessage.message());

        var responseParameters = MalimProtos.ResponseParameters.newBuilder();
        responseParameters.setQuitResponseParameters(responseParametersForQUIT);

        return responseMessageForQUIT.build();
    }

    public static OperationType createMessageOperationType(MalimMessageOperationType malimMessageOperationType) {
        OperationType translatedOperationType;
        switch (malimMessageOperationType) {
            case ECHO ->
                translatedOperationType = OperationType.ECHO;
            case SUM ->
                translatedOperationType = OperationType.SUM;
            case COUNT ->
                translatedOperationType = OperationType.COUNT;
            case DISCONNECT ->
                translatedOperationType = OperationType.DISCONNECT;
            case QUIT ->
                translatedOperationType = OperationType.QUIT;
            default -> {
                translatedOperationType = OperationType.OPERATION_TYPE_UNSPECIFIED;
            }
        }
        return translatedOperationType;
    }
   
    public static MalimMessageType parseMalimMessageType(MalimProtos.MalimMessage malimMessageToParse) {
        MalimMessageType parsedMessageType;

        switch (malimMessageToParse.getMessageType()) {
            case REQUEST ->
                parsedMessageType = MalimMessageType.REQUEST;
            case RESPONSE ->
                parsedMessageType = MalimMessageType.RESPONSE;
            case ERROR ->
                parsedMessageType = MalimMessageType.ERROR;
            default -> {
                parsedMessageType = MalimMessageType.MESSAGE_TYPE_UNSPECIFIED;
            }
        }

        return parsedMessageType;
    }
    
    public static MalimMessageOperationType parseMalimMessageOperationType(MalimProtos.MalimMessage malimMessageToParse) {

        MalimMessageOperationType parsedMessageOperationType;

        switch (malimMessageToParse.getOperationType()) {
            case ECHO ->
                parsedMessageOperationType = MalimMessageOperationType.ECHO;
            case SUM ->
                parsedMessageOperationType = MalimMessageOperationType.SUM;
            case COUNT ->
                parsedMessageOperationType = MalimMessageOperationType.COUNT;
            case DISCONNECT ->
                parsedMessageOperationType = MalimMessageOperationType.DISCONNECT;
            case QUIT ->
                parsedMessageOperationType = MalimMessageOperationType.QUIT;
            default -> {
                parsedMessageOperationType = MalimMessageOperationType.OPERATION_TYPE_UNSPECIFIED;
            }
        }

        return parsedMessageOperationType;
    }

    public static MalimECHORequestMessage parseMalimECHORequestMessage(MalimProtos.MalimMessage malimMessageToParse) {
        int simulationTimeParsed = malimMessageToParse.getRequestParameters().getEchoRequestParameters().getSimulationTime();
        String messageParsed = malimMessageToParse.getRequestParameters().getEchoRequestParameters().getMessage();
        return new MalimECHORequestMessage(simulationTimeParsed, messageParsed);
    }

    public static MalimSUMRequestMesssage parseMalimSUMRequestMesssage(MalimProtos.MalimMessage malimMessageToParse) {
        int simulationTimeParsed = malimMessageToParse.getRequestParameters().getSumRequestParameters().getSimulationTime();
        ArrayList<Integer> valuesParsed = new ArrayList<>(malimMessageToParse.getRequestParameters().getSumRequestParameters().getValuesList());

        return new MalimSUMRequestMesssage(simulationTimeParsed, valuesParsed);
    }

    public static MalimCOUNTRequestMessage parseMalimCOUNTRequestMessage(MalimProtos.MalimMessage malimMessageToParse) {
        int simulationTimeParsed = malimMessageToParse.getRequestParameters().getCountRequestParameters().getSimulationTime();
        ArrayList<Integer> valuesParsed = new ArrayList<>(malimMessageToParse.getRequestParameters().getCountRequestParameters().getValuesList());

        return new MalimCOUNTRequestMessage(simulationTimeParsed, valuesParsed);
    }

    public static MalimDISCONNECTRequestMessage parseMalimDISCONNECTRequestMessage(MalimProtos.MalimMessage malimMessageToParse) {
        int simulationTimeParsed = malimMessageToParse.getRequestParameters().getDisconnectRequestParameters().getSimulationTime();
        String messageParsed = malimMessageToParse.getRequestParameters().getDisconnectRequestParameters().getMessage();

        return new MalimDISCONNECTRequestMessage(simulationTimeParsed, messageParsed);
    }

    public static MalimQUITRequestMessage parseMalimQUITRequestMessage(MalimProtos.MalimMessage malimMessageToParse) {
        int simulationTimeParsed = malimMessageToParse.getRequestParameters().getQuitRequestParameters().getSimulationTime();
        String messageParsed = malimMessageToParse.getRequestParameters().getQuitRequestParameters().getMessage();

        return new MalimQUITRequestMessage(simulationTimeParsed, messageParsed);
    }

    public static MalimECHOResponseMessage parseECHOResponseMessage(MalimProtos.MalimMessage malimMessageToParse) {
        String messageParsed = malimMessageToParse.getResponseParameters().getEchoResponseParameters().getMessage();
        return new MalimECHOResponseMessage(messageParsed);
    }

    public static MalimSUMResponseMesssage parseSUMResponseMessage(MalimProtos.MalimMessage malimMessageToParse) {
        int resultParsed = malimMessageToParse.getResponseParameters().getSumResponseParameters().getResult();
        return new MalimSUMResponseMesssage(resultParsed);
    }

    public static MalimCOUNTResponseMessage parseCOUNTResponseMessage(MalimProtos.MalimMessage malimMessageToParse) {
        int resultParsed = malimMessageToParse.getResponseParameters().getCountResponseParameters().getResult();
        return new MalimCOUNTResponseMessage(resultParsed);
    }

    public static MalimDISCONNECTResponseMessage parseDISCONNECTResponseMessage(MalimProtos.MalimMessage malimMessageToParse) {
        String messageParsed = malimMessageToParse.getResponseParameters().getDisconnectResponseParameters().getMessage();
        return new MalimDISCONNECTResponseMessage(messageParsed);
    }

    public static MalimQUITResponseMessage parseQUITResponseMessage(MalimProtos.MalimMessage malimMessageToParse) {
        String messageParsed = malimMessageToParse.getResponseParameters().getQuitResponseParameters().getMessage();
        return new MalimQUITResponseMessage(messageParsed);
    }

    public static MalimERRORMessage parseErrorMessage(MalimProtos.MalimMessage malimMessageToParse) {
        MalimMessageOperationType causingOperationType = parseMalimMessageOperationType(malimMessageToParse);
        int errorCodeParsed = malimMessageToParse.getErrorParameters().getErrorCode();
        String errorMessage = malimMessageToParse.getErrorParameters().getMessage();
        return new MalimERRORMessage(causingOperationType, errorCodeParsed, errorMessage);
    }

    public static void writeMalimMessageToSocket(
            MalimProtos.MalimMessage malimMessageToWrite,
            OutputStream socketOutputStream) throws IOException {

        malimMessageToWrite.writeDelimitedTo(socketOutputStream);
    }

    public static MalimProtos.MalimMessage readMalimMessageFromSocket(
            InputStream socketInputStream
    ) throws IOException {
        return MalimProtos.MalimMessage.parseDelimitedFrom(socketInputStream);
    }
}
