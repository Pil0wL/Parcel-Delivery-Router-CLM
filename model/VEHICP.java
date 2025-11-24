


package parceldeliveryproject.model;

import java.text.BreakIterator;
import parceldeliveryproject.ds.DoubleyLinkedList;
import parceldeliveryproject.util.Platform;




public class VEHICP { // Very Efficient Hauler In Carrying Parcels
    


    private class Cargo {
        public Parcel[] parcels;
        public final int maxSize = 3;
        public int currentSize = 0;

        public Cargo() {
            parcels = new Parcel[maxSize];
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public boolean isFull() {
            return currentSize == maxSize;
        }

        public void insert(Parcel toInsert) {
            parcels[currentSize] = toInsert;
            currentSize++;
        }
        public void remove(int pos) {
            for (int i = pos; i < parcels.length - 1; i++) { // delete in main roster
                // this basically moves every value after the found position down
                // therefore deleting the value
                if (parcels[i] == null) { // this slot is empty; no need to move anymore
                    break;
                }
                Parcel value = parcels[i + 1];
                parcels[i] = value;
            }
            parcels[maxSize - 1] = null; // if you are removing; the last one will always be null
            currentSize--;
        }
        public void display() {
            for (Parcel p: parcels) {
                System.out.print((p == null) ? "[empty slot]" : p);
                System.out.print(" | ");

            }
        }
    }
    private final Cargo carryingCargo;
    public VEHICP() {
        carryingCargo = new Cargo();
    }


    Platform Scope;
    public void _load(Platform Scope) {
        this.Scope = Scope;
        currentNode = Scope.RouteDLL._WareHouse;
    }

    private DoubleyLinkedList.Node currentNode;
    private DoubleyLinkedList.Node currentTarget = null;

    public void iterate() {
        DoubleyLinkedList RouteDLL = Scope.RouteDLL;
        
        
        System.out.println("Currently at " + currentNode);
        System.out.println((currentTarget == null) ? "I dont have a target currently!" : ("I am trying to go to " + currentTarget));

        if (currentTarget == null) {
            currentTarget = RouteDLL.getHead(); // if there are none; it will default to WareHouse
            if (currentTarget == Scope.RouteDLL._WareHouse && currentTarget.down == null) {
                currentTarget = null;
                System.out.println("There are currently no other routes other than the warehouse!");
                return;
            }

            
            System.out.println("I got assigned a new target! " + currentTarget);
        }
        if (carryingCargo.isEmpty()) { // return to warehouse
            currentTarget = null;
            System.out.println("the cargo hold is empty ...removed my target!");
            if (currentNode != RouteDLL._WareHouse) {
                System.out.println("Returning to Warehouse...");
                traverse(RouteDLL._WareHouse);
                return;
            }
            System.out.println("I am currently at the warehouse!");
            while (!carryingCargo.isFull()) { // refill cargo from the pickup queue
                Parcel gotParcel = Scope.pickupQueue.dequeue();
                if (gotParcel == null) break;
                
                gotParcel.InTransit = true;
                carryingCargo.insert(gotParcel);
            }
            if (carryingCargo.isEmpty()) { // cargo is still empty even after refillin
                System.out.println("No Parcels in the pickup queue to deliver!");
                return;
            }

            System.out.println("Successfully refilled parcels as cargo!");
            return;
        }


        // go through all current parcels in the cargo and see if atleast one of them is this destination/node
        for (int i = 0; i < carryingCargo.currentSize; i++) {
            Parcel value = carryingCargo.parcels[i];
            if (value == null) break;
            if (value.AssociatedNode == currentNode) { // one of these parcels destination is this
                carryingCargo.remove(i); // remove the parcel
                System.out.println("Delivered a parcel!");
                RouteDLL.removeNode(value.AssociatedNode); // remove this destination from the system
                value.AssociatedNode = null; // me when i free up memory ts so tuff frfr
                value.InTransit = false;

                Scope.deliveryLog.add(value); // add this delivery to the delivery log

                System.out.println("Reset Route edit action history! ... because a route was removed and it wont be relevant anymore");
                Scope.resetREAHistory();


                if (currentTarget == currentNode) {
                    System.out.println("...this was also my target, I am going to be removing it");
                    currentTarget = null;
                }

                // go unto either node since this one currently is removed
                DoubleyLinkedList.Node moveOverTo = currentNode.up;
                if (moveOverTo == null) {
                    currentNode = currentNode.down;
                } else {
                    currentNode = moveOverTo;
                }
                System.out.println("Moved over to " + currentNode + " because the previous one is invalid!");

                return;
            }
        }

        // Main movement AI
        if (currentNode == currentTarget) {
            currentTarget = currentTarget.down; // go to next one
            if (currentTarget == null) {
                System.out.println("reached the target! but there is no more routes after this one, aborting traverse!");
                return;
            }
            System.out.println("reached target! going to the next one after it in the route plan, which is: " + currentTarget);
        }
        traverse(currentTarget);
    }

    private boolean wasGoingLeft = true;
    private void traverse(DoubleyLinkedList.Node currentTarget) {
        System.out.println("I was going " + (wasGoingLeft ? "Left" : "Right"));
        int ZIPToCompare = currentNode.ZIP;
        if (currentTarget.ZIP == ZIPToCompare) { // if the two addresses are the same
            if (wasGoingLeft) { // if it was going left beforehand
                ZIPToCompare++; // it will always go left
            } else {
                ZIPToCompare--; // ... otherwise right
            }
        }
        DoubleyLinkedList.Node newLocation;
        String directionTook;

        if (currentTarget.ZIP < ZIPToCompare) { // traverse left
            wasGoingLeft = true;
            newLocation = currentNode.leftLocation;
            directionTook = "Left";
        } else { // traverse right
            wasGoingLeft = false;
            newLocation = currentNode.rightLocation;
            directionTook = "Right";
        }

        if (newLocation == null) { // this will most likely never show up hopefully lmao
            System.out.println("This is a dead end; tell the devs or smthn that this is nearly impossible to get, unless ofc after the truck reached its destination you changed the route plan to either side, but that's on you! resetting target");
            currentTarget = null;
            return;
        }

        currentNode = newLocation;
        System.out.println((String) String.format("Truck successfully traversed %s! right now its at %s", directionTook, newLocation.toString()));

    }

    public void displayCargo() {
        carryingCargo.display();
    }
}
