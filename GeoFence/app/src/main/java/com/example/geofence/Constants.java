package com.example.geofence;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

final class Constants {
    private Constants(){

    }

    private static final String PACKAGE_NAME = "com.google.android.gms.location.Geofence";

    static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    private static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    static final float GEOFENCE_RADIUS_IN_METERS = 1609;

    static final HashMap<String, LatLng> BAY_AREA_LANDMARKS = new HashMap<>();

    static{
        BAY_AREA_LANDMARKS.put("SFO", new LatLng(37.621313, -122.378955));
        BAY_AREA_LANDMARKS.put("GOOGLE", new LatLng(37.422611, -122.0840577));
    }
}
