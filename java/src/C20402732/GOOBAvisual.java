package C20402732;

import C20492576.Colours;
import C20492576.DynamicColour;
import ie.assignment.OOP;

import processing.core.PVector;

public class GOOBAvisual extends OOP {

    OOP oop;
    DynamicColour gdc;

    public GOOBAvisual(OOP oop, Colours gcl) {
        this.oop = oop;
        gdc = new DynamicColour(oop, gcl);
    }

    int hypno = 0;
    float hypnocontrol = 0;
    float hypnogrow = 0;
    float diameter = 0;
    int colour = 0;
    int limit = 300;
    int rotation = 0;
    int spiral = 0;
    int lock = 0;
    int dolly = 0;
    

    public void render() {
        oop.calculateAverageAmplitude();
        dolly();
        oop.noFill();
        oop.pushMatrix();
        shrinkngrow();
        circle();
        centre();
        poison();
        oop.popMatrix();
        
    }

    public void dolly(){
        if(oop.lock == false)
            dolly = 0;
        if (oop.keyPressed) {
            if ((oop.key == 'i' || oop.key == 'I') && oop.lock == true && dolly > -200) {
                    oop.camera1.dolly(-10);
                    dolly--;
            }
        }
        if (oop.keyPressed) {
            if ((oop.key == 'k' || oop.key == 'K') && oop.lock == true && dolly < 200) {
                    oop.camera1.dolly(10);
                    dolly++;
            }
        }
    }

    public void circle() {
        oop.stroke(255, 120, 255);
        diameter = oop.getSmoothedAmplitude();
        diameter = map(diameter, 0, 1, limit, limit + 70);
        oop.rotate(radians(frameCount % 360 * 2));
        oop.strokeWeight(5);
        oop.noFill();
        oop.ellipse(0, 0, diameter * 2, diameter * 2);

        oop.pushMatrix();

        oop.rotate(radians(frameCount % 360 * 2));

        for (int k = 0; k < 720; k++) {
            colour = (int) map(k, 0, 360, 0, 255);
            int change = spiral % 3;
            if (k % 3 == change)
                oop.stroke(200,200,200);
            if (k % 3 == (change+1)%3)
                oop.stroke(128,0,128);
            if (k % 3 == (change+2)%3)
                oop.stroke(0,200,0);
        

            oop.line(cos(k) * diameter, sin(k) * diameter,
                    cos(k) * oop.getSmoothedAmplitude()* diameter + cos(k) * diameter * 2,
                    sin(k) * oop.getSmoothedAmplitude() * diameter + sin(k) * diameter);
            oop.circle(cos(k) * oop.getSmoothedAmplitude() * diameter + cos(k) * diameter * 2, sin(k) * oop.getSmoothedAmplitude() * diameter + sin(k) * diameter, 8);
        } 
        if (oop.frameCount % 10 == 2)
            spiral++;
        oop.strokeWeight(1);
        oop.popMatrix();

    }

    public void shrinkngrow() {
        int time = oop.getAudioPlayer().position();
        if ((time > 53500 && time < 76000 && limit > 100) || (time > 94500 && limit != 300)) {
            limit--;
        }
        if (time > 76000 && time < 94500) {
            limit++;
            oop.rotate(0);
        }
        
        if (limit == 100 && time > 58000 && time < 76000) {
            oop.rotate(radians(rotation));
            rotation++;
            oop.triangle(0, (float) (-limit * 7.5), limit * 6, (float) (limit * 4.5), -limit * 6,
                    (float) (limit * 4.5));
        }
    }

    public void centre() {
        oop.pushMatrix();
        hypnogrow = map(diameter, limit, limit + 70, limit - 30, limit - 10);
        oop.strokeWeight(3);
        for (int i = 0; i < hypnogrow; i++) {
            gdc.changeColour(0.1f);
            oop.rotate(radians((hypnocontrol % 360) + 180));
            oop.pushMatrix();
            oop.translate(0, (hypno % limit), -i);
            oop.line(0, 0, 0, (float) (limit / 7.5));
            oop.popMatrix();
            hypno++;
        }

        oop.popMatrix();
        hypnocontrol = (float) (hypnocontrol + 0.01);

    }

    public void poison() {

        float passed = oop.getAudioPlayer().position();
        
        float dx = map(oop.mouseX, 0, oop.width, (float) -0.2, (float) 0.2);
        PVector wind = new PVector(dx, 0);
        OOP.tlps.applyForce(wind);
        OOP.trps.applyForce(wind);
        OOP.blps.applyForce(wind);
        OOP.brps.applyForce(wind);
        OOP.tlps.run();
        OOP.trps.run();
        OOP.blps.run();
        OOP.brps.run();
        if (passed > 39500 && passed < 40000 ||
                passed > 44000 && passed < 44500 ||
                passed > 48500 && passed < 49000 ||
                passed > 53000 && passed < 53500 ||
                passed > 113000 && passed < 113500 ||
                passed > 117500 && passed < 118000 ||
                passed > 122000 && passed < 122500 ||
                passed > 126500 && passed < 127000) {
            OOP.tlps.addParticle();
            OOP.trps.addParticle();
            OOP.blps.addParticle();
            OOP.brps.addParticle();

        }
    }
}
