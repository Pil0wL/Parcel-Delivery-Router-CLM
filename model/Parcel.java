


package parceldeliveryproject.model;




public class Parcel {
    public final String ID;
    public final String Name;
    public final short ZIP;
    public final float Weight; // in kilograms
    public final boolean Fragile;
    public boolean InTransit = false;
    
    public Parcel(String Name, short ZIP, float Weight, boolean Fragile) {
        ID = Long.toString(System.nanoTime());;
        this.Name = Name;
        this.ZIP = ZIP;
        this.Weight = Weight;
        this.Fragile = Fragile;
    }
}
