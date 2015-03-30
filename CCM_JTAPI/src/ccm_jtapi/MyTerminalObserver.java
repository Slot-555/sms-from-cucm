package ccm_jtapi;

import javax.telephony.TerminalObserver;
import javax.telephony.events.TermEv;


public class MyTerminalObserver implements TerminalObserver {

    @Override
    public void terminalChangedEvent(TermEv[] termevs) {
        for (TermEv evlist1 : termevs){
            System.out.println(evlist1.getID());
        }
    }

}
