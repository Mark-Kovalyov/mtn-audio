package mayton.audio.serde;

import mayton.audio.BitsPerSample;
import mayton.audio.Channels;
import mayton.audio.SamplingFrequency;
import mayton.audio.serde.AudioWriter;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class WavWriter implements AudioWriter {

    private OutputStream outputStream;

    public static final int HEADER_SIZE = 44;

    private RandomAccessFile randomAccessFile;
    int samplingFrequency;
    int bitsPerSample;
    int chunks;
    int samplesPerChunk;
    int channels;

    public WavWriter(RandomAccessFile randomAccessFile, SamplingFrequency samplingFrequencyEnum, BitsPerSample bitsPerSampleEnum, Channels channelsEnum) throws IOException {
        this.randomAccessFile = randomAccessFile;
        this.samplingFrequency = samplingFrequencyEnum.frequency;
        this.bitsPerSample = bitsPerSampleEnum.bits;
        this.channels = channelsEnum.channels;
        writeHeader();
    }

    private int calculateDataSize() {
        return bitsPerSample * chunks * samplesPerChunk * channels - 8;
    }

    private void writeHeader() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1000);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(0x46_46_49_52); // RIFF
        int dataSize = 0x0; // Temporary!!!
        buffer.putInt(0x0); // Size of the overall file - 8 bytes, in bytes (32-bit integer). Typically, you’d fill this in after creation.
        buffer.putInt(0x66_45_56_41); // WAVE
        buffer.put((byte)'f');
        buffer.put((byte)'m');
        buffer.put((byte)'t');
        buffer.putShort((short) 16); // Length of format data as listed above ?
        buffer.putShort((short) 1);  // PCM
        buffer.putShort((short) channels);
        buffer.putInt(samplingFrequency); // Sample Rate - 32 byte integer. Common values are 44100 (CD), 48000 (DAT). Sample Rate = Number of Samples per second, or Hertz.
        buffer.putInt((samplingFrequency * bitsPerSample * channels) / 8); // (Sample Rate * BitsPerSample * Channels) / 8.
        // (BitsPerSample * Channels) / 8.1 - 8 bit mono2 - 8 bit stereo/16 bit mono4 - 16 bit stereo
        // Bits per sample
        // “data” chunk header. Marks the beginning of the data section.
        // Size of the data section.
        // ....

        randomAccessFile.write(buffer.array());
    }

    @Override
    public void close() throws Exception {
        randomAccessFile.seek(0);
        if (outputStream != null) {
            outputStream.close();
        }
    }



    @Override
    public void writeBlock(double[] values, int channel) throws IOException {
        Validate.isTrue(channel < channels);
        for (int i = 0; i < values.length; i++) {
            randomAccessFile.writeShort((int) (32768.0 * values[i]));
        }
    }
}
