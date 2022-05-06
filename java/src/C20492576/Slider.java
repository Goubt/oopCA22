package C20492576;

import ie.assignment.*;

public class Slider extends OOP {

    OOP obj;
    int sliderWidth, sliderHeight;
    float xPosition, yPosition;
    float sliderPosition, newSliderPosition;
    float max;
    int loose;
    boolean over;
    boolean locked;
    float ratio;
    float xMouse;
    float yMouse;

    public Slider(OOP obj, float xp, float yp, int sw, int sh, int l, int off) {
        this.obj = obj;

        sliderWidth = sw;
        sliderHeight = sh;

        xMouse = obj.mouseX - obj.height / 2;
        yMouse = obj.mouseY - obj.height / 2 - off;

        int widthHeight = sliderWidth - sliderHeight;

        ratio = (float) sliderWidth / (float) widthHeight;

        xPosition = xp;
        yPosition = yp - sliderHeight;

        sliderPosition = xPosition + sliderWidth / 2 - sliderHeight / 2;
        newSliderPosition = sliderPosition;

        max = xPosition + sliderWidth - sliderHeight;

        loose = l;
    }

    public void update() {
        if (overSlider() == true)
            over = true;

        else
            over = false;

        if (obj.mousePressed && over == true)
            locked = true;

        if (!obj.mousePressed)
            locked = false;

        if (locked == true)
            newSliderPosition = OOP.constrain(xMouse - sliderHeight / 2, xPosition, max);

        if (OOP.abs(newSliderPosition - sliderPosition) > 1)
            sliderPosition = sliderPosition + (newSliderPosition - sliderPosition) / loose;

    }

    public boolean overSlider() {
        if (xMouse > xPosition - sliderWidth && xMouse < xPosition + sliderWidth && yMouse > yPosition - sliderHeight
                && yMouse < yPosition + sliderHeight / 2) {
            return true;
        }

        else {
            return false;
        }
    }

    public void display() {

        println(max);

        obj.fill(204);
        obj.rect(xPosition, yPosition, sliderWidth, sliderHeight);
        if (over || locked) {
            obj.fill(0, 0, 0);
        } else {
            obj.fill(102, 102, 102);
        }
        obj.rect(xMouse, yPosition, sliderHeight, sliderHeight);
    }

    public float getPos() {
        // Convert sliderPos to be values between
        // 0 and the total width of the scrollbar
        return sliderPosition * ratio;
    }

}
