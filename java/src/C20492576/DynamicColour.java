package C20492576;

import ie.assignment.*;

public class DynamicColour {

    OOP obj;

    public DynamicColour(OOP obj) {
        this.obj = obj;
    }

    public void changeColour(float speed) {

        obj.clFinn.r += obj.clFinn.rChange * speed;

        if (obj.clFinn.r < 0 || obj.clFinn.r > 255) {
            obj.clFinn.rChange *= -1;
        }

        obj.clFinn.g += obj.clFinn.gChange * speed;

        if (obj.clFinn.g < 0 || obj.clFinn.g > 255) {
            obj.clFinn.gChange *= -1;
        }

        obj.clFinn.b += obj.clFinn.bChange * speed;

        if (obj.clFinn.b < 0 || obj.clFinn.b > 255) {
            obj.clFinn.bChange *= -1;
        }

        obj.stroke(obj.clFinn.r, obj.clFinn.g, obj.clFinn.b);
    }
}