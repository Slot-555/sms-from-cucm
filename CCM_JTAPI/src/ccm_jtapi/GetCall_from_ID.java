package ccm_jtapi;

import com.cisco.jtapi.extensions.CiscoCall;
import com.cisco.jtapi.extensions.CiscoJtapiPeer;
import com.cisco.jtapi.extensions.CiscoProvider;
import javax.telephony.Provider;
import javax.telephony.JtapiPeer;
import javax.telephony.JtapiPeerFactory;

public class GetCall_from_ID{

public static final void main(String args[]) {

try {
CiscoJtapiPeer peer = (CiscoJtapiPeer) JtapiPeerFactory.getJtapiPeer(null);
CiscoProvider prov = (CiscoProvider) peer.getProvider("10.0.3.3;login=recording;passwd=recording");
CiscoCall call;
//call = CiscoProv.getCall(Integer.parseInt(args[1]));
//call = CiscoProv.getCall(Integer.parseInt("18695959"));
//System.out.println(prov.getCalls() );
call = prov.getCall(18703993);
//call = CiscoProv
if (call != null) {
System.out.println( "Called: " + call.getCalledAddress());
System.out.println( "Calling: " + call.getCallingAddress());
}
else {System.out.println("Call is null ");}
} catch (Exception excp) { System.out.println("Can't get Provider: " + excp.toString()); }

}
}