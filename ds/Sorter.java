

package parceldeliveryproject.ds;

import parceldeliveryproject.model.Parcel;

public class Sorter {
    private static int currentMaxSize(Parcel[] parcels) {
        int i = 0;
        for (; i < parcels.length; i++) {
            if (parcels[i] == null) {
                break;
            } 
        }
        return i;
    }

    public static void bubbleSortName(Parcel[] parcels) {
        int max = currentMaxSize(parcels);

        for (int i = 0; i < max - 1; i++) {
            for (int j = 0; j < max - i - 1; j++) {
                if (parcels[j].Name.compareToIgnoreCase(parcels[j + 1].Name) > 0) {
                    Parcel temp = parcels[j];
                    parcels[j] = parcels[j + 1];
                    parcels[j + 1] = temp;
                }
            }
        }
    }
    
    public static void insertionSortZIP(Parcel[] parcels) {
        int max = currentMaxSize(parcels);


        for (int i = 1; i < max; i++) {
            Parcel key = parcels[i];
            int j = i - 1;

            while (j >= 0 && parcels[j].ZIP > key.ZIP) {
                parcels[j + 1] = parcels[j];
                j--;
            }

            parcels[j + 1] = key;
        }
    }
    
    public static void selectionSortWeight(Parcel[] parcels) {
        int max = currentMaxSize(parcels);
        for (int i = 0; i < max - 1; i++) {
            int min = i;
            for (int j = i + 1; j < max; j++) {
                if (parcels[j].Weight < parcels[min].Weight) {
                min = j;
                }
            }
            Parcel temp = parcels[i];
            parcels[i] = parcels[min];
            parcels[min] = temp;
        }
    }
}
