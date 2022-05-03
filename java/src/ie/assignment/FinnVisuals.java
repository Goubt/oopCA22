package ie.assignment;

import ie.assignment.Branch;



public class FinnVisuals extends Visual {

    OOP v;
    Branch tree;

    public FinnVisuals(OOP v) {
        this.v = v;
    }

    public void render() {
        v.colorMode(OOP.HSB);
        v.strokeWeight(1);

        v.fill(0, 40);
        v.rect(-1, -1, v.width + 1, v.height + 1);

        v.fill(255);
        v.stroke(255);

        v.fCounter++;

        for (int i = 0; i < 6; i++) {
            v.resetMatrix();

            v.translate(v.width / 2, v.height / 2);
            v.branchCount = 0;

            v.rotate(OOP.map(v.fCounter % 360, 0, 360, 0, OOP.PI * 2));
            v.rotate(OOP.map((float) i, 0f, 6f, 0f, OOP.PI * 2));

            tree = new Branch(v, 0f, OOP.map(v.smoothedAmplitude, 0, .5f, -v.height / 15f, -v.height / 4f), 0, 20-);

            v.fill((v.fCounter / 10) % 255);
            v.stroke((v.fCounter / 10) % 255, 255, 255);

            tree.display();

        }

    }

}
    
}
