package ccm_jtapi;

import com.cisco.jtapi.extensions.CiscoCall;
import com.cisco.jtapi.extensions.CiscoCallEv;
import com.cisco.jtapi.extensions.CiscoFeatureReason;
import javax.telephony.callcontrol.CallControlCallObserver;
import javax.telephony.callcontrol.events.CallCtlTermConnDroppedEv;
import javax.telephony.callcontrol.events.CallCtlTermConnRingingEv;
import javax.telephony.callcontrol.events.CallCtlTermConnTalkingEv;
import javax.telephony.events.CallEv;

class MyCallControlCallObserver implements CallControlCallObserver {

    
@Override
public void callChangedEvent(CallEv[] evlist) {
    for (CallEv evlist1 : evlist) {
//        if (evlist1.getID() == CallCtlTermConnTalkingEv.ID) {
//            System.out.println("TALKING CiscoFeatureReason: " + ((CiscoCallEv)evlist1).getCiscoFeatureReason() );
//            CiscoCall call = (CiscoCall) evlist1.getCall();
//            System.out.println( "Called:  " + call.getCalledAddress());
//            System.out.println( "Calling: " + call.getCallingAddress());
//            System.out.println( "Call ID: " + call.getCallID());
//            System.out.println( "Call ID int: " + call.getCallID().intValue());
//            System.out.println( "Call Global ID: " + call.getCallID().getGlobalCallID());
//            System.out.println( "Call Global Manager ID: " + call.getCallID().getCallManagerID());
//            System.out.println( "Call Global Manager ID: " );
//        }
//        
//        if (evlist1.getID() == CallCtlTermConnRingingEv.ID) {
//            System.out.println("RINGING CiscoFeatureReason: " + ((CiscoCallEv)evlist1).getCiscoFeatureReason() );
//        }
        
        if (evlist1.getID() == CallCtlTermConnDroppedEv.ID) {
            //System.out.println("DROPPED CiscoFeatureReason: " + ((CiscoCallEv)evlist1).getCiscoFeatureReason() );
            if (((CiscoCallEv)evlist1).getCiscoFeatureReason() == CiscoFeatureReason.REASON_FORWARDNOANSWER ){
                System.out.println("Пролюбился звонок");
                CiscoCall call = (CiscoCall) evlist1.getCall();
                System.out.println( "Called:  " + call.getCalledAddress());
                System.out.println( "Calling: " + call.getCallingAddress());
                System.out.println("-----------------");
            }
        }
    }
}
}
