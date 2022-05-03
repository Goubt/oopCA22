package ie.assignment;
import ddf.minim.analysis.BeatDetect;

class BeatDetection extends OOP{

    public Boolean HatBeat(BeatDetect beat) {

        if (beat.isRange(24, 10, 5))
            return true;

        return false;
    }

    public Boolean readBeat(BeatDetect beat) {

        if (beat.isKick())
            return true;

        return false;
    }

}