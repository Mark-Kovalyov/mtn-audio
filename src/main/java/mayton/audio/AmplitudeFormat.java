package mayton.audio;

public enum AmplitudeFormat {

    PCM_16_BIT(0),
    PCM_32_BIT_FP(1),
    PCM_64_BIT_FP(2);

    public final int format;

    AmplitudeFormat(int format) {
        this.format = format;
    }
}
