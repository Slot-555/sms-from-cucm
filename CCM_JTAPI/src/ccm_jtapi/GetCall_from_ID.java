package ccm_jtapi;

import com.cisco.cti.util.Condition;
import com.cisco.jtapi.extensions.CiscoAddress;
import com.cisco.jtapi.extensions.CiscoCall;
import com.cisco.jtapi.extensions.CiscoJtapiPeer;
import com.cisco.jtapi.extensions.CiscoProvider;
import com.cisco.jtapi.extensions.CiscoProviderObserver;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.telephony.InvalidArgumentException;
import javax.telephony.JtapiPeerFactory;
import javax.telephony.JtapiPeerUnavailableException;
import javax.telephony.MethodNotSupportedException;
import javax.telephony.ProviderObserver;
import javax.telephony.ResourceUnavailableException;
import javax.telephony.events.ProvEv;
import javax.telephony.events.ProvInServiceEv;

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
        System.out.println("Before wait " + Arrays.toString(prov.getCalls()) );
        inService.waitTrue();
        System.out.println("After wait " + Arrays.toString(prov.getCalls()) );
        
//        CiscoCall call = prov.getCall(18811012);
//        if (call != null) {
//            System.out.println( "Called: " + call.getCalledAddress());
//            System.out.println( "Calling: " + call.getCallingAddress());
//        }
//        else {System.out.println("Call is null ");}
        
    }
    
    
public static final void main(String args[]) throws Exception {
            new GetCall_from_ID();
}
}