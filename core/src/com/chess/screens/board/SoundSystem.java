package com.chess.screens.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundSystem {

    public enum SOUND_KEYS {
        REJECT_CLICK, CLICK, ATTACK
    }

    private static Sound click = Gdx.audio.newSound(Gdx.files.internal("audio/click.wav"));
    private static Sound rejectClick = Gdx.audio.newSound(Gdx.files.internal("audio/rejectClick.wav"));
    private static Sound attack = Gdx.audio.newSound(Gdx.files.internal("audio/attack.wav"));

    public static void play(SOUND_KEYS key) {
        if (key == SOUND_KEYS.CLICK) {
            click.play();
        } else if (key == SOUND_KEYS.REJECT_CLICK) {
            rejectClick.play();
        } else if (key == SOUND_KEYS.ATTACK) {
            attack.play();
        }
    }


}
