package mayton.audio;

public enum BitsPerSample {

    BITS_PER_SAMPLE_8(8),
    BITS_PER_SAMPLE_16(16);

    public final int bits;

    BitsPerSample(int bits) {
        this.bits = bits;
    }
}
