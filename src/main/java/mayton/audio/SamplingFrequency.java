package mayton.audio;

public enum SamplingFrequency {

    FREQUENCY_44K(44100),
    FREQUENCY_22K(22050),
    FREQUENCY_11K(11025),
    FREQUENCY_8K(8000);

    public final int frequency;

    SamplingFrequency(int frequency) {
        this.frequency = frequency;
    }
}
