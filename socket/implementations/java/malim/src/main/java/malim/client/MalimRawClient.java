package malim.client;

import malim.protocol.FolderRequest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class MalimRawClient {

    private static final String progName = "MalimRawClient";
    //private static final int BUF_LEN = 128;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Bitte Server-IP und Port angeben.");
            return;
        }
        MalimRawClient client = new MalimRawClient();
        client.runInstance(args);
    }

    private void runInstance(String[] args) {
        if(args.length < 2) {return;}

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println("Contacting Server=" + host + " at Port=" + port);




        Scanner sc = new Scanner(System.in);

        int operationType ;
        int simulationTime;

        // Request Message
        FolderRequest fRequest = null;


        try(Socket socket = new Socket(host, port);
            DataInputStream dataInput = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("TCPMalimClient gestartet");
            System.out.println("MalimClient.runInstance host: " + host + " port: " + port);

            while (true) {
                System.out.println("Text eingeben ('quit' für Beenden des Clients und 'w' für weiter):");
                String s = sc.nextLine();
                if (s.equalsIgnoreCase("quit")) {
                    System.out.println("Client wird beendet.");
                    break;
                }
                //ich würde Quit anders nennen
                System.out.println("Fktnr (0 echo, 1 sum up numbers, 2 count positive numbers, 3 close-connection (Close), 4 server-shutdown (Shutdown) )");
                operationType = sc.nextInt();
                System.out.println("Bitte Simulationszeit in Sekunden eingeben, maximaler Wert 32000:");
                simulationTime = sc.nextInt();
                fRequest = new FolderRequest(operationType, simulationTime);

                switch (operationType) {
                    case 0:
                        buildOtherMessage(sc, fRequest);
                        sendOtherMessage(dataOutput, fRequest);
                        receiveOtherMessage(dataInput);
                        System.out.println(progName + " received ECHO message");
                        break;
                    case 1:
                        buildSumOrCountMessage(sc, fRequest);
                        sendSumOrCountMessage(dataOutput, fRequest);
                        receiveSumOrCountMessage(dataInput);


                        break;

                    case 2:
                        buildSumOrCountMessage(sc, fRequest);
                        sendSumOrCountMessage(dataOutput, fRequest);
                        receiveSumOrCountMessage(dataInput);
                        break;

                    case 3:
                        buildOtherMessage(sc, fRequest);
                        sendOtherMessage(dataOutput, fRequest);
                        receiveOtherMessage(dataInput);
                        System.out.println(progName + " diconnected");

                        break;

                    case 4:
                        buildOtherMessage(sc, fRequest);
                        sendOtherMessage(dataOutput, fRequest);
                        receiveOtherMessage(dataInput);
                        System.out.println(progName + ": quited");
                        break;
                    default:
                        System.out.println("Invalid operation type.");
                        return;
                }

            }
        }catch(IOException e){
                throw new RuntimeException(e);
            }
    }



    private void buildSumOrCountMessage(Scanner sc,FolderRequest fRequest) {
        System.out.println("Bitte Anzahl nachfolgender Parameter eingeben:");
        int numberOfParameters = sc.nextInt();
        int[] parameterList = new int[numberOfParameters];
        for (int i = 0; i < numberOfParameters; i++) {
            System.out.println("Bitte " + i + ".-ten Parameter eingeben:");
            parameterList[i] = sc.nextInt();
        }

        fRequest.setNumberOfParameter(numberOfParameters);
        fRequest.setParameterList(parameterList);



    }



    private void buildOtherMessage(Scanner sc, FolderRequest fRequest) {
        System.out.println("Bitte Text eingeben");
        String message = sc.nextLine();
        int messageLength = message.length();
        fRequest.setMessageLength(messageLength);
        fRequest.setMessage(message);
        
    }



    private void sendSumOrCountMessage(DataOutputStream dataOutput, FolderRequest fRequest) throws IOException{
        dataOutput.writeInt(fRequest.getOperationType());
        dataOutput.writeInt(fRequest.getSimulationTime());
        dataOutput.writeInt(fRequest.getNumberOfParameter());
        int [] nums = fRequest.getParameterList();
        for(int i:nums){
            dataOutput.writeInt(i);
        }
        dataOutput.flush();
        System.out.println("Sende Anfragenachricht...");
    }
    private void sendOtherMessage(DataOutputStream dataOutput, FolderRequest fRequest) throws IOException {
        dataOutput.writeInt(fRequest.getOperationType());
        dataOutput.writeInt(fRequest.getSimulationTime());
        //dataOutput.writeInt(fRequest.getMessageLength());
        byte[] message = fRequest.getMessage().getBytes(StandardCharsets.UTF_8.name()); //wiesoo .name() ??
        dataOutput.write(message.length);
        dataOutput.write(message, 0, message.length);
        System.out.println("Sende Anfragenachricht implizit (d.h. ohne flush()) mit Fktnr=" + fRequest.getOperationType() + "und Text=" + fRequest.getMessage());

    }

    private void receiveSumOrCountMessage(DataInputStream dataInput) throws IOException {
        int operationType = dataInput.readInt();
        Byte operationStatus = dataInput.readByte();
        int result = dataInput.readInt();
        System.out.println("Lesen der Antwortnachricht: Statusnummer=" + operationStatus );
        System.out.println("Lesen der Antwortnachricht: Result=" + result );

    }
    private void receiveOtherMessage(DataInputStream dataInput) throws IOException{
        int operationType = dataInput.readInt();
        Byte operationStatus = dataInput.readByte();
        int messageLength = dataInput.readInt();
        byte[] message = new byte[messageLength];
        for(int i=0;i<messageLength;i++){
            message[i] = dataInput.readByte();
        }
        String messageString = new String(message,0,messageLength, StandardCharsets.UTF_8.name());
        System.out.println("Lesen der Antwortnachricht: OperationStatus=" + operationStatus );
        System.out.println("Lesen der Antwortnachricht: MessageLength=" + messageString );


    }


}
