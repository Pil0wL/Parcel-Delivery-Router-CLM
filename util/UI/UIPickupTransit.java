
package parceldeliveryproject.util.UI;
import parceldeliveryproject.util.*;
import parceldeliveryproject.model.Parcel;
import parceldeliveryproject.ds.*;

public class UIPickupTransit extends UIBase {
    
    public UIPickupTransit(Platform Scope) {
        super(Scope);
        menuTitle = "Pickup/Transit Menu";
        displayChoices = new String[] {
            "Enqueue first Parcel",
            "Enqueue All Parcels"
        };

        
    }
    
    @Override
    public void selectedChoice(int choice) {
        Parcel[] parcels = Scope.parcels;
        Queue pickupQueue = Scope.pickupQueue;
        DoubleyLinkedList RouteDLL = Scope.RouteDLL;

        switch(choice) {
            case 1:
                if (parcels[0] == null) { 
                    System.out.println("Enter Parcels First");
                    break;
                }

                Parcel target = parcels[0];
                target.InTransit = true; // set its in-transit value to true
                DoubleyLinkedList.Node AssociatedNode = RouteDLL.addFirst(target.ZIP);
                target.AssociatedNode = AssociatedNode; // have an associated node/home for it to go to

                ParcelArrayHelper.deletePos(parcels, 0); // delete it in the main parcel roster

                pickupQueue.enqueue(target);

                pickupQueue.display();
                break;
            case 2:
                if (parcels[0] == null) { 
                    System.out.println("Enter Parcels First");
                    break;
                }
                
                for (Parcel p : parcels) {
                    pickupQueue.enqueue(p);
                    p.InTransit = true;
                    RouteDLL.addFirst(p.ZIP);
                }
                for (int index = 0; index < parcels.length; index++) { // delete everything
                    parcels[index] = null;
                }


                pickupQueue.display();
                break;
            
        }
    }
}