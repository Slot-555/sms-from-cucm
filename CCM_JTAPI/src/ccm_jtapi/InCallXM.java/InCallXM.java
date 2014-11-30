package ccm_jtapi;

import javax.telephony.*;
import javax.telephony.events.*;
import javax.telephony.callcontrol.*;
import javax.telephony.callcontrol.events.*;
import javax.telephony.*;
import javax.telephony.Provider;
import javax.telephony.JtapiPeer;
import javax.telephony.JtapiPeerFactory;

public class InCallXM{

public static final void main(String args[]) {

Provider provider = null;
try {
JtapiPeer peer = JtapiPeerFactory.getJtapiPeer(null);
provider = peer.getProvider("10.0.3.3;login=recording;passwd=recording");
} catch (Exception excp) {
System.out.println("Can't get Provider: " + excp.toString());
System.exit(0);}

try {
//Address srcAddr = provider.getAddress("5215");
//System.out.println("Addr: "+srcAddr.getName());
//        Terminal[] terminals = provider.getTerminals();
//        System.out.println("Avaialable Terminals : ");
//        for (int i = 0; i < terminals.length; i++) {
//            System.out.println("Terminal : " +terminals[i].getName());
//        }
Terminal terminal = provider.getTerminal("hecgjlvex1");
terminal.addCallObserver(new MyCallCtlInCallObserver());
System.out.println("--- Adding observer complit ---");
} catch (Exception excp) {
System.out.println("Can't get Terminal: " + excp.toString());
System.exit(0);}
}}



class MyCallCtlInCallObserver implements CallControlCallObserver {

@Override
public void callChangedEvent(CallEv[] evlist) {
for (int i = 0; i < evlist.length; i++) {

if (evlist[i] instanceof TermConnEv) {
TerminalConnection termconn = null;
String name = null;
try {
TermConnEv tcev = (TermConnEv)evlist[i];
Terminal term = termconn.getTerminal();
termconn = tcev.getTerminalConnection();
name = term.getName();
} catch (Exception excp) { }

String msg = "TerminalConnection to Terminal: " + name + " is ";

if (evlist[i].getID() == CallCtlTermConnTalkingEv.ID) {
System.out.println(msg + "TALKING");
}
else if (evlist[i].getID() == CallCtlTermConnRingingEv.ID) {
System.out.println(msg + "RINGING");

try {
final TerminalConnection _tc = termconn;
Runnable r = new Runnable() {
public void run() {
try{
_tc.answer();
} catch (Exception excp){ }
};
};
Thread T = new Thread(r);
T.start();
} catch (Exception excp) { }
}
else if (evlist[i].getID() == CallCtlTermConnDroppedEv.ID) { System.out.println(msg + "DROPPED - " + evlist[i].getID()); }
System.out.println(evlist[i].getID());
}
}
}
}