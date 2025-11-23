
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
            "Show Selected Route Node",
            "Show Current Route",
            "Move Selected by Left by n",
            "Move Selected by Right by n",
            "Undo",
            "Redo",
            "(Iterate Delivery Transport)"
        };

        
    }
    
    @Override
    public void selectedChoice(int choice) {
        Scanner console = Scope.console;
        Queue pickupQueue = Scope.pickupQueue;
        DoubleyLinkedList RouteDLL = Scope.RouteDLL;

        switch(choice) {
            case 1:
                System.out.println("You are currently at route: " + Scope.ntisfeitro);
                break;
            case 2:
                System.out.println("The current route...\n");
                RouteDLL.printWhole();
                break;
            case 3:
                
                System.out.println("\nInsert amount to move by...");
                System.out.print("Name: ");
                int name = console.nextInt();

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