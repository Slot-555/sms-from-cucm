package ccm_jtapi;

import com.cisco.jtapi.extensions.CiscoCallEv;
import com.cisco.jtapi.extensions.CiscoFeatureReason;
import javax.telephony.TerminalObserver;
import javax.telephony.events.TermEv;


public class MyTerminalObserver implements TerminalObserver {

    @Override
    public void terminalChangedEvent(TermEv[] termevs) {
        for (TermEv evlist1 : termevs){
            System.out.println("EventID: " + ((CiscoCallEv)evlist1).getID());
            System.out.println( ((CiscoCallEv)evlist1).getCiscoFeatureReason() );
        }
    }

}
