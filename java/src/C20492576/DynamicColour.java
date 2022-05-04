package C20492576;

import ie.assignment.*;

public class DynamicColour {

    OOP obj;
    Colours cl;

    public DynamicColour(OOP obj, Colours cl) {
        this.obj = obj;
        this.cl = cl;
    }

    public void changeColour(float speed) {

        cl.r += cl.rChange * speed;

        if (cl.r < 0 || cl.r > 255) {
            cl.rChange *= -1;
        }

        cl.g += cl.gChange * speed;

        if (cl.g < 0 || cl.g > 255) {
            cl.gChange *= -1;
        }

        cl.b += cl.bChange * speed;

        if (cl.b < 0 || cl.b > 255) {
            cl.bChange *= -1;
        }

        obj.stroke(cl.r, cl.g, cl.b);
    }
}
