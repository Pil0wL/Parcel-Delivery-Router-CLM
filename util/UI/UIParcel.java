
package parceldeliveryproject.util.UI;
import parceldeliveryproject.util.Platform;
import parceldeliveryproject.model.Parcel;
import parceldeliveryproject.ds.*;

import java.util.Scanner;

public class UIParcel extends UIBase {
    
    public UIParcel(Platform Scope) {
        super(Scope);
        menuTitle = "Main Parcel Roster Menu";
        displayChoices = new String[] {
            "Enter Parcels",
            "Display All Parcels",
            "Sort Parcels By ...",
            "Enqueue All Parcels",
            "Dequeue From Parcel Pickup Queue"
            //"Insert Parcels Into Priority Queue",
            //"Deliver From Priority Queue"
        };
    }
    
    @Override
    public void selectedChoice(int choice) {
        Scanner console = Scope.console;
        Parcel[] parcels = Scope.parcels;
        Queue pickupQueue = Scope.pickupQueue;

        switch(choice) {
            case 1:
                if (parcels[parcels.length - 1] != null) { // assuming that there is no void inbetween items in the array
                    System.out.println("The current parcel roster if full!");
                    break;
                }

                System.out.println("\nEnter Details For this Parcel:");
                System.out.print("Name: ");
                String name = console.nextLine();

                System.out.print("ZIP Code: ");
                int zip = console.nextInt();

                System.out.print("Weight (kg): ");
                double weight = console.nextDouble();

                System.out.print("Is it Fragile/Perishable? (True/False): ");
                boolean fragperi = console.nextBoolean();

                console.nextLine();
                int foundEmptySlot = 0;
                for (int index = 0; index < parcels.length; index++) { // guaranteed to give something as per the checking
                    if (parcels[index] == null) {
                        foundEmptySlot = index;
                    }
                }
                parcels[foundEmptySlot] = new Parcel(name, zip, weight, fragperi);
                
                System.out.println("Parcel Successfully Recorded");
                break;

                
            case 2:
                System.out.println("\n=====List Of Parcels=====");
                for (Parcel p : parcels) System.out.println(p);
                break;

            case 3:
                if (parcels[0] == null) {
                    System.out.println("No Parcels Entered Yet");
                    break;
                }
                int sort_choice = console.nextInt();
                if (sort_choice < 1 || sort_choice > 3) {
                    System.out.println("Choice is out of range");
                    break;
                }
                switch(sort_choice) { 
                    case 1: // Name

                        Sorter.bubbleSortName(parcels);
                        System.out.println("\nParcels Sorted By Name (A-Z): ");
                        break;
                    case 2: // ZIP

                        Sorter.insertionSortZIP(parcels);
                        System.out.println("\nParcels Sorted By ZIP:");
                        break;
                    case 3: // Weight

                        Sorter.selectionSortWeight(parcels);
                        System.out.println("\nParcels Sorted By Weight:");
                        break;
                }
                for (Parcel p : parcels) System.out.println(p);

                break;
                
            case 4:
                if (parcels[0] == null) { 
                    System.out.println("Enter Parcels First");
                    break;
                }
                
                for (Parcel p : parcels) pickupQueue.enqueue(p);
                pickupQueue.display();
                break;
            
            case 5:
                Parcel served = pickupQueue.dequeue();
                if (served != null) System.out.println("Picked up: " + served);
                pickupQueue.display();
                break;
        }
    }
}