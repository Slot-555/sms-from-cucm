package ccm_jtapi;

import javax.telephony.*;
import javax.telephony.events.*;
import com.cisco.cti.util.Condition;
 
public class MakeCall
{
	public MakeCall() throws Exception
	{

		JtapiPeer peer = JtapiPeerFactory.getJtapiPeer(null);
 		Provider provider = peer.getProvider("10.0.3.3;login=recording;passwd=recording");
                
		/* wait for it to come into service */
		final Condition	inService = new Condition();
		provider.addObserver(new ProviderObserver() {
                        @Override
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

		Address srcAddr = provider.getAddress("5215");
		srcAddr.addCallObserver(new CallObserver() {
                        @Override
			public void callChangedEvent (CallEv [] eventList) {
                            System.out.println(eventList.toString());
			}
		});

		Call call = provider.createCall();
		call.connect(srcAddr.getTerminals()[0], srcAddr, "4403");
	}
}
