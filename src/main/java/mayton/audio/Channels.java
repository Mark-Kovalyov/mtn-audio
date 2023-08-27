package mayton.audio;

public enum Channels {

    MONO(1), STEREO(2);

    public final int channels;

    Channels(int channels) {
        this.channels = channels;
    }
}
