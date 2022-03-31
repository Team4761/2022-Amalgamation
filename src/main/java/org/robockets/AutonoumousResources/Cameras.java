package org.robockets.AutonoumousResources;

import org.photonvision.PhotonCamera;

public class Cameras {

    // Time to learn how these work!
    public static final PhotonCamera cam_front = new PhotonCamera("front");
    public static final PhotonCamera cam_intake = new PhotonCamera("intake");
    public static final PhotonCamera cam_climber = new PhotonCamera("climber");


    // Never used, but it COULD
    public Cameras() {
        cam_front.getLatestResult(); //wtf does this do?
    }
}
