
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
            //"[DEBUG]: Insert test parcels"
        };
    }
    
    @Override
    public void selectedChoice(int choice) {
        Scanner console = Scope.console;
        Parcel[] parcels = Scope.parcels;
        Queue pickupQueue = Scope.pickupQueue;

        switch(choice) {
            case 1:
                if (parcels[parcels.length - 1] != null) { // assuming that there is no void inbetween items in the array; and that the items are side-by-side
                    System.out.println("The current parcel roster if full!");
                    break;
                }

                System.out.println("\nEnter Details For this Parcel:");
                System.out.print("Name: ");
                String name = console.next();

                System.out.print("ZIP Code (Preferably 1-9999, but can extend out of that): ");
                int zip = console.nextInt();

                System.out.print("Weight (kg): ");
                double weight = console.nextDouble();

                System.out.print("Is it Fragile/Perishable? (True/False) (Case Sensitive; defaults to False): ");
                boolean fragperi = (console.next().equals("True"));

                int foundEmptySlot = 0;
                for (int index = 0; index < parcels.length; index++) { // guaranteed to give something as per the checking
                    if (parcels[index] == null) {
                        foundEmptySlot = index;
                        break;
                    }
                }
                parcels[foundEmptySlot] = new Parcel(name, zip, weight, fragperi);
                Scope.ZIPIndex.insert(zip); // add it to the current index
                
                System.out.println("Parcel Successfully Recorded");
                System.out.println(parcels[foundEmptySlot]);
                break;

                
            case 2:
                System.out.println("\n=====List Of Parcels=====");
                for (Parcel p : parcels) {
                    if (p == null) break;
                    System.out.println(p);
                }
                break;

            case 3:
                if (parcels[0] == null) {
                    System.out.println("No Parcels Entered Yet");
                    break;
                }
                System.out.println((String) String.format(UIBase.choiceFormat, 1, "Name"));
                System.out.println((String) String.format(UIBase.choiceFormat, 2, "ZIP"));
                System.out.println((String) String.format(UIBase.choiceFormat, 3, "Weight"));
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
                for (Parcel p : parcels) {
                    if (p == null) break;
                    System.out.println(p);
                }

                break;
              
            case 4:
                if (parcels[0] != null) {
                    System.out.println("Requires that there was no parcels inserted beforehand");
                    break;
                }
                parcels[0] = new Parcel("Skibiwdi", 1200, 12.5, false);
                Scope.ZIPIndex.insert(1200);
                parcels[1] = new Parcel("not fragile fr", 1200, 12.5, true);
                Scope.ZIPIndex.insert(1200);
                parcels[2] = new Parcel("yesterday's regret", 1500, 400, false);
                Scope.ZIPIndex.insert(1500);
                parcels[3] = new Parcel("Iron Blocks", 800, 200, false);
                Scope.ZIPIndex.insert(800);
                
                System.out.println("added the parcels for debug");
                break;
        }
    }
}