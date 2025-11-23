


package parceldeliveryproject.model;

import parceldeliveryproject.ds.DoubleyLinkedList;

public class RouteEditAction {
    
    public final int moves;
    public final boolean goingLeft;
    public DoubleyLinkedList.Node affectedNode;

    public RouteEditAction(DoubleyLinkedList.Node affectedNode, int moves, boolean goingLeft) {
        this.affectedNode = affectedNode;
        this.moves = moves;
        this.goingLeft = goingLeft;
    }

}
