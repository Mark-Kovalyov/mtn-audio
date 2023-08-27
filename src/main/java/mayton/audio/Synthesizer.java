package mayton.audio;


public class Synthesizer {

    static final double π = Math.PI;

    static final double π2 = π * 2.0;

    private Synthesizer() {}

    public static double[] generateMeander(int lengthMs, int frequency, double amplitude) {
        int ticks = (int) (lengthMs / 1000.0 * frequency);
        double φ = 1.0;
        double[] data = new double[ticks];
        for (int i = 0; i < ticks; i++) {

        }
        return data;
    }

    public static double[] generateSine(int lengthMs, int frequency, double amplitude) {
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
        double[] data = new double[ticks];
        return new double[0];
    }

}
