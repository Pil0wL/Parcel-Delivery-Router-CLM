
package parceldeliveryproject.util.UI;
import parceldeliveryproject.util.*;
import parceldeliveryproject.model.Parcel;
import parceldeliveryproject.ds.*;

import java.util.Scanner;

public class UIRouteOperator extends UIBase {
    
    public UIRouteOperator(Platform Scope) {
        super(Scope);
        menuTitle = "Route Operator Menu";
        displayChoices = new String[] {
            "Enqueue first Parcel",
            "Enqueue All Parcels",
            "Dequeue From Parcel Pickup Queue",
            "(Iterate Delivery Transport)"
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
                ParcelArrayHelper.deletePos(parcels, 0);

                pickupQueue.enqueue(target);

                pickupQueue.display();
                break;
            case 2:
                if (parcels[0] == null) { 
                    System.out.println("Enter Parcels First");
                    break;
                }
                
                for (Parcel p : parcels) pickupQueue.enqueue(p);
                for (int index = 0; index < parcels.length; index++) {
                    parcels[index] = null;
                }


                pickupQueue.display();
                break;
            
            case 3:


                break;

            case 4:
                Parcel served = pickupQueue.dequeue();
                if (served == null) break;
                RouteDLL.removeNode(served.AssociatedNode);

                System.out.println("Picked up: " + served);
                pickupQueue.display();
                break;
        }
    }
}