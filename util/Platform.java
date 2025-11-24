
package parceldeliveryproject.util;


import parceldeliveryproject.ds.*;
import parceldeliveryproject.ds.BinarySearchTree.*;
import parceldeliveryproject.model.*;
import java.util.Scanner;


public class Platform { // a class used for sharing between UI
    public Parcel[] parcels = null;
    public Queue pickupQueue = null;
    public PriorityQueue priorityQueue = null;
    public Scanner console = null;

    
    public DoubleyLinkedList RouteDLL = null;
    public DoubleyLinkedList.Node ntisfeitro = null; // node that is selected for editing in the route operator
    public int REAHistoryMaxSize = 0; // route edit actions
    public StackX<RouteEditAction> REACurrentHistory = null; // route edit actions
    public StackX<RouteEditAction> REAUndoHistory = null; // route edit actions
    public VEHICP Truck = null;

    public SingleLinkedList deliveryLog = null;
    public BSTZIP ZIPIndex = null;

    public void resetREAHistory() {
        REACurrentHistory = new StackX<>(REAHistoryMaxSize);
        REAUndoHistory = new StackX<>(REAHistoryMaxSize);
    }
    public void resetUndoHistory() {
        REAUndoHistory = new StackX<>(REAHistoryMaxSize);
    }
}