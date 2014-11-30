package ccm_jtapi;

import javax.telephony.*;
import javax.telephony.Provider;
import javax.telephony.JtapiPeer;
import javax.telephony.JtapiPeerFactory;

public class InCallXM{

public static final void main(String args[]) {

Provider provider = null;
try {
JtapiPeer peer = JtapiPeerFactory.getJtapiPeer(null);
provider = peer.getProvider("10.0.3.3;login=recording;passwd=recording");
} catch (Exception excp) { System.out.println("Can't get Provider: " + excp.toString()); }

try {
Address srcAddr = provider.getAddress("5215");
srcAddr.addCallObserver(new MyCallCtlInCallObserver());
    
//Terminal terminal = provider.getTerminal("hecgjlvex1");
//terminal.addCallObserver(new MyCallCtlInCallObserver());
    
//provider.addObserver(new MyCallCtlInCallObserver());

System.out.println("--- Adding observer complit ---");
} catch (Exception excp) { System.out.println("Can't get Terminal: " + excp.toString()); }
}
}