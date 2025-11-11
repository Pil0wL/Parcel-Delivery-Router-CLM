package parcelrouter;

public class Parcel {
    String name;
    int zip;
    double weight;
    boolean fragperi;
    
        public Parcel(String name, int zip, double weight, boolean fragperi) {
            this.name = name;
            this.zip = zip;
            this.weight = weight;
            this.fragperi = fragperi;
        }
        
        public String toString() {
            return "| Name: " + name + " | ZIP: " + zip + "| Weight: " + weight + "kg | Fragile/Preishable: " + fragperi; 
        }       
}
