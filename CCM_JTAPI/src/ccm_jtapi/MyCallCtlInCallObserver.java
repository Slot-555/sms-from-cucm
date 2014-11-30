package ccm_jtapi;

import javax.telephony.Terminal;
import javax.telephony.TerminalConnection;
import javax.telephony.callcontrol.CallControlCallObserver;
import javax.telephony.callcontrol.events.CallCtlTermConnDroppedEv;
import javax.telephony.callcontrol.events.CallCtlTermConnRingingEv;
import javax.telephony.callcontrol.events.CallCtlTermConnTalkingEv;
import javax.telephony.events.CallEv;
import javax.telephony.events.TermConnEv;

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