

package parceldeliveryproject.app;

import parceldeliveryproject.ds.*;
import parceldeliveryproject.model.Parcel;

import java.util.Scanner;

public class ParcelDeliveryRouterApp {
    public static void main(String[] args) {
        
        Scanner console = new Scanner(System.in);
        
        Parcel[] parcels = null;
        Queue pickupQueue = null;
        PriorityQueue priorityQueue = null;
        
        int choice;
        
        do {
            System.out.println("\n ===== PARCEL DELIVERY ROUTER MENU ======");
                System.out.println("[1] Enter Parcels");
                System.out.println("[2] Display All Parcels");
                System.out.println("[3] Sort Parcels By Name");
                System.out.println("[4] Sort Parcels By ZIP");
                System.out.println("[5] Sort Parcels By Weight");
                System.out.println("[6] Enqueue All Parcels");
                System.out.println("[7] Dequeue From Parcel Pickup Queue");
                System.out.println("[8] Insert Parcels Into Priority Queue");
                System.out.println("[9] Deliver From Priority Queue");
                System.out.println("[10] Exit");
                System.out.print("Enter Your Choice: ");
            choice = console.nextInt();
            console.nextLine();
            
            switch(choice) {
                case 1:
                    System.out.print("Enter Number of Parcels: ");
                    int num = console.nextInt();
                    console.nextLine();
                    parcels = new Parcel[num];
                    
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
                    pickupQueue = new Queue(parcels.length);
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
                    break;
                    
                default:
                    System.out.println("Invalid Choice, Please Choose A Number On The Menu");
            }
        } while (choice != 10);
        
        console.close();
    }
}

