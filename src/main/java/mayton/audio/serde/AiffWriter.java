package mayton.audio.serde;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;



public class AiffWriter implements AudioWriter{

    public enum WavFormat {
        PCM(1);
        public final int code;
        WavFormat(int code) {
            this.code = code;
        }
    }

    private RandomAccessFile raf;

    private void writeChunk(double[] values) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
    }

    @Override
    public void writeBlock(double[] values) throws IOException, AudioWriterExeption {

    }

    @Override
    public void close() throws Exception {

    }
}
