
package parceldeliveryproject.util.UI;
import parceldeliveryproject.util.*;
import parceldeliveryproject.model.Parcel;
import parceldeliveryproject.ds.*;

public class UIPerishableQueue extends UIBase {
    
    public UIPerishableQueue(Platform Scope) {
        super(Scope);
        menuTitle = "Perishable Queue Menu";
        displayChoices = new String[] {
            "Display current Queue",
            "Create a new Priority Queue from the current parcel roster",
            "Enqueue for transport From Priority Queue"
        };
    }
    
    @Override
    public void selectedChoice(int choice) {
        Parcel[] parcels = Scope.parcels;
        PriorityQueue priorityQueue = Scope.priorityQueue;
        DoubleyLinkedList RouteDLL = Scope.RouteDLL;
        Queue pickupQueue = Scope.pickupQueue;

        switch(choice) {
            case 1:
                if (priorityQueue == null) { 
                    System.out.println("Create a priority queue first!");
                    break;
                }
                priorityQueue.display();
                break;
            case 2:
                if (parcels[0] == null) { 
                    System.out.println("Enter Parcels First!");
                    break;
                }
                priorityQueue = new PriorityQueue(parcels.length);
                Scope.priorityQueue = priorityQueue;

                for (Parcel p : parcels) {
                    if (p == null) break;
                    priorityQueue.insert(p);
                }
                priorityQueue.display();
                break;
            
            case 3:
                if (priorityQueue == null) {
                    System.out.println("Priority Queue Unavailable; Create one first");
                    break;
                }
                Parcel targetParcel = priorityQueue.remove();
                if (targetParcel == null) {
                    System.out.println("Priority Queue is empty!");
                    break;
                }

                if (targetParcel.InTransit) {
                    System.out.println("This package is already being delivered; Refresh the current priority queue");
                    break;
                }
                if (targetParcel.AssociatedNode != null) {
                    System.out.println("This package is already readied for delivery; Refresh the current priority queue");
                    break;
                }

                int foundpos = -1;
                for (int i = 0; i < parcels.length; i++) { // find in main roster
                    Parcel value = parcels[i];
                    if (value == null) {
                        break;
                    }
                    if (targetParcel == value) {
                        foundpos = i;
                        break;
                    }
                }
                if (foundpos == -1) {
                    System.out.println("This package is neither in the parcel roster nor is being delivered... idk what to say lmao like... tihs is like so friggin rare yo");
                    break;
                }

                ParcelArrayHelper.deletePos(parcels, foundpos);

                // man i should have a global func for this
                DoubleyLinkedList.Node AssociatedNode = RouteDLL.addLast(targetParcel.ZIP);
                targetParcel.AssociatedNode = AssociatedNode; // have an associated node/home for it to go to 

                pickupQueue.enqueue(targetParcel);

                System.out.println("Queued for transport: " + targetParcel);
                priorityQueue.display();
                break;

        }
    }
}