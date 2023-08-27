package mayton.audio.serde;

import java.io.IOException;

public interface AudioWriter extends AutoCloseable {

    void writeBlock(double[] values, int channel) throws IOException;

}
