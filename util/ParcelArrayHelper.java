
package parceldeliveryproject.util;

import parceldeliveryproject.model.Parcel;


public class ParcelArrayHelper { 
    
    public static void deletePos(Parcel[] parcels, int pos) {

        for (int i = pos; i < parcels.length; i++) { // delete in main roster
            // this basically moves every value after the found position down
            // therefore deleting the value
            if (parcels[i] == null) { // this slot is empty; no need to move anymore
                break;
            }
            Parcel value = parcels[i + 1];
            parcels[i] = value;
        }

    }
}