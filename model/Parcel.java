


package parceldeliveryproject.model;

import parceldeliveryproject.ds.DoubleyLinkedList;




public class Parcel {
    public final String ID;
    public final String Name;
    public final int ZIP;
    public final double Weight; // in kilograms
    public final boolean Fragile;
    
    public DoubleyLinkedList.Node AssociatedNode = null; // think of this as its target house
    public boolean InTransit = false;
    
    public Parcel(String Name, int ZIP, double Weight, boolean Fragile) {
        ID = Long.toString(System.nanoTime());
        this.Name = Name;
        this.ZIP = ZIP;
        this.Weight = Weight;
        this.Fragile = Fragile;
    }

    @Override
    public String toString() {
        return String.format(
            "ID = %s, Name = %s, ZIP = %d, Weight (kg) = %.2f, Fragile = %s, In Transit = %s", 
            ID, 
            Name, 
            ZIP, 
            Weight, 
            Boolean.toString(Fragile), 
            Boolean.toString(InTransit)
            );
    }
}
