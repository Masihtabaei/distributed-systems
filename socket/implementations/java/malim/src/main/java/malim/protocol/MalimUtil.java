package malim.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import malim.protocol.MalimProtos.MessageType;
import malim.protocol.MalimProtos.OperationType;


public class MalimUtil {
    public static MalimProtos.MalimMessage createRequestForECHO(
        int simulationTime,
        String messageToECHO) {

        var requestMessageForECHO = MalimProtos.MalimMessage.newBuilder();
        requestMessageForECHO.setMessageType(MessageType.REQUEST);
        requestMessageForECHO.setOperationType(OperationType.ECHO);

        var requestParametersForECHO = MalimProtos.EchoRequestParameters.newBuilder();
        requestParametersForECHO.setSimulationTime(1000);
        requestParametersForECHO.setMessage(messageToECHO);
        
        var requestParameters = MalimProtos.RequestParameters.newBuilder();
        requestParameters.setEchoRequestParameters(requestParametersForECHO);

        return requestMessageForECHO.build();
    }

    public static MalimProtos.MalimMessage createRequestMessageForSUM(
        int simulationTime,
        ArrayList<Integer> valuesToSUM) {
        var requestMessageForSUM = MalimProtos.MalimMessage.newBuilder();
        requestMessageForSUM.setMessageType(MessageType.REQUEST);
        requestMessageForSUM.setOperationType(OperationType.SUM);
        
        var requestParametersForSUM = MalimProtos.SumRequestParameters.newBuilder();
        requestParametersForSUM.setSimulationTime(simulationTime);
        requestParametersForSUM.addAllValues(valuesToSUM);

        var requestParameters = MalimProtos.RequestParameters.newBuilder();
        requestParameters.setSumRequestParameters(requestParametersForSUM);

        requestMessageForSUM.setRequestParameters(requestParameters);

        return requestMessageForSUM.build();
    }

    public static MalimProtos.MalimMessage createRequestMessageForCOUNT(
        int simulationTime,
        ArrayList<Integer> valuesToCount) {
        var requestMessageForCOUNT = MalimProtos.MalimMessage.newBuilder();
        requestMessageForCOUNT.setMessageType(MessageType.REQUEST);
        requestMessageForCOUNT.setOperationType(OperationType.COUNT);
        
        var requestParametersForCOUNT = MalimProtos.CountRequestParameters.newBuilder();
        requestParametersForCOUNT.setSimulationTime(simulationTime);
        requestParametersForCOUNT.addAllValues(valuesToCount);

        var requestParameters = MalimProtos.RequestParameters.newBuilder();
        requestParameters.setCountRequestParameters(requestParametersForCOUNT);

        requestMessageForCOUNT.setRequestParameters(requestParameters);

        return requestMessageForCOUNT.build();
    }

    public static MalimProtos.MalimMessage createRequestMessageForDisconnect(
        int simulationTime,
        String messageForDISCONNECT
    ){
        var reuestMessageForDISCONNECT = MalimProtos.MalimMessage.newBuilder();
        reuestMessageForDISCONNECT.setMessageType(MessageType.REQUEST);
        reuestMessageForDISCONNECT.setOperationType(OperationType.DISCONNECT);

        var requestParametersForDISCONNECT = MalimProtos.DisconnectRequestParameters.newBuilder();
        requestParametersForDISCONNECT.setSimulationTime(simulationTime);
        requestParametersForDISCONNECT.setMessage(messageForDISCONNECT);

        var requestParameters = MalimProtos.RequestParameters.newBuilder();
        requestParameters.setDisconnectRequestParameters(requestParametersForDISCONNECT);


        reuestMessageForDISCONNECT.setRequestParameters(requestParameters);

        return reuestMessageForDISCONNECT.build();
    }

    public static MalimProtos.MalimMessage createRequestMessageForQUIT(
        int simulationTime,
        String messageForQUIT
    ){
        var requestMessageForQUIT = MalimProtos.MalimMessage.newBuilder();
        requestMessageForQUIT.setMessageType(MessageType.REQUEST);
        requestMessageForQUIT.setOperationType(OperationType.QUIT);

        var requestParametersForQUIT = MalimProtos.QuitRequestParameters.newBuilder();
        requestParametersForQUIT.setSimulationTime(simulationTime);
        requestParametersForQUIT.setMessage(messageForQUIT);

        var requestParameters = MalimProtos.RequestParameters.newBuilder();
        requestParameters.setQuitRequestParameters(requestParametersForQUIT);

        requestMessageForQUIT.setRequestParameters(requestParameters);

        return requestMessageForQUIT.build();
    }

    public static MalimProtos.MalimMessage createErrorMessage(
        int errorCode,
        OperationType causingOperationType,
        String messgeForERROR
    ){
        var errorMessage = MalimProtos.MalimMessage.newBuilder();
        errorMessage.setMessageType(MessageType.ERROR);
        errorMessage.setOperationType(causingOperationType);

        var parametersForERROR = MalimProtos.ErrorParameters.newBuilder();
        parametersForERROR.setErrorCode(errorCode);
        parametersForERROR.setMessage(messgeForERROR);

        errorMessage.setErrorParameters(parametersForERROR);

        return errorMessage.build();
    }

    public static MalimProtos.MalimMessage createResponseMessageForECHO(
        String resultOfECHORequest
    ){
        var responseMessageForECHO = MalimProtos.MalimMessage.newBuilder();
        responseMessageForECHO.setMessageType(MessageType.RESPONSE);
        responseMessageForECHO.setOperationType(OperationType.ECHO);

        var responseParametersForECHO = MalimProtos.EchoResponseParameters.newBuilder();
        responseParametersForECHO.setMessage(resultOfECHORequest);
        
        var responseParameters = MalimProtos.ResponseParameters.newBuilder();
        responseParameters.setEchoResponseParameters(responseParametersForECHO);

        responseMessageForECHO.setResponseParameters(responseParameters);
        
        return responseMessageForECHO.build();
    }

    public static MalimProtos.MalimMessage createResponseMessageForSUM(
        int resultOfSUMRequest
    ){
        var responseMessageForSUM = MalimProtos.MalimMessage.newBuilder();
        responseMessageForSUM.setMessageType(MessageType.RESPONSE);
        responseMessageForSUM.setOperationType(OperationType.SUM);

        var responseParametersForSUM = MalimProtos.SumResponseParameters.newBuilder();
        responseParametersForSUM.setResult(resultOfSUMRequest);

        var responseParameters = MalimProtos.ResponseParameters.newBuilder();
        responseParameters.setSumResponseParameters(responseParametersForSUM);

        responseMessageForSUM.setResponseParameters(responseParameters);

        return responseMessageForSUM.build();
    }

    public static MalimProtos.MalimMessage createResponseMessageForCOUNT(
        int resultOfCOUNTRequest
    ){
        var responseMessageForCOUNT = MalimProtos.MalimMessage.newBuilder();
        responseMessageForCOUNT.setMessageType(MessageType.RESPONSE);
        responseMessageForCOUNT.setOperationType(OperationType.COUNT);

        var responseParametersForCOUNT = MalimProtos.CountResponseParameters.newBuilder();
        responseParametersForCOUNT.setResult(resultOfCOUNTRequest);

        var responseParameters = MalimProtos.ResponseParameters.newBuilder();
        responseParameters.setCountResponseParameters(responseParametersForCOUNT);

        responseMessageForCOUNT.setResponseParameters(responseParameters);

        return responseMessageForCOUNT.build();        
    }

    public static MalimProtos.MalimMessage createResponseMessageForDISCONNECT(
        String resultOfDISCONNECTRequest
        ){
            var responseMessageForDISCONNECT = MalimProtos.MalimMessage.newBuilder();
            responseMessageForDISCONNECT.setMessageType(MessageType.RESPONSE);
            responseMessageForDISCONNECT.setOperationType(OperationType.DISCONNECT);

            var responseParametersForDISCONNECT = MalimProtos.DisconnectResponseParameters.newBuilder();
            responseParametersForDISCONNECT.setMessage(resultOfDISCONNECTRequest);

            var responseParameters = MalimProtos.ResponseParameters.newBuilder();
            responseParameters.setDisconnectResponseParameters(responseParametersForDISCONNECT);

            responseMessageForDISCONNECT.setResponseParameters(responseParameters);

            return responseMessageForDISCONNECT.build();
            
        }

    public static MalimProtos.MalimMessage createResponseMessageForQUIT(
        String resultOfQUITRequest
    ){
        var responseMessageForQUIT = MalimProtos.MalimMessage.newBuilder();
        responseMessageForQUIT.setMessageType(MessageType.RESPONSE);
        responseMessageForQUIT.setOperationType(OperationType.QUIT);

        var responseParametersForQUIT = MalimProtos.QuitResponseParameters.newBuilder();
        responseParametersForQUIT.setMessage(resultOfQUITRequest);

        var responseParameters = MalimProtos.ResponseParameters.newBuilder();
        responseParameters.setQuitResponseParameters(responseParametersForQUIT);

        return responseMessageForQUIT.build();
    }

    public static MalimMessageType parseMalimMessageType(MalimProtos.MalimMessage malimMessageToParse){
        MalimMessageType parsedMessageType;

        switch (malimMessageToParse.getMessageType()) {
            case REQUEST -> parsedMessageType = MalimMessageType.REQUEST;
            case RESPONSE -> parsedMessageType = MalimMessageType.RESPONSE;
            case ERROR -> parsedMessageType = MalimMessageType.ERROR;
            default -> {
                parsedMessageType = MalimMessageType.MESSAGE_TYPE_UNSPECIFIED;
            }
        }

        return parsedMessageType;
    }

    public static MalimMessageOperationType parseMalimMessageOperationType(MalimProtos.MalimMessage malimMessageToParse){
        
        MalimMessageOperationType parsedMessageOperationType;

        switch (malimMessageToParse.getOperationType()) {
            case ECHO -> parsedMessageOperationType = MalimMessageOperationType.ECHO;
            case SUM -> parsedMessageOperationType = MalimMessageOperationType.SUM;
            case COUNT -> parsedMessageOperationType = MalimMessageOperationType.COUNT;
            case DISCONNECT -> parsedMessageOperationType = MalimMessageOperationType.DISCONNECT;
            case QUIT -> parsedMessageOperationType = MalimMessageOperationType.QUIT;
            default -> {
                parsedMessageOperationType = MalimMessageOperationType.OPERATION_TYPE_UNSPECIFIED;
            }
        }

        return parsedMessageOperationType;
    }

    public static MalimECHOResponseMessage parseECHOResponseMessage(MalimProtos.MalimMessage malimMessageToParse){
        String messageParsed = malimMessageToParse.getResponseParameters().getEchoResponseParameters().getMessage();
        return new MalimECHOResponseMessage(messageParsed);
    }
    
    public static MalimSUMResponseMesssage parseSUMResponseMessage(MalimProtos.MalimMessage malimMessageToParse){
        int resultParsed = malimMessageToParse.getResponseParameters().getSumResponseParameters().getResult();
        return new MalimSUMResponseMesssage(resultParsed);
    }

    public static MalimCOUNTResponseMessage parseCOUNTResponseMessage(MalimProtos.MalimMessage malimMessageToParse){
        int resultParsed = malimMessageToParse.getResponseParameters().getCountResponseParameters().getResult();
        return new MalimCOUNTResponseMessage(resultParsed);
    }

    public static MalimDISCONNECTResponseMessage parseDISCONNECTResponseMessage(MalimProtos.MalimMessage malimMessageToParse){
        String messageParsed = malimMessageToParse.getResponseParameters().getDisconnectResponseParameters().getMessage();
        return new MalimDISCONNECTResponseMessage(messageParsed);
    }

    public static MalimQUITResponseMessage parseQUITResponseMessage(MalimProtos.MalimMessage malimMessageToParse){
        String messageParsed = malimMessageToParse.getResponseParameters().getQuitResponseParameters().getMessage();
        return new MalimQUITResponseMessage(messageParsed);
    }

    public static MalimERRORMessage parseErrorMessage(MalimProtos.MalimMessage malimMessageToParse){
        int errorCodeParsed = malimMessageToParse.getErrorParameters().getErrorCode();
        String errorMessage = malimMessageToParse.getErrorParameters().getMessage();
        return new MalimERRORMessage(errorCodeParsed, errorMessage);
    }

    public static void writeMalimMessageToSocket(
        MalimProtos.MalimMessage malimMessageToWrite,
        OutputStream socketOutputStream) throws IOException{
        
        malimMessageToWrite.writeDelimitedTo(socketOutputStream);
    }

    public static MalimProtos.MalimMessage readMalimMessageFromSocket(
        InputStream socketInputStream
    ) throws IOException{
        return MalimProtos.MalimMessage.parseDelimitedFrom(socketInputStream);
    }
}
