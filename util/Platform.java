
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
    public BSTRoute RealisticRoute = null;
    public VEHICP Truck = null;
}