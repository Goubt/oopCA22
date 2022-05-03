package ie.assignment;

import ddf.minim.analysis.BeatDetect;


public class BeatDetection {

    public Boolean HatBeat(BeatDetect beat) {

        if (beat.isHat())
            return true;

        return false;
    }

    public Boolean KickBeat(BeatDetect beat) {


        if (beat.isKick())
           return true;

        return false;
    }
}