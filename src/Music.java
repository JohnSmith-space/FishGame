import java.io.*;
import javax.sound.sampled.*;

public class Music extends Thread {
    private String resourcePath;
    private final int EXTERNAL_BUFFER_SIZE = 524288;
    public Music(String resourcePath) {
        this.resourcePath = resourcePath;
    }
    @SuppressWarnings("unused")
    public void run() {
        while (true) {
            InputStream is = Music.class.getResourceAsStream(resourcePath);
            if (is == null) {
                System.err.println("Wave file not found in classpath: " + resourcePath);
                return;
            }
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(is);
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }

            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine auline = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            if (auline.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
            }
            auline.start();
            int nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
            try {
                while (nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        auline.write(abData, 0, nBytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } finally {
                auline.drain();
                auline.close();
                try {
                    audioInputStream.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
