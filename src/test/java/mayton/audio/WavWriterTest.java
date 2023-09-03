package mayton.audio;

import mayton.audio.serde.AudioWriter;
import mayton.audio.serde.WavWriter;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WavWriterTest {

    @EnabledOnOs(OS.LINUX)
    @Test
    void simple_test() throws Exception {
        RandomAccessFile raf = new RandomAccessFile(File.createTempFile("mayton.audio.WavWriterTest_simple_test", ".dat"), "rw");
        AudioWriter w = new WavWriter(raf, SamplingFrequency.FREQUENCY_22K, BitsPerSample.BITS_PER_SAMPLE_16);
        w.close();
    }

    @EnabledOnOs(OS.LINUX)
    @Test
    @Disabled
    void simple_tone_3_sec_850hz() throws Exception {
        File file = File.createTempFile("mayton.audio.WavWriterTest_simple_tone_3_sec_850hz", ".wav");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        WavWriter w = new WavWriter(
                raf,
                SamplingFrequency.FREQUENCY_22K,
                BitsPerSample.BITS_PER_SAMPLE_16);
        double[] sine = new double[SamplingFrequency.FREQUENCY_22K.frequency * 3];
        w.writeBlock(sine);
        w.close();
        assertTrue(isCorrect(file.getAbsolutePath()));
    }

    private boolean isCorrect(String filepath) throws IOException, InterruptedException {
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
