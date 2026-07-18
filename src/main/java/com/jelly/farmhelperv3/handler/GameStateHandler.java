package com.jelly.farmhelperv3.handler;

import com.jelly.farmhelperv3.config.FarmHelperConfig;
import com.jelly.farmhelperv3.util.helper.Clock;
import lombok.Getter;
import lombok.Setter;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLevelEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.telemetry.events.WorldUnloadEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.server.players.PlayerList;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class GameStateHandler {
    private static GameStateHandler INSTANCE;
    private static final Minecraft mc = Minecraft.getInstance();
    private static final Pattern areaPattern = Pattern.compile("Area:\\s(.+)");
    private static final Timer notMovingTimer = new Timer();
    private static final Timer rewarpTimer = new Timer();
    private static final Clock jacobContestLeftClock = new Clock();
    public static final Pattern jacobsRemainingTimePattern = Pattern.compile("([0-9]|[1-2][0-9])m([0-9]|[1-5][0-9])s");
    public static final Pattern jacobsStartsInTimePattern = Pattern.compile("Starts In: ([1-3]?[0-9])?m ?([1-5]?[0-9])?s?");
    private static final Pattern serverClosingPattern = Pattern.compile("Server closing: (?<minutes>\\d+):(?<seconds>\\d+) .*");
    private static final Pattern pestsFromVacuumPattern = Pattern.compile("Vacuum Bag: ([\\d,]+) Pest(s)?");
    private static final Pattern composterResourceTablistPattern = Pattern.compile("\\s(Organic Matter|Fuel): (\\d{1,3}(\\.\\d{1,3})?)k");

    @Getter
    private static Location lastLocation = Location.TELEPORTING;
    @Getter
    private static Location location = Location.TELEPORTING;

    @Getter
    private static long lastTimeInGarden = -1;

    private static boolean isInJacobContest = false;
    private static boolean isGuestInGarden = false;
    @Getter
    private static boolean frontWalkable;
    @Getter
    private static boolean rightWalkable;
    @Getter
    private static boolean leftWalkable;
    @Getter
    private static boolean backWalkable;

    @Getter
    private static double dx;
    @Getter
    private static double dy;
    @Getter
    private static double dz;

    @Getter
    private static String serverIP;
    private static long randomValueToWait = -1;
    private static long randomRewarpValueToWait = -1;
    private static long randomValueToWaitNextTime = -1;

    @Getter
    private static BuffState cookieBuffState = BuffState.UNKNOWN;
    @Getter
    private static BuffState godPotState = BuffState.UNKNOWN;
    @Getter @Setter
    private static BuffState pestRepellentState = BuffState.UNKNOWN;
    @Getter
    private static BuffState composterState = BuffState.UNKNOWN;

    @Getter
    private static double currentPurse = 0;
    @Getter
    private static double previousPurse = 0;
    @Getter
    private static long bits = 0;
    @Getter
    private static long copper = 0;

    @Getter
    private static int currentPlot = 0;
    @Getter
    private static List<Integer> infestedPlots = new ArrayList<>();
    @Getter
    private static int pestsCount = 0;
    @Getter
    private static int currentPlotPestsCount = 0;
    @Getter
    private static int organicmatterCount = Integer.MAX_VALUE;
    @Getter
    private static int fuelCount = Integer.MAX_VALUE;

    @Getter
    private static Optional<FarmHelperConfig.CropEnum> jacobsContestCrop = Optional.empty();
    @Getter
    private static List<FarmHelperConfig.CropEnum> jacobsContestNextCrop = new ArrayList<>();
    @Getter
    private static int jacobsContestCropNumber = 0;
    @Getter
    private static JacobMedal jacobMedal = JacobMedal.NONE;
    @Getter @Setter
    private static boolean wasInJacobContest = false;

    @Getter @Setter
    private static Optional<Integer> serverClosingSeconds = Optional.empty();

    @Getter
    private static int speed = 0;

    @Setter
    private static boolean updatedState = false;

    public static GameStateHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GameStateHandler();
        }
        return INSTANCE;
    }

    public static void initGameStateHandlerEvents(){
        //onWorldChange && onWorldLoad
        ClientLevelEvents.AFTER_CLIENT_LEVEL_CHANGE.register(((client, level) -> {
            lastLocation = location;
            location = Location.TELEPORTING;
            serverClosingSeconds = Optional.empty();
            if(mc.getCurrentServer() != null && mc.getCurrentServer().ip != null){
                serverIP = mc.getCurrentServer().ip;
            }
        }));

        //onChatMessageRecieved
        ClientReceiveMessageEvents.CHAT.register((component, playerChatMessage, gameProfile, bound, instant) -> {
            if(playerChatMessage == null){ return; }
            String message = playerChatMessage.toString().trim();
            if(message.equals("You were spawned in Limbo.") || message.equals("You are AFK. Move around to return from AFK.")){
                lastLocation = location;
                location = Location.LIMBO;
            }
        });

        //TODO: onTablistUpdate

        //onUpdateScoreboardLine
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.level == null || client.player == null){ return; }
            Scoreboard scoreboard = client.level.getScoreboard();
            Objective objective = scoreboard.getDisplayObjective(DisplaySlot.SIDEBAR);
            if(objective == null){ return; }

            List<PlayerScoreEntry> scores = new ArrayList<>(scoreboard.listPlayerScores(objective));
            scores.removeIf(score -> score.value() <= 0 || score.owner().startsWith("#"));

            scores.sort(Comparator.comparingInt(PlayerScoreEntry::value).reversed().thenComparing(PlayerScoreEntry::owner));

            List<String> lines = new ArrayList<>();
            for(PlayerScoreEntry score : scores){
                String name = score.owner();
                Team team = scoreboard.getPlayersTeam(name);

                if(team != null){
                    lines.add(team.getFormattedName(Component.literal(name)).toString());
                }else{
                    lines.add(name);
                }
            }

            for(String line : lines){
                checkServerClosing(line);
            }

            checkCurrentPests(lines);
        });

        //onTick
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.level == null || client.player == null){ return; }
        });
    }

    private void onTickCheckMoving(){ }

    private void onTickCheckSpeed(){
        float speed = mc.player.getSpeed();
        this.speed = (int) (speed * 1_000);
    }

    private static void onTickCheckRewarp(){ }

    private static void onTickCheckPlot(){ }

    private static void checkServerClosing(String cleanedLine){
        Matcher serverClosingMatcher = serverClosingPattern.matcher(cleanedLine);
        if(serverClosingMatcher.find()){
            int minutes = Integer.parseInt(serverClosingMatcher.group("minutes"));
            int seconds = Integer.parseInt(serverClosingMatcher.group("seconds"));
            serverClosingSeconds = Optional.of(minutes * 60 + seconds);
        }
    }

    private static void checkCurrentPests(List<String> list) {
        int pestsCountTemp = 0;
        for (String cleanedLine : list) {
            if (cleanedLine.contains("The Garden") && cleanedLine.contains("ൠ")) {
                try {
                    String[] split = cleanedLine.trim().split(" ");
                    int temp = Integer.parseInt(split[split.length - 1].trim().replace("x", ""));
                    int previousPestsCount = pestsCount;
                    pestsCount = temp;
                    pestsCountTemp = temp;
                    /*
                    if (pestsCount > previousPestsCount && !PestsDestroyer.getInstance().isRunning() && pestsCount > FarmHelperConfig.startKillingPestsAt) {
                        if (FarmHelperConfig.sendWebhookLogIfPestsDetectionNumberExceeded) {
                            LogUtils.webhookLog("[Pests Destroyer]\\nThere " + (pestsCount > 1 ? "are" : "is") + " currently **" + pestsCount + "** " + (pestsCount > 1 ? "pests" : "pest") + " in the garden!", FarmHelperConfig.pingEveryoneOnPestsDetectionNumberExceeded);
                        }
                        if (FarmHelperConfig.sendNotificationIfPestsDetectionNumberExceeded) {
                            FailsafeUtils.getInstance().sendNotification("There " + (pestsCount > 1 ? "are" : "is") + " currently " + pestsCount + " " + (pestsCount > 1 ? "pests" : "pest") + " in the garden!", TrayIcon.MessageType.WARNING);
                        }
                    }
                     */
                } catch (NumberFormatException ignored) {
                    pestsCount = 0;
                }
            }
            if (cleanedLine.contains("Plot") && cleanedLine.contains("x")) {
                String[] split = cleanedLine.trim().split(" ");
                String last = split[split.length - 1];
                try {
                    currentPlotPestsCount = Integer.parseInt(last.replace("x", ""));
                } catch (NumberFormatException ignored) {
                    currentPlotPestsCount = 0;
                }
            } else if (cleanedLine.contains("Plot")) {
                currentPlotPestsCount = 0;
            }
        }
        if (pestsCountTemp != pestsCount) {
            pestsCount = pestsCountTemp;
        }
        if (pestsCount == 0) {
            infestedPlots.clear();
        }
    }


    @Getter
    public enum Location{
        PRIVATE_ISLAND("Private Island"),
        HUB("Hub"),
        THE_PARK("The Park"),
        THE_FARMING_ISLANDS("The Farming Islands"),
        SPIDER_DEN("Spider's Den"),
        THE_END("The End"),
        CRIMSON_ISLE("Crimson Isle"),
        GOLD_MINE("Gold Mine"),
        DEEP_CAVERNS("Deep Caverns"),
        DWARVEN_MINES("Dwarven Mines"),
        CRYSTAL_HOLLOWS("Crystal Hollows"),
        JERRY_WORKSHOP("Jerry's Workshop"),
        DUNGEON_HUB("Dungeon Hub"),
        LIMBO("UNKNOWN"),
        LOBBY("PROTOTYPE"),
        GARDEN("Garden"),
        DUNGEON("Dungeon"),
        UNKNOWN(""),
        TELEPORTING("Teleporting");

        private final String name;

        Location(String name) {
            this.name = name;
        }
    }

    public enum BuffState{
        ACTIVE,
        FAILSAFE,
        NOT_ACTIVE,
        UNKNOWN
    }

    private enum JacobMedal{
        NONE,
        BRONZE,
        SILVER,
        GOLD,
        PLATINUM,
        DIAMOND
    }
}
