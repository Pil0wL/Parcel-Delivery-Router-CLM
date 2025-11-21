

package parceldeliveryproject.app;

import parceldeliveryproject.ds.*;
import parceldeliveryproject.model.Parcel;
import parceldeliveryproject.util.UI.*;
import parceldeliveryproject.util.Platform;
import java.util.Scanner;

public class ParcelDeliveryRouterApp {
    public static void main(String[] args) {
        
        Scanner console = new Scanner(System.in);
        
        
        Platform Scope = new Platform();
        Scope.parcels = new Parcel[5];
        Scope.pickupQueue = new Queue(5);
        Scope.priorityQueue = new PriorityQueue(5);
        Scope.console = console;

        UIBase[] interfaceArray = new UIBase[] {
            new UIParcel(Scope),
        };
        
        UIBase selectedInterface = interfaceArray[0];

        boolean running = true;
        while (running) {
            System.out.println("\n ===== PARCEL DELIVERY ROUTER MENU ======");
            if (selectedInterface != null) {
                int lastChoice = UIBase.readChoices(selectedInterface);
                // vvv these two below are considered outer options
                System.out.println(String.format(UIBase.choiceFormat, lastChoice + 1, "Select Other Menus"));
                System.out.println(String.format(UIBase.choiceFormat, lastChoice + 2, "Quick Exit"));
                
                
                System.out.print("Enter Your Choice: ");
                int choice = console.nextInt();
                console.nextLine();

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


            System.out.println("Temp breaker because i haven't had the option to make this select an option of which menu you want to go to");
            break;
        }
        
        console.close();
    }
}

