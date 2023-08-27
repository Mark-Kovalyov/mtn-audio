package mayton.audio;

import mayton.audio.serde.WavWriter;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WavWriterTest {

    @Test
    void simple_test() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(File.createTempFile("mayton.audio.WavWriterTest_simple_test", ".dat"), "rw");
        new WavWriter(raf, SamplingFrequency.FREQUENCY_22K, BitsPerSample.BITS_PER_SAMPLE_16, Channels.MONO);
    }

    @Test
    void simple_tone_3_sec_850hz() throws Exception {
        File file = File.createTempFile("mayton.audio.WavWriterTest_simple_tone_3_sec_850hz", ".wav");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        WavWriter w = new WavWriter(
                raf,
                SamplingFrequency.FREQUENCY_22K,
                BitsPerSample.BITS_PER_SAMPLE_16,
                Channels.MONO);
        double[] sine = new double[SamplingFrequency.FREQUENCY_22K.frequency * 3];
        w.writeBlock(sine, 0);
        w.close();

        assertEquals("", file.getAbsolutePath());
    }

    boolean isCorrect(String filepath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/usr/bin/soxi", filepath);
        Process process = processBuilder.start();
        int errorCode = process.waitFor();
        List<String> out = IOUtils.readLines(process.getInputStream(), StandardCharsets.UTF_8);
        out.forEach(item -> System.out.println(item));
        List<String> err =  IOUtils.readLines(process.getErrorStream(), StandardCharsets.UTF_8);
        err.forEach(item -> System.err.println(item));
        return errorCode == 0;
    }

}
