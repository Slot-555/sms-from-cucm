package ccm_jtapi;

import com.cisco.cti.util.Condition;
import com.cisco.jtapi.extensions.CiscoCall;
import com.cisco.jtapi.extensions.CiscoJtapiPeer;
import com.cisco.jtapi.extensions.CiscoProvider;
import java.util.Arrays;
import javax.telephony.CallObserver;
import javax.telephony.JtapiPeerFactory;
import javax.telephony.ProviderObserver;
import javax.telephony.Terminal;
import javax.telephony.TerminalObserver;
import javax.telephony.events.CallEv;
import javax.telephony.events.ProvEv;
import javax.telephony.events.ProvInServiceEv;
import javax.telephony.events.TermEv;

public class GetCall_from_ID {
        Condition inService = new Condition();

    public GetCall_from_ID () throws Exception {
        CiscoJtapiPeer peer = (CiscoJtapiPeer) JtapiPeerFactory.getJtapiPeer(null);
        CiscoProvider prov = (CiscoProvider) peer.getProvider("10.0.3.3;login=recording;passwd=recording");
        prov.addObserver(new ProviderObserver() {
			public void providerChangedEvent (ProvEv [] eventList) {
				if (eventList == null) return;
				for (int i = 0; i < eventList.length; ++i) {
					if (eventList[i] instanceof ProvInServiceEv) {
						inService.set();
					}
				}
			}
		});
        inService.waitTrue();
        Terminal[] ts = prov.getTerminals();
        Terminal term = prov.getTerminals()[4];
        System.out.println(term.getName());
                term.addCallObserver((CallObserver) new CallObserver(){
            @Override
            public void callChangedEvent(CallEv[] callevs) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        } );
                term.addObserver((TerminalObserver) new TerminalObserver() {
            @Override
            public void terminalChangedEvent(TermEv[] termevs) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        System.out.println("All calls: " + Arrays.toString(prov.getCalls()) );        
        CiscoCall call = prov.getCall(19193385);
        if (call != null) {
            System.out.println( "Called: " + call.getCalledAddress());
            System.out.println( "Calling: " + call.getCallingAddress());
        }
        else {System.out.println("Call is null ");}
        
    }
    
    
public static final void main(String args[]) throws Exception {
            new GetCall_from_ID();
}
}