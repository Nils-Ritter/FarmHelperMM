package com.jelly.farmhelperv3.config;

import lombok.Getter;

public class FarmHelperConfig{
    @Getter
    public enum CropEnum{
        NONE("None"),
        CARROT("Carrot"),
        NETHER_WART("Nether Wart"),
        POTATO("Potato"),
        WHEAT("Wheat"),
        SUGAR_CANE("Sugar Cane"),
        MELON("Melon"),
        PUMPKIN("Pumpkin"),
        PUMPKIN_MELON_UNKNOWN("Pumpkin/Melon"),
        CACTUS("Cactus"),
        COCOA_BEANS("Cocoa Beans"),
        MUSHROOM("Mushroom"),
        MUSHROOM_ROTATE("Mushroom"),
        SUNFLOWER("Sunflower"),
        MOONFLOWER("Moonflower"),
        ROSE("Rose");

        final String localizedName;

        CropEnum(String localizedName) {
            this.localizedName = localizedName;
        }
    }
}
