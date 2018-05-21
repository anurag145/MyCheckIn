package com.github.anurag145.mycheckin.estimote;

/**
 * Created by anura on 3/13/2018.
 */

public interface BeaconContentFactory {

    void getContent(BeaconID beaconID, Callback callback);

    interface Callback {
        void onContentReady(Object content);
    }
}