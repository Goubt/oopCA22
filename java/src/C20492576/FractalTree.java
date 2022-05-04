package C20492576;

import ie.assignment.OOP;
import processing.core.PApplet;

public class FractalTree extends PApplet{

    OOP tree;
    DynamicColour dc;
    FractalTree start;

    float amplitude;
    float angle = 0;

    FractalTree[] branches;

    
    
    public FractalTree(OOP tree, float amplitude, float angle, int noBranches) {

        this.tree = tree;
        this.amplitude = amplitude;
        this.angle = angle;
        branch(noBranches);
        dc = new DynamicColour(tree);

    }

    void display() {

        tree.rotate(this.angle);

        tree.line(0, 0, 0, amplitude);

        tree.pushMatrix();

        if (branches != null) {
            tree.translate(0, amplitude);
            tree.pushMatrix();
            branches[0].display();
            tree.popMatrix();
            branches[1].display();
        }

        tree.popMatrix();
    }

    void branch(int noBranches) {

        if (noBranches > 0) {

            float angle = OOP.map(tree.getSmoothedAmplitude(), 0, 1, 3.14f / 10f, 3.14f / 2f);

            branches = new FractalTree[2];
            tree.branchCount += 2;

            branches[0] = new FractalTree(tree, amplitude / 1.4f, angle, noBranches - 2);
            branches[1] = new FractalTree(tree, amplitude / 1.3f, -angle, noBranches - 2);

        }
    }



    public void render() {

    
        
        tree.rotationCycle++;

        //tree.rotate(OOP.map(tree.rotationCycle % 360, 0, 360, 0, OOP.PI * 4));
        println(tree.rotateTree);
        tree.rotate(tree.rotateTree);

        for (int i = 0; i < 8; i++) {
            // tree.resetMatrix();
            tree.rotate(tree.PI / 4);
            tree.pushMatrix();

            tree.translate(tree.width / 2, tree.height / 2);

            tree.branchCount = 0;
            tree.popMatrix();

            // tree.stroke(tree.r,tree.g,tree.b);

            dc.changeColour(0.1f);

            // tree.stroke((tree.rotationCycle / 2) % 255, (tree.rotationCycle / 3) % 255,
            // (tree.rotationCycle / 4) % 255);

            display();

        }

    }
}