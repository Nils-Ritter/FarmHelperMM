package com.jelly.farmhelperv3.util;

import com.jelly.farmhelperv3.config.FarmHelperConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;

public class LogUtils{
    private static final Minecraft mc = Minecraft.getInstance();
    private static String lastDebugMessage;
    private static long statusMsgTime = -1;
    private static int retries = 0;

    public synchronized static void sendLog(Component chat){
        if(mc.player != null /* && !FarmHelperConfig.streamerMode */){ //TODO: ALSO CHECK STREAMER MODE
            mc.player.sendSystemMessage(chat);
        }else if(mc.player == null){
            System.out.println("[Farm Helper] " + chat.toString());
        }
    }

    public static void sendSuccess(String message){
        sendLog(Component.literal("§2§lFarm Helper §8» §a" + message));
    }

    public static void sendWarning(String message){
        sendLog(Component.literal("§6§lFarm Helper §8» §e" + message));
    }

    public static void sendError(String message){
        sendLog(Component.literal("§4§lFarm Helper §8» §c" + message));
    }

    public static void sendDebug(String message){
        if(lastDebugMessage != null && lastDebugMessage.equals(message)){ return; }
        //TODO
    }
}