package ccm_jtapi;

import javax.telephony.*;
import javax.telephony.events.*;
/* The MyOutCallObserver class implements the CallObserver interface and receives all events associated with the Call. */
public class MyOutCallObserver1 implements CallObserver {

   @Override
   public void callChangedEvent(CallEv[] evlist) {

for (int i = 0; i < evlist.length; i++) {

  if (evlist[i] instanceof ConnEv) {

    String name = null;
    try {
      Connection connection = ((ConnEv)evlist[i]).getConnection();
      Address addr = connection.getAddress();
      name = addr.getName();
    } catch (Exception excp) {
      // Handle Exceptions
    }
    String msg = "Connection to Address: " + name + " is ";

    if (evlist[i].getID() == ConnAlertingEv.ID) {
      System.out.println(msg + "ALERTING");
    }
    else if (evlist[i].getID() == ConnInProgressEv.ID) {
      System.out.println(msg + "INPROGRESS");
    }
    else if (evlist[i].getID() == ConnConnectedEv.ID) {
      System.out.println(msg + "CONNECTED");
    }
    else if (evlist[i].getID() == ConnDisconnectedEv.ID) {
      System.out.println(msg + "DISCONNECTED");
    }
  }  
}
  }
}