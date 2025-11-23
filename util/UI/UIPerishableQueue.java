
package parceldeliveryproject.util.UI;
import parceldeliveryproject.util.*;
import parceldeliveryproject.model.Parcel;
import parceldeliveryproject.ds.*;

import java.util.Scanner;

public class UIPerishableQueue extends UIBase {
    
    public UIPerishableQueue(Platform Scope) {
        super(Scope);
        menuTitle = "Perishable Queue Menu";
        displayChoices = new String[] {
            "Create a new Priority Queue from the current parcel roster",
            "Enqueue for transport From Priority Queue"
        };
    }
    
    @Override
    public void selectedChoice(int choice) {
        Parcel[] parcels = Scope.parcels;
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

                if (delivered.InTransit) {
                    System.out.println("This package has already been readied for delivery; Refresh the current priority queue");
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
                    System.out.println("This package is neither in the parcel roster nor is being delivered... idk what to say lmao like... tihs is like so friggin rare yo");
                    break;
                }

                ParcelArrayHelper.deletePos(parcels, foundpos);

                System.out.println("Queued for transport: " + delivered);
                priorityQueue.display();
                break;
        }
    }
}