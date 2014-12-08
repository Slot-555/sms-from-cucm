package ccm_jtapi;

import com.cisco.jtapi.extensions.CiscoCall;
import com.cisco.jtapi.extensions.CiscoJtapiPeer;
import com.cisco.jtapi.extensions.CiscoProvider;
import javax.telephony.*;
import javax.telephony.Provider;
import javax.telephony.JtapiPeer;
import javax.telephony.JtapiPeerFactory;

public class InCallXM{

public static final void main(String args[]) {

Provider provider = null;
try {
//int ID = args[0];
JtapiPeer peer = (CiscoJtapiPeer) JtapiPeerFactory.getJtapiPeer(null);
provider = (CiscoProvider) peer.getProvider("10.0.3.3;login=recording;passwd=recording");
CiscoCall calls;

CiscoProvider provider2 = (CiscoProvider) provider;
//calls = provider2.getCall(Integer.parseInt(args[1]));
} catch (Exception excp) { System.out.println("Can't get Provider: " + excp.toString()); }

try {
Address srcAddr = provider.getAddress("5215");
srcAddr.addCallObserver(new MyCallCtlInCallObserver());

System.out.println("--- Adding observer complit");
} catch (Exception excp) { System.out.println("Can't get Terminal: " + excp.toString()); }
}
}