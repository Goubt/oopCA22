package C20402732;

import ie.assignment.OOP;

import processing.core.PVector;

public class GOOBAvisual extends OOP {

    OOP oop;

    public GOOBAvisual(OOP oop) {
        this.oop = oop;
    }

    int bleh = 0;

    public void render() {

        oop.calculateAverageAmplitude();
        oop.background(0);
        oop.noFill();
        Kickflash();
        HatFlash();
        circle();
        // sphere();
        poison();

    }

    public void HatFlash() {
        oop.beat.detect(oop.getAudioPlayer().mix);
        Boolean type = oop.fBeat.HatBeat((oop.beat));
        float actualsize = map(oop.spheresize, 0, 30, 50, 400);

        oop.pushMatrix();
        oop.fill(255, 255, 255);
        oop.noStroke();
        oop.translate(oop.width / 2, oop.height / 2, 0);
        oop.sphere(actualsize);
        oop.translate(-oop.width, 0, 0);
        oop.sphere(actualsize);
        oop.translate(0, -oop.height, 0);
        oop.sphere(actualsize);
        oop.translate(oop.width, 0, 0);
        oop.sphere(actualsize);
        oop.popMatrix();
        oop.spheresize = backgroundBeat(type, oop.spheresize);

        if (oop.spheresize > 5)
            oop.spheresize -= 2;
    }

    public void Kickflash() {
        oop.beat.detect(oop.getAudioPlayer().mix);
        Boolean type = oop.fBeat.KickBeat((oop.beat));

        oop.background(oop.screenBrightness);
        oop.screenBrightness = backgroundBeat(type, oop.screenBrightness);

        if (oop.screenBrightness > 10)
            oop.screenBrightness -= 2;
    }

    public void circle() {
        oop.stroke(255, 120, 255);
        oop.strokeWeight(10);
        oop.noFill();
        oop.ellipse(0, 0, 1000, 1000);
        oop.fill(0, 5);

        oop.pushMatrix();

        oop.rotate(radians(frameCount % 360 * 2));
        for (int j = 0; j < 360; j++) {

            oop.line(cos(j) * 500, sin(j) * 500, cos(j) * abs(oop.getAudioPlayer().left.get(j)) * 200 + cos(j) * 500,
                    sin(j) * abs(oop.getAudioPlayer().right.get(j)) * 200 + sin(j) * 500);
        }
        for (int k = 360; k > 0; k--) {

            oop.line(cos(k) * 500, sin(k) * 500, cos(k) * abs(oop.getAudioPlayer().right.get(k)) * 200 + cos(k) * 500,
                    sin(k) * abs(oop.getAudioPlayer().left.get(k)) * 200 + sin(k) * 500);
        }

        oop.popMatrix();
    }

    public void sphere() {
        oop.pushMatrix();
        oop.strokeWeight(1);
        oop.rotate(bleh);
        oop.sphere(oop.getSmoothedAmplitude() * 1500);
        oop.popMatrix();

        bleh++;
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
