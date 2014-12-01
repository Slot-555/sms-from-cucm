package ccm_jtapi;

import javax.telephony.callcontrol.CallControlCallObserver;
import javax.telephony.callcontrol.events.CallCtlConnFailedEv;
import javax.telephony.callcontrol.events.CallCtlEv;
import javax.telephony.callcontrol.events.CallCtlTermConnDroppedEv;
import javax.telephony.callcontrol.events.CallCtlTermConnRingingEv;
import javax.telephony.callcontrol.events.CallCtlTermConnTalkingEv;
import javax.telephony.events.CallEv;

class MyCallCtlInCallObserver implements CallControlCallObserver {

@Override
public void callChangedEvent(CallEv[] evlist) {
for (int i = 0; i < evlist.length; i++) {
    if (evlist[i].getID() == CallCtlEv.CAUSE_CALL_NOT_ANSWERED ) { System.out.println("Потомучта не отвечаешь а?"); }
    if (evlist[i].getID() == CallCtlTermConnTalkingEv.ID) { System.out.println("TALKING"); }
    if (evlist[i].getID() == CallCtlTermConnRingingEv.ID) { System.out.println("RINGING"); }
    if (evlist[i].getID() == CallCtlTermConnDroppedEv.ID) { System.out.println("DROPPED"); }
    //System.out.println(evlist[i].getID());
}
}
}
