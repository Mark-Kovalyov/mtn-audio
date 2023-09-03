package mayton.audio.serde;

import java.io.IOException;

public interface AudioWriter extends AutoCloseable {

    void writeBlock(double[] values) throws IOException, AudioWriterExeption;

}
