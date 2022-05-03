package C20402732;

import ie.assignment.OOP;

import processing.core.PVector;

public class GOOBAvisual extends OOP{

    OOP oop;

    public GOOBAvisual(OOP oop){
        this.oop = oop;
    }
    
    public void render() {
        oop.translate(width/2, height/2, -width);
        oop.stroke(255,0,0);
		oop.noFill();
		oop.box(100);
        oop.rect(0,0,width-100,height-1000); 
            oop.translate(width/2, height/2, -width);
            oop.background(0);
            float sum = 0;
            for(int i = 0 ; i < oop.getAudioBuffer().size() ; i ++)
              {
                  sum += abs(oop.getAudioBuffer().get(i));
                  OOP.lerpedBuffer[i] = lerp(OOP.lerpedBuffer[i], oop.getAudioBuffer().get(i), 0.05f);
              }
                for(int i = 0 ; i < oop.getAudioBuffer().size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, oop.getAudioBuffer().size(), 0, 255);
                    oop.stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * height/2 * 4.0f;
                    oop.line(i, height/2 + f, i, height - f);                    
                }

                
        float passed =  oop.getAudioPlayer().position();
            print(oop.mouseX);
                float dx = map(oop.mouseX, 0, oop.width, (float)-0.2, (float)0.2);
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
                    passed > 126500 && passed < 127000 ) {
                    OOP.tlps.addParticle();
                    OOP.trps.addParticle();
                    OOP.blps.addParticle();
                    OOP.brps.addParticle();
                    
                    }
    
    }
}
