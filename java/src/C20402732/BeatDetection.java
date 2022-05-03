package C20402732;

import ie.assignment.OOP;

import ddf.minim.analysis.BeatDetect;


public class BeatDetection {

    public Boolean HatBeat(BeatDetect beat) {

        if (beat.isRange(24, 10, 5))
            return true;

        return false;
    }

    public Boolean KickBeat(BeatDetect beat) {


        if (beat.isKick())
           return true;

        return false;
    }
}