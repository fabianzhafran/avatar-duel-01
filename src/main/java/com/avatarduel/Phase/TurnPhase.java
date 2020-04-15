package com.avatarduel.Phase;

public enum TurnPhase {
    DRAWPHASE("Draw Phase"), MAINPHASE1("Main Phase 1"), BATTLEPHASE("Battle Phase"), MAINPHASE2("Main Phase 2"),
    ENDPHASE("End Phase");

    private String pName;

    TurnPhase(String text) {
        this.pName = text;
    }

    @Override
    public String toString() {
        return this.pName;
    }
}