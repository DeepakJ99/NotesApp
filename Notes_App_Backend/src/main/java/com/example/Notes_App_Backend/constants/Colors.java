package com.example.Notes_App_Backend.constants;

import java.util.Random;

public class Colors {
    private static final String[] COLORS = new String[]{
            "zomp", "pigment-green", "glaucous", "munsell-blue",
            "rose-taupe", "moss-green", "brown-sugar", "royal-purple",
            "redwood"
    };
    public static String generateRandomColor() {
        Random random = new Random();
        return COLORS[random.nextInt(COLORS.length)];
    }
}
