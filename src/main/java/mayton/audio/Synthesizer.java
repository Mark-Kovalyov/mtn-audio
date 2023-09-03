package mayton.audio;


import org.apache.commons.lang3.Validate;

public class Synthesizer {

    static final double π = Math.PI;

    static final double π2 = π * 2.0;

    private Synthesizer() {}

    public static double[] generateMeander(int lengthMs, int frequency, double amplitude) {
        Validate.exclusiveBetween(0.0,1.0,amplitude);
        Validate.isTrue(lengthMs >= 0);
        Validate.exclusiveBetween(0 , 44100, frequency);
        int ticks = (int) (lengthMs / 1000.0 * frequency);
        double φ = 1.0;
        double[] data = new double[ticks];
        for (int i = 0; i < ticks; i++) {

        }
        return data;
    }

    public static double[] generateSine(int lengthMs, int frequency, double amplitude) {
        Validate.exclusiveBetween(0.0,1.0,amplitude);
        Validate.isTrue(lengthMs >= 0);
        Validate.exclusiveBetween(0 , 44100, frequency);
        int ticks = (int) (lengthMs / 1000.0 * frequency);
        double φ = 1.0;
        double[] data = new double[ticks];
        for (int i = 0; i < ticks; i++) {
            data[i] = amplitude * Math.sin(φ * i);
        }
        return data;
    }

    public static double[] generateSilence(int lengthMs, int frequency) {
        int ticks = (int) (lengthMs / 1000.0 * frequency);
        Validate.exclusiveBetween(0 , 44100, frequency);
        Validate.isTrue(lengthMs >= 0);
        double[] data = new double[ticks];
        return new double[0];
    }

}
