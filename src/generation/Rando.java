package generation;

import java.util.Random;

/**
 * Used to generate random numbers in unique ways
 */

public class Rando {
    
    static int randoRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
