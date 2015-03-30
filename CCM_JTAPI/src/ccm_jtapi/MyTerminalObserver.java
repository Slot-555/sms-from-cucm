package ccm_jtapi;

import com.cisco.jtapi.extensions.CiscoFeatureReason;
import javax.telephony.TerminalObserver;
import javax.telephony.events.TermEv;


public class MyTerminalObserver implements TerminalObserver, CiscoFeatureReason {

    @Override
    public void terminalChangedEvent(TermEv[] termevs) {
        for (TermEv evlist1 : termevs){
            System.out.println(evlist1.getID());
        }
    }

}
