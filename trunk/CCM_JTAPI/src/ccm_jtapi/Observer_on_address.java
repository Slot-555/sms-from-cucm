package ccm_jtapi;

import com.cisco.cti.util.Condition;
import com.cisco.jtapi.extensions.CiscoAddress;
import com.cisco.jtapi.extensions.CiscoJtapiPeer;
import com.cisco.jtapi.extensions.CiscoProvider;
import javax.telephony.JtapiPeer;
import javax.telephony.JtapiPeerFactory;
import javax.telephony.ProviderObserver;
import javax.telephony.events.ProvEv;
import javax.telephony.events.ProvInServiceEv;

public class Observer_on_address{
        Condition inService = new Condition();
    public Observer_on_address () throws Exception {
        JtapiPeer peer = (CiscoJtapiPeer) JtapiPeerFactory.getJtapiPeer(null);
        CiscoProvider provider = (CiscoProvider) peer.getProvider("10.0.3.3;login=recording;passwd=recording");
                provider.addObserver(new ProviderObserver() {
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
        CiscoAddress srcAddr = (CiscoAddress) provider.getAddress("5215");
        srcAddr.addCallObserver(new MyCallCtlInCallObserver());
        System.out.println("--- Adding observer complit");
    }
    
public static final void main(String args[])throws Exception {
    new Observer_on_address ();
}
}