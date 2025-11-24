

package parceldeliveryproject.app;

import parceldeliveryproject.ds.*;
import parceldeliveryproject.model.*;
import parceldeliveryproject.util.UI.*;
import parceldeliveryproject.util.Platform;
import java.util.Scanner;

public class ParcelDeliveryRouterApp {

    public static void main(String[] args) {
        
        Scanner console = new Scanner(System.in);
        
        Platform Scope = new Platform();
        Scope.parcels = new Parcel[5];
        Scope.pickupQueue = new Queue(5);
        Scope.console = console;
        Scope.RouteDLL = new DoubleyLinkedList();
        Scope.REAHistoryMaxSize = 64;
        Scope.resetREAHistory();
        Scope.Truck = new VEHICP();
        Scope.deliveryLog = new SingleLinkedList();

        Scope.RouteDLL._load(Scope);
        //Scope.RealisticRoute._load(Scope);
        Scope.Truck._load(Scope); // needs to be after the routedll

        Scope.ntisfeitro = Scope.RouteDLL._WareHouse;
        

        UIBase[] interfaceArray = new UIBase[] {
            new UIParcel(Scope),
            new UIPerishableQueue(Scope),
            new UIPickupTransit(Scope),
            new UIRouteOperator(Scope),
            new UITruckControls(Scope),
            new UIIndex(Scope)
        };
        
        UIBase selectedInterface = interfaceArray[0];

        boolean running = true;
        while (running) {
            System.out.println("\n ===== PARCEL DELIVERY ROUTER MENU ======");
            if (selectedInterface != null) {
                int lastChoice = selectedInterface.readChoices();
                // vvv these two below are considered outer options
                System.out.println(""); // command line seperator
                System.out.println((String) String.format(UIBase.choiceFormat, lastChoice + 1, "Select Other Menus"));
                System.out.println((String) String.format(UIBase.choiceFormat, lastChoice + 2, "Quick Exit"));
                
                
                System.out.print("Enter Your Choice: ");
                int choice = console.nextInt();

                { // this section determines if the choice was in range
                    int max = selectedInterface.displayChoices.length;
                    
                    if (choice < 1 || choice > (max + 2)) {

                        System.out.println("Invalid Choice, Please Choose A Number On The Menu");

                        continue;
                    }
                }

                { // special case to select the outer two options
                    int transformedChoice = choice - lastChoice; 
                    switch(transformedChoice) {
                        case 1:
                            System.out.println("Giving the option to select other menus...");
                            selectedInterface = null;
                            continue;
                        case 2:
                            System.out.println("Exiting...");
                            running = false;
                            continue;
                    }
                }

                selectedInterface.selectedChoice(choice);

                continue;
            }

            // to display the other menus

            for (int i = 0; i < interfaceArray.length; i++) {
                UIBase indexedInterface = interfaceArray[i];
                String message = String.format(UIBase.choiceFormat, i + 1, indexedInterface.menuTitle);
                System.out.println(message);
            }
            System.out.print("Select a menu: ");
            int choice = console.nextInt();

            if (choice < 1 || choice > interfaceArray.length) {
                System.out.println("Choice is out of range");
                continue;
            }

            selectedInterface = interfaceArray[choice - 1];

            //System.out.println("Temp breaker because i haven't had the option to make this select an option of which menu you want to go to");
            //break;
        }

        System.out.println("Closed the Menu...");
        console.close();
    }
}

