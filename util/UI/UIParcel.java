
package parceldeliveryproject.util.UI;
import java.lang.foreign.MemorySegment;
import parceldeliveryproject.util.Platform;
import java.util.Scanner;


public class UIParcel extends UIBase {
    
    public UIParcel(Platform Scope) {
        super(Scope);
        displayChoices = new String[] {
            "Enter Parcels",
            "Display All Parcels",
            "Sort Parcels By Name",
            "Sort Parcels By ZIP",
            "Sort Parcels By Weight",
            "Enqueue All Parcels",
            "Dequeue From Parcel Pickup Queue",
            "Insert Parcels Into Priority Queue",
            "Deliver From Priority Queue"
        };
    }
    
    @Override
    public void selectedChoice(int choice) {
        Scanner console = Scope.console;
        /*
        switch(choice) {
            case 1:
                System.out.print("Enter Number of Parcels: ");
                int num = console.nextInt();
                console.nextLine();
                
                for (int i = 0; i < num; i++) {
                    System.out.println("\nEnter Details For Parcel " + (i + 1) + ":");
                    System.out.print("Name: ");
                    String name = console.nextLine();
    
                    System.out.print("ZIP Code: ");
                    int zip = console.nextInt();
    
                    System.out.print("Weight (kg): ");
                    double weight = console.nextDouble();
    
                    System.out.print("Is it Fragile/Perishable? (True/False): ");
                    boolean fragperi = console.nextBoolean();
    
                    console.nextLine();
                    parcels[i] = new Parcel(name, zip, weight, fragperi);
                }
                System.out.println("Parcels Successfully Recorded");
                break;
                
            case 2:
                if (parcels == null) {
                    System.out.println("No Parcels Entered Yet");
                    break;
                }
                System.out.println("\n=====List Of Parcels=====");
                for (Parcel p : parcels) System.out.println(p);
                break;
                
            case 3:
                if (parcels == null) { 
                    System.out.println("Enter Parcels First");
                    break;
                }
                Sorter.bubbleSortName(parcels);
                System.out.println("\nParcels Sorted By Name (A-Z): ");
                for (Parcel p : parcels) System.out.println(p);
                break;
                
            case 4:
                if (parcels == null) { 
                    System.out.println("Enter Parcels First");
                    break;
                }
                Sorter.insertionSortZIP(parcels);
                System.out.println("\nParcels Sorted By ZIP:");
                for (Parcel p : parcels) System.out.println(p);
                break;
                
            case 5:
                if (parcels == null) { 
                    System.out.println("Enter Parcels First");
                    break;
                }
                Sorter.selectionSortWeight(parcels);
                System.out.println("\nParcels Sorted By Weight:");
                for (Parcel p : parcels) System.out.println(p);
                break;
                
            case 6:
                if (parcels == null) { 
                    System.out.println("Enter Parcels First");
                    break;
                }
                
                for (Parcel p : parcels) pickupQueue.enqueue(p);
                pickupQueue.display();
                break;
            
            case 7:
                if (pickupQueue == null) {
                    System.out.println("Queue Unavailable");
                    break;
                }
                Parcel served = pickupQueue.dequeue();
                if (served != null) System.out.println("Picked up: " + served);
                pickupQueue.display();
                break;
                
            case 8:
                if (parcels == null) { 
                    System.out.println("Enter Parcels First");
                    break;
                }
                priorityQueue = new PriorityQueue(parcels.length);
                for (Parcel p : parcels) priorityQueue.insert(p);
                priorityQueue.display();
                break;
            
            case 9:
                if (priorityQueue == null) {
                    System.out.println("Priority Queue Unavailable");
                    break;
                }
                Parcel delivered = priorityQueue.remove();
                if (delivered != null) System.out.println("Delivered: " + delivered);
                priorityQueue.display();
                break;
                
            case 10:
                System.out.println("Exiting Program...");
                running = false;
                
                break;
            default:
                System.out.println("Invalid Choice, Please Choose A Number On The Menu");
        }
        */
    }
}