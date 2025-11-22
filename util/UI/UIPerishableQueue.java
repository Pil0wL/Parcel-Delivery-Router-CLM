
package parceldeliveryproject.util.UI;
import parceldeliveryproject.util.Platform;
import parceldeliveryproject.model.Parcel;
import parceldeliveryproject.ds.*;

import java.util.Scanner;

public class UIPerishableQueue extends UIBase {
    
    public UIPerishableQueue(Platform Scope) {
        super(Scope);
        menuTitle = "Perishable Queue Menu";
        displayChoices = new String[] {
            "Create a new Priority Queue from the current parcel roster",
            "Deliver From Priority Queue"
        };
    }
    
    @Override
    public void selectedChoice(int choice) {
        Scanner console = Scope.console;
        Parcel[] parcels = Scope.parcels;
        Queue pickupQueue = Scope.pickupQueue;
        PriorityQueue priorityQueue = Scope.priorityQueue;

        switch(choice) {
            case 1:
                if (parcels[0] == null) { 
                    System.out.println("Enter Parcels First!");
                    break;
                }
                priorityQueue = new PriorityQueue(parcels.length);
                Scope.priorityQueue = priorityQueue;

                for (Parcel p : parcels) priorityQueue.insert(p);
                priorityQueue.display();
                break;
            
            case 2:
                if (priorityQueue == null) {
                    System.out.println("Priority Queue Unavailable; Create one first");
                    break;
                }
                Parcel delivered = priorityQueue.remove();
                if (delivered == null) {
                    System.out.println("Priority Queue is empty!");
                    break;
                }

                int foundpos = -1;
                for (int i = 0; i < parcels.length; i++) { // find in main roster
                    Parcel value = parcels[i];
                    if (value == null) {
                        break;
                    }
                    if (delivered == value) {
                        foundpos = i;
                        break;
                    }
                }
                if (foundpos == -1) {
                    System.out.println("This package has already been readied for delivery; Refresh the current priority queue");
                    break;
                }

                for (int i = foundpos; i < parcels.length; i++) { // delete in main roster
                    // this basically moves every value after the found position down
                    // therefore deleting the value
                    if (parcels[i] == null) { // this slot is empty; no need to move anymore
                        break;
                    }
                    Parcel value = parcels[i + 1];
                    parcels[i] = value;
                }


                System.out.println("Delivered: " + delivered);
                priorityQueue.display();
                break;
        }
    }
}