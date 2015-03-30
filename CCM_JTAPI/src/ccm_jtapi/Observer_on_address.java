package ccm_jtapi;

import com.cisco.cti.util.Condition;
import com.cisco.jtapi.extensions.CiscoAddress;
import com.cisco.jtapi.extensions.CiscoConnection;
import com.cisco.jtapi.extensions.CiscoJtapiPeer;
import com.cisco.jtapi.extensions.CiscoProvider;
import javax.telephony.JtapiPeer;
import javax.telephony.JtapiPeerFactory;
import javax.telephony.ProviderObserver;
import javax.telephony.Terminal;
import javax.telephony.TerminalObserver;
import javax.telephony.events.ProvEv;
import javax.telephony.events.ProvInServiceEv;

public class Observer_on_address{
        Condition inService = new Condition();
    public Observer_on_address () throws Exception {
        JtapiPeer peer = (CiscoJtapiPeer) JtapiPeerFactory.getJtapiPeer(null);
        CiscoProvider provider = (CiscoProvider) peer.getProvider("10.0.3.3;login=recording;passwd=recording");
                provider.addObserver(new ProviderObserver() {
                        @Override
			public void providerChangedEvent (ProvEv [] eventList) {
				if (eventList == null) return;
				for (int i = 0; i < eventList.length; ++i) {
                                    //System.out.println(eventList[i].getCause());
					if (eventList[i] instanceof ProvInServiceEv) {
						inService.set();
					}
				}
			}
		});
        inService.waitTrue();
        System.out.println("In servise.");
        CiscoAddress srcAddr = (CiscoAddress) provider.getAddress("5215");
        
        Terminal[] terminals = srcAddr.getTerminals();
        terminals[0].addObserver( new MyTerminalObserver());
//        CiscoConnection c = (CiscoConnection) terminals[1].getTerminalConnections()[1].getConnection();
//        System.out.println("--- Adding observer complit" + c.getConnectionID());
        
        //srcAddr.addCallObserver(new MyCallCtlInCallObserver());
        System.out.println("--- Adding observer complit");
    }
    
public static final void main(String args[])throws Exception {
    new Observer_on_address ();
}
}