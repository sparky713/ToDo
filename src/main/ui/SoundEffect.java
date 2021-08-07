package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

// a helper class for sound effects
public class SoundEffect {

    private Clip effect;

    public void setFile(String audioFile) {
        try {
            File file = new File(audioFile);
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            effect = AudioSystem.getClip();
            effect.open(audio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound() {
        effect.setFramePosition(0);
        effect.start();
    }
}
