package ccm_jtapi;

import com.cisco.jtapi.extensions.CiscoAddress;
import com.cisco.jtapi.extensions.CiscoJtapiPeer;
import com.cisco.jtapi.extensions.CiscoProvider;
import javax.telephony.Provider;
import javax.telephony.JtapiPeer;
import javax.telephony.JtapiPeerFactory;

public class InCallXM{

public static final void main(String args[]) {

Provider provider = null;
try {
JtapiPeer peer = (CiscoJtapiPeer) JtapiPeerFactory.getJtapiPeer(null);
provider = (CiscoProvider) peer.getProvider("10.0.3.3;login=recording;passwd=recording");
CiscoProvider provider2 = (CiscoProvider) provider;
CiscoAddress srcAddr = (CiscoAddress) provider2.getAddress("5215");
srcAddr.addCallObserver(new MyCallCtlInCallObserver());

} catch (Exception excp) { System.out.println("Can't get Provider: " + excp.toString()); }

try {
//CiscoAddress srcAddr = provider2.getAddress("5215");
//srcAddr.addCallObserver(new MyCallCtlInCallObserver());

System.out.println("--- Adding observer complit");
} catch (Exception excp) { System.out.println("Can't get Terminal: " + excp.toString()); }
}
}