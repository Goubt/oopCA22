package C20492576;

import java.util.concurrent.ThreadLocalRandom;

public class Colours {

    int min = 0;
    int max = 255;

    public float r = ThreadLocalRandom.current().nextInt(min, max);
    public float g = ThreadLocalRandom.current().nextInt(min, max);
    public float b = ThreadLocalRandom.current().nextInt(min, max);
    public int rChange = 1;
    public int gChange = 1;
    public int bChange = 1;
}
