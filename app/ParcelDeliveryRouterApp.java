

package parceldeliveryproject.app;
import parceldeliveryproject.model.*;



public class ParcelDeliveryRouterApp {
    public static void main(String[] args) {
        Parcel created = new Parcel(
            "Skibidi",
            (short) 2100,
            (float) 12.1,
            false
            );

        System.out.println(created.ID);
    }
}

