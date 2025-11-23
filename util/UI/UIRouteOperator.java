
package parceldeliveryproject.util.UI;
import parceldeliveryproject.util.*;
import parceldeliveryproject.model.*;
import parceldeliveryproject.ds.*;

import java.util.Scanner;

public class UIRouteOperator extends UIBase {
    
    public UIRouteOperator(Platform Scope) {
        super(Scope);
        menuTitle = "Route Operator Menu";
        displayChoices = new String[] {
            "Show Selected Route Node",
            "Show Current Route",
            "Move Selected Left by n",
            "Move Selected Right by n",
            "Select Node from the left of the current by n",
            "Select Node from the right of the current by n",
            "Undo",
            "Redo"
        };

        
    }
    
    @Override
    public void selectedChoice(int choice) {
        Scanner console = Scope.console;
        Queue pickupQueue = Scope.pickupQueue;
        DoubleyLinkedList RouteDLL = Scope.RouteDLL;
        StackX<RouteEditAction> REACurrentHistory = Scope.REACurrentHistory;
        StackX<RouteEditAction> REAUndoHistory = Scope.REAUndoHistory;

        switch(choice) {
            case 1:
                System.out.println("You have currently selected route: " + Scope.ntisfeitro);
                break;
            case 2:
                System.out.println("The current route...\n");
                RouteDLL.printWhole(Scope.ntisfeitro);
                break;
            case 3: // Move Selected Left
                
                System.out.println("\nInsert amount to move left by... (<1 will be set to 1)");
                int leftAmount = console.nextInt();

                int movedLeftBy = RouteDLL.moveNodeLeftBy(Scope.ntisfeitro, leftAmount);
                if (movedLeftBy == 0) {
                    System.out.println("this was already at the max left!");
                    break;
                }
                System.out.println("Moved successfully by " + movedLeftBy + "!");
                RouteDLL.printWhole(Scope.ntisfeitro);

                if (REACurrentHistory.isFull()) {
                    System.out.println("Route editor action history has reached max limit (" + Scope.REAHistoryMaxSize + "); creating anew...");
                    Scope.resetREAHistory();
                    REACurrentHistory = Scope.REACurrentHistory;
                    REAUndoHistory = Scope.REAUndoHistory;
                }
                if (!REAUndoHistory.isEmpty()) { // clear the undo history IF it isn't empty because it isn't relevant anymore
                    Scope.REAUndoHistory();
                    //REAUndoHistory = Scope.REAUndoHistory; // i just remembered that this file isn't continious
                }
                REACurrentHistory.push(new RouteEditAction(Scope.ntisfeitro, movedLeftBy, true));

                break;
            case 4: // Move Selected Right
                
                System.out.println("\nInsert amount to move right by... (<1 will be set to 1)");
                int rightAmount = console.nextInt();

                int movedRightBy = RouteDLL.moveNodeRightBy(Scope.ntisfeitro, rightAmount);
                if (movedRightBy == 0) {
                    System.out.println("this was already at the max right!");
                    break;
                }
                System.out.println("Moved successfully by " + movedRightBy + "!");
                RouteDLL.printWhole(Scope.ntisfeitro);

                if (REACurrentHistory.isFull()) {
                    System.out.println("Route editor action history has reached max limit (" + Scope.REAHistoryMaxSize + "); creating anew...");
                    Scope.resetREAHistory();
                    REACurrentHistory = Scope.REACurrentHistory;
                    REAUndoHistory = Scope.REAUndoHistory;
                }
                if (!REAUndoHistory.isEmpty()) { // clear the undo history IF it isn't empty because it isn't relevant anymore
                    Scope.REAUndoHistory();
                    //REAUndoHistory = Scope.REAUndoHistory; // i just remembered that this file isn't continious
                }
                REACurrentHistory.push(new RouteEditAction(Scope.ntisfeitro, movedRightBy, false));

                break;
            case 5: // Select going left
                System.out.println("\nInsert amount to select left by... (<1 will be set to 1)");
                int selectLeftBy = console.nextInt();
                if (selectLeftBy < 1) {
                    selectLeftBy = 1;
                }

                DoubleyLinkedList.Node cnftslo = Scope.ntisfeitro; // current node for the select left operation
                if (cnftslo.up == null) {
                    System.out.println("Can't select further left!");
                    break;
                }
                
                for (int i = 0; i < selectLeftBy; i++) {
                    DoubleyLinkedList.Node temp = cnftslo.up;
                    if (temp == null) break;
                    cnftslo = temp;
                }

                Scope.ntisfeitro = cnftslo;
                System.out.println("Selcted Route Node: " + cnftslo + "!");
                RouteDLL.printWhole(Scope.ntisfeitro);

                break;


            case 6: // Select going right
                System.out.println("\nInsert amount to select right by... (<1 will be set to 1)");
                int selectRightBy = console.nextInt();
                if (selectRightBy < 1) {
                    selectRightBy = 1;
                }

                DoubleyLinkedList.Node cnftsro = Scope.ntisfeitro; // current node for the select right operation
                if (cnftsro.down == null) {
                    System.out.println("Can't select further right!");
                    break;
                }
                
                for (int i = 0; i < selectRightBy; i++) {
                    DoubleyLinkedList.Node temp = cnftsro.down;
                    if (temp == null) break;
                    cnftsro = temp;
                }

                Scope.ntisfeitro = cnftsro;
                System.out.println("Selcted Route Node: " + cnftsro + "!");
                RouteDLL.printWhole(Scope.ntisfeitro);
                break;



            case 7: // undo
                if (REACurrentHistory.isEmpty()) {
                    System.out.println("Cannot further Undo!");
                    break;
                }
                RouteEditAction poppedUndo = REACurrentHistory.pop();
                REAUndoHistory.push(poppedUndo); // no need to guard; as if this is max: the only way to add more is by undoing, and undoing will clear the undo history
                
                if (poppedUndo.goingLeft) {
                    RouteDLL.moveNodeRightBy(poppedUndo.affectedNode, poppedUndo.moves);
                } else {
                    RouteDLL.moveNodeLeftBy(poppedUndo.affectedNode, poppedUndo.moves);
                }

                System.out.println("Successfully undo'd!");
                RouteDLL.printWhole(Scope.ntisfeitro);
                break;

            case 8: // redo
                if (REAUndoHistory.isEmpty()) {
                    System.out.println("No Redos available!");
                    break;
                }
                if (REACurrentHistory.isFull()) {
                    System.out.println("Route editor action history has reached max limit (" + Scope.REAHistoryMaxSize + "); creating anew...");
                    Scope.resetREAHistory();
                    //REACurrentHistory = Scope.REACurrentHistory; // i just remembered that this file isn't continious
                    //REAUndoHistory = Scope.REAUndoHistory;
                    System.out.println("Aborting redo action");
                    break;
                }
                RouteEditAction poppedRedo = REAUndoHistory.pop();
                REACurrentHistory.push(poppedRedo); // no need to guard; as per the check above
                
                if (poppedRedo.goingLeft) {
                    RouteDLL.moveNodeLeftBy(poppedRedo.affectedNode, poppedRedo.moves);
                } else {
                    RouteDLL.moveNodeRightBy(poppedRedo.affectedNode, poppedRedo.moves);
                }

                System.out.println("Successfully redo'd!");
                RouteDLL.printWhole(Scope.ntisfeitro);
                break;


        }
    }
}