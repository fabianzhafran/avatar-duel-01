package com.avatarduel.phase;

import java.util.HashMap;

public class PhaseEnum {

    public static HashMap<Integer, String> phaseEnum = PhaseEnum.phaseEnumReceive();
 
    public static int DRAW_PHASE = 1;
    public static int MAIN_PHASE_1 = 2;
    public static int BATTLE_PHASE = 3;
    public static int END_PHASE = 4;

    public static HashMap<Integer, String> phaseEnumReceive() {
        HashMap<Integer, String> returnMap = new HashMap<Integer, String>();
        returnMap.put(1, "Draw Phase");
        returnMap.put(2, "Main Phase");
        returnMap.put(3, "Battle Phase");
        returnMap.put(4, "End Phase");
        return returnMap;
    }

}