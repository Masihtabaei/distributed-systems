package malim.client.approach.second;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import malim.protocol.approach.second.MalimCOUNTRequestMessage;
import malim.protocol.approach.second.MalimDISCONNECTRequestMessage;
import malim.protocol.approach.second.MalimProtos;
import malim.protocol.approach.second.MalimQUITRequestMessage;
import malim.protocol.approach.second.MalimSUMRequestMesssage;
import malim.protocol.approach.second.MalimUtil;

public class MalimClient {

    private static String proname = "TcpMalimClient";
    // private static final int BUF_LEN = 128

/*     public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Bitte Server-IP und Port angeben.");
            return;
        }
        MalimClient client = new MalimClient();
        client.runInstance(args);
    } */

    private void runInstance(String[] args) {
        if(args.length == 0) {return;}
        String host = args[0];//Zielhost
        int port = Integer.parseInt(args[1]);//Zielport




        Scanner sc = new Scanner(System.in);
        int fktnr;
        int simulationTime;
        int anzahlParameter;
        //ArrayList<Integer> nums = new ArrayList<Integer>();




            System.out.println("Contacting Server=" + host + " at Port=" + port);
            try(Socket socket = new Socket(host, port);
                DataInputStream datainput = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
            ) {
                System.out.println("TCPMalimClient gestartet");
                System.out.println("MalimClient.runInstance host: " + host + " port: " + port);

                while (true) {
                    System.out.println("Text eingeben ('quit' für Beenden des Clients):");
                    String s = sc.nextLine();
                    if (s.equalsIgnoreCase("quit")) {
                        System.out.println("Client wird beendet.");
                        break;
                    }

                    System.out.println("Fktnr (1 sum up numbers, 2 count positive numbers, 5 close-connection (Close), 7 server-shutdown (Shutdown) )");
                    fktnr = sc.nextInt();
                    // 5 und 7 muss ich hier noch abdecken
                    System.out.println("Bitte Simulationszeit in Sekunden eingeben, maximaler Wert 32000:");
                    simulationTime = sc.nextInt();
                    String text;





                    MalimProtos.MalimMessage requestMessage = null;
                    ArrayList<Integer> nums = new ArrayList<Integer>();
                    switch (fktnr) {
                        case 1:

                            System.out.println("Bitte Anzahl nachfolgender Parameter eingeben:");
                            anzahlParameter = sc.nextInt();

                            for (int i = 0; i < anzahlParameter; i++) {
                                System.out.println("Bitte " + i + ".-ten Parameter eingeben:");
                                nums.add(sc.nextInt());
                            }

                            MalimSUMRequestMesssage sumRequestMesssage = new MalimSUMRequestMesssage(simulationTime, nums);
                            requestMessage = MalimUtil.createRequestMessageForSUM(sumRequestMesssage);
                            MalimUtil.writeMalimMessageToSocket(requestMessage, dataOutput);
                            System.out.println("MalimClient.runInstance: Sende Anfragenachricht ... ");

                            MalimProtos.MalimMessage sumResponseMessage = MalimUtil.readMalimMessageFromSocket(datainput);

                            //System.out.println("MalimClient.runInstance: Response " + sumResponseMessage);

                            var sumResponse = sumResponseMessage.getResponseParameters().getSumResponseParameters().getResult();
                            //sumResponseMessage.getResponseParameters().getSumResponseParametersOrBuilder().getResult();
                            System.out.println("MalimClient.runInstance: Antwort = " + sumResponse);
                            break;



                    //System.out.println("MalimClient.runInstance: Response " + responseMessage);



                        case 2:

                            System.out.println("Bitte Anzahl nachfolgender Parameter eingeben:");
                            anzahlParameter = sc.nextInt();

                            for (int i = 0; i < anzahlParameter; i++) {
                                System.out.println("Bitte " + i + ".-ten Parameter eingeben:");
                                nums.add(sc.nextInt());
                            }

                            MalimCOUNTRequestMessage countRequestMesssage = new MalimCOUNTRequestMessage(simulationTime, nums);
                            requestMessage = MalimUtil.createRequestMessageForCOUNT(countRequestMesssage);

                            MalimUtil.writeMalimMessageToSocket(requestMessage, dataOutput);
                            System.out.println("MalimClient.runInstance: Sende Anfragenachricht ... ");

                            MalimProtos.MalimMessage countResponseMessage = MalimUtil.readMalimMessageFromSocket(datainput);

                            //System.out.println("MalimClient.runInstance: Response " + sumResponseMessage);

                            var countResponse = countResponseMessage.getResponseParameters().getCountResponseParameters().getResult();
                            //responseMessage.getResponseParameters().getSumResponseParametersOrBuilder().getResult();
                            System.out.println("MalimClient.runInstance: Antwort = " + countResponse);

                            break;
                        case 5:
                            System.out.println("Text eingeben");
                            text = sc.nextLine();

                            MalimQUITRequestMessage quitRequestMessage = new MalimQUITRequestMessage(simulationTime,text);
                            //requestMessage = MalimUtil.createRequestMessageForQUIT(quitRequestMessage);

                            MalimUtil.writeMalimMessageToSocket(requestMessage,dataOutput);
                            System.out.println("Sende Anfragenachricht implizit (d.h. ohne flush()) mit Fktnr=" + fktnr + "und Text=" + text);

                            MalimProtos.MalimMessage quitResponseMessage = MalimUtil.readMalimMessageFromSocket(datainput);

                            var quitResponse = quitResponseMessage.getResponseParameters().getQuitResponseParameters().getMessage();
                            System.out.println("Quit Antwort = " + quitResponse);


                            break;

                        case 7:
                            System.out.println("Text eingeben");
                            text = sc.nextLine();

                            MalimDISCONNECTRequestMessage disconnectRequestMessage = new MalimDISCONNECTRequestMessage(simulationTime,text);
                            requestMessage = MalimUtil.createRequestMessageForDisconnect(disconnectRequestMessage);

                            MalimUtil.writeMalimMessageToSocket(requestMessage,dataOutput);
                            System.out.println("Sende Anfragenachricht implizit (d.h. ohne flush()) mit Fktnr=" + fktnr + "und Text=" + text);

                            MalimProtos.MalimMessage disconnectResponseMessage = MalimUtil.readMalimMessageFromSocket(datainput);

                            var disconnectResponse = disconnectResponseMessage.getResponseParameters().getDisconnectResponseParameters().getMessage();
                            System.out.println("Quit Antwort = " + disconnectResponse);
                            break;
                    }



                }
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }





