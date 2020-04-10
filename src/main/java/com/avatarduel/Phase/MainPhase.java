package com.avatarduel.Phase;

import com.avatarduel.Player;

public class MainPhase extends Phase {

    private int no;

    public MainPhase(Player aPlayer, int no) {
        super(aPlayer, TurnPhase.MAINPHASE1);
        this.no = no;
        
        if (this.no == 2) {
            this.pType = TurnPhase.MAINPHASE2;
        }
    }

    @Override
    public void playPhase() {
        // Hook ke GUI, nunggu input dari user.. binggung implementasinya

        // TODO : Meletakan 0 atau lebih kartu karakter

        // TODO : Mengubah posisi dari kartu karakter yang ada pada field
        if (no == 1) {

        } else if (no == 2) {
            // do nothing karena mainphase 2
        }

        // TODO : Meletakan maksimal satu kartu land

        // TODO : Meletakkan 0 atau lebih kartu skill

        // TODO : Membuang 0 atau lebih kartu skill
    }
}