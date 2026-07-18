package com.jelly.farmhelperv3.config;

import com.jelly.farmhelperv3.FarmHelper;
import com.jelly.farmhelperv3.config.struct.Rewarp;
import com.jelly.farmhelperv3.handler.GameStateHandler;
import com.jelly.farmhelperv3.util.LogUtils;
import io.wispforest.owo.ui.core.Color;
import lombok.Getter;
import io.wispforest.owo.config.annotation.Config;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Config(name = "farm-helper-config", wrapperName = "Farm_Helper_Configuration")
public class FarmHelperConfig{
    private transient static final Minecraft mc = Minecraft.getInstance();
    private transient static final String GENERAL = "General";
    private transient static final String MISCELLANEOUS = "Miscellaneous";
    private transient static final String FAILSAFE = "Failsafe";
    private transient static final String SCHEDULER = "Scheduler";
    private transient static final String JACOBS_CONTEST = "Jacob's Contest";
    private transient static final String VISITORS_MACRO = "Visitors Macro";
    private transient static final String PESTS_DESTROYER = "Pests Destroyer";
    private transient static final String PEST_FARMER = "Pest Farmer";
    private transient static final String AUTO_PEST_EXCHANGE = "Auto Pest Exchange";
    private transient static final String AUTO_GOD_POT = "Auto God Pot";
    private transient static final String AUTO_SELL = "Auto Sell";
    private transient static final String AUTO_REPELLENT = "Auto Repellent";
    private transient static final String AUTO_SPRAYONATOR = "Auto Sprayonator";
    private transient static final String AUTO_COMPOSTER = "Auto Composter";
    private transient static final String DISCORD_INTEGRATION = "Discord Integration";
    private transient static final String DELAYS = "Delays";
    private transient static final String HUD = "HUD";
    private transient static final String DEBUG = "Debug";
    private transient static final String EXPERIMENTAL = "Experimental";

    private transient static final File configRewarpFile = new File("farmhelper_rewarp.json");

    public static List<Rewarp> rewarpList = new ArrayList<>();

    //Settings:
    public static boolean proxyEnabled = false;
    public static String proxyAddress = "";
    public static String proxyUsername = "";
    public static String proxyPassword = "";
    //TODO: public static ProxyType proxyType = ProxyType.HTTP;

    public static boolean guiInfo;
    public static int macroType = 0; //indexes of all the macro types :3
    public static boolean alwaysHoldW = false;
    public static boolean customFarmingSpeed = false;
    public static int farmingSpeed = 400;
    public static boolean rotateAfterWarped = false;
    public static boolean rotateAfterDrop = false;
    public static boolean dontFixAfterWarping = false;
    public static boolean customPitch = false;
    public static float customPitchLevel = 0;
    public static boolean customYaw = false;
    public static float customYawLevel = 0;
    public static boolean highlightRewarp = true;
    public static boolean rewarpWarning;
    Runnable _addRewarp = FarmHelperConfig::addRewarp;
    Runnable _removeRewarp = FarmHelperConfig::removeRewarp;
    Runnable _removeAllRewarps = FarmHelperConfig::removeAllRewarps;
    public static int spawnPosX = 0;
    public static int spawnPosY = 0;
    public static int spawnPosZ = 0;
    public static float spawnYaw = 0;
    public static float spawnPitch = 0;
    public static int spawnPlot = 0;
    //TODO: Runnable _setSpawnPos = PlayerUtils::setSpawnLocation;
    Runnable _resetSpawnPos = () -> {
        spawnPosX = 0;
        spawnPosY = 0;
        spawnPosZ = 0;
        LogUtils.sendSuccess("Spawn position has been reset!");
    };
    public static boolean drawSpawnLocation = true;
    public static final KeyMapping.Category farmHelper = null;
    public static final KeyMapping toggleMacroKeybind = new KeyMapping(
            "key.farm-helper.toggleMacroKeybind",
            GLFW.GLFW_KEY_GRAVE_ACCENT,
            KeyMapping.Category.MISC
    );
    public static final KeyMapping openGuiKeybind = new KeyMapping(
            "key.farm-helper.openGuiKeybind",
            GLFW.GLFW_KEY_F,
            KeyMapping.Category.MISC
    );
    public static final KeyMapping freelookKeybind = new KeyMapping(
            "key.farm-helper.freelookKeybind",
            GLFW.GLFW_KEY_L,
            KeyMapping.Category.MISC
    );
    public static final KeyMapping cancelFailsafeKeybind = new KeyMapping(
            "key.farm-helper.cancelFailsafeKeybind",
            GLFW.GLFW_PLATFORM_NULL, //To be set later in Settings, currently unbound
            KeyMapping.Category.MISC
    );
    public static final KeyMapping tpToInfestedPlotKeybinding = new KeyMapping(
            "key.farm-helper.tpToInfestedPlotKeybinding",
            GLFW.GLFW_PLATFORM_NULL, //To be set later in Settings, currently unbound
            KeyMapping.Category.MISC
    );
    public static final KeyMapping plotCleaningHelperKeybind = new KeyMapping(
            "key.farm-helper.plotCleaningHelperKeybind",
            GLFW.GLFW_PLATFORM_NULL, //To be set later in Settings, currently unbound
            KeyMapping.Category.MISC
    );
    private int freelookWarning;
    public static boolean autoChooseTool = false;
    public static boolean autoUpdateDownloadBetaVersions = false;
    Runnable _checkForUpdate = () -> { FarmHelperConfig.checkForUpdate(); };
    public static boolean muteTheGame = false;
    public static boolean changeWindowTitle = true;
    public static boolean autoCookie = false;
    public static boolean holdLeftClickWhenChangingRow = true;
    public static boolean autoUngrabMouse = true;
    public static boolean pipMode = false;
    public static boolean tmpAntiStuckEnabled = false;
    public static int antiStuckTriesUntilRewarp = 5;
    public static boolean performanceMode = false;
    public static boolean fastRender = true;
    public static int performanceModeMaxFPS = 20;
    public static boolean increasedCocoaBeans = true;
    public static boolean increasedCrops = true;
    public static boolean increasedNetherWarts = true;
    public static boolean increasedMushrooms = true;
    public static boolean pinglessCactus = true;
    public static boolean sendAnalyticData = true;
    public static boolean popUpNotifications = true;
    public static boolean autoAltTab = false;
    public static boolean failsafeAction = false;
    public static int failsafeStopDelay = 2000;
    public static boolean autoWarpOnWorldChange = true;
    public static boolean autoEvacuateOnServerReboot = true;
    public static boolean autoReconnect = true;
    public static boolean pauseOnGuestArrival = false;
    public static float teleportLagTolerance = 0.5f;
    public static int detectionTimeWindow = 500;
    public static float pitchSensitivity = 7;
    public static float yawSensitivity = 5;
    public static float teleportDistanceThreshold = 4;
    public static float verticalKnockbackThreshold = 4000;
    public static boolean enableBpsCheck = true;
    public static float minBpsThreshold = 10f;
    public static boolean customReactionsWarning1;
    public static boolean customReactionsWarning2;
    public static boolean customReactionsWarning3;
    public static boolean customReactionsWarning4;
    public static boolean customReactionsWarning5;
    public static boolean enableCustomReactions = false;
    //public static boolean customFailsafeSoundWarning;
    Runnable _testFailsafe = () -> {
        //TODO:
        /*
        if (!MacroHandler.getInstance().isMacroToggled()) {
            LogUtils.sendError("You need to start the macro first!");
            return;
        }
        LogUtils.sendWarning("Testing failsafe...");
        PlayerUtils.closeScreen();
        Failsafe testingFailsafe = FailsafeManager.getInstance().failsafes.get(testFailsafeType);
        if (testingFailsafe.equals(DirtFailsafe.getInstance()) || testingFailsafe.equals(BedrockCageFailsafe.getInstance())) {
            LogUtils.sendError("You can't test this failsafe because it requires specific conditions to trigger!");
            return;
        }
        FailsafeManager.getInstance().possibleDetection(testingFailsafe);
         */
    };
    public static int testFailsafeType = 0;
    public static boolean captureClipAfterFailsafe = false;
    public static boolean captureClipAfterGettingBanned = false;
    public static boolean clipCapturingType = false;
    //public static OneKeyBind captureClipKeybind = new OneKeyBind(Keyboard.KEY_NONE);
    public static int captureClipDelay = 30;
    public static boolean captureClipWarning;
    public static boolean captureClipWarning2;
    //TODO: public FailsafeNotificationsPage failsafeNotificationsPage = new FailsafeNotificationsPage();
    public static boolean checkDesync = true;
    public static int desyncPauseDelay = 5_000;
    public static boolean enableFailsafeSound = true;
    public static boolean failsafeSoundType = false;
    public static int failsafeMcSoundSelected = 1;
    public static int failsafeSoundSelected = 1;
    public static int failsafeSoundTimes = 13;
    public static boolean customFailsafeSoundWarning;
    public static float failsafeSoundVolume = 50.0f;
    public static boolean maxOutMinecraftSounds = false;
    //TODO: Runnable _playFailsafeSoundButton = () -> AudioManager.getInstance().playSound();
    //TODO: Runnable _stopFailsafeSoundButton = () -> AudioManager.getInstance().resetSound();
    public static boolean enableRestartAfterFailSafe = true;
    public static int restartAfterFailSafeDelay = 0;
    public static boolean restartAfterFailSafeInfo;
    public static boolean alwaysTeleportToGarden = false;
    public static boolean banwaveCheckerEnabled = true;
    public static boolean enableLeavePauseOnBanwave = false;
    public static boolean banwaveAction = false;
    public static int banwaveThresholdType = 0;
    public static int banwaveThreshold = 50;
    public static int delayBeforeReconnecting = 5;
    public static boolean banwaveDontLeaveDuringJacobsContest = true;
    public static boolean sendFailsafeMessage = false;
    //TODO: public static CustomFailsafeMessagesPage customFailsafeMessagesPage = new CustomFailsafeMessagesPage();
    public static boolean enableScheduler = true;
    public static int schedulerFarmingTime = 60;
    public static int schedulerFarmingTimeRandomness = 5;
    public static int schedulerBreakTime = 5;
    public static int schedulerBreakTimeRandomness = 5;
    public static boolean pauseSchedulerDuringJacobsContest = true;
    public static boolean openInventoryOnSchedulerBreaks = true;
    public static boolean schedulerDisconnectDuringBreak = false;
    public static boolean schedulerWaitUntilRewarp = false;
    public static boolean schedulerResetOnDisable = true;
    public Runnable schedulerReset = () -> {
        //TODO:
        /*
        if (!MacroHandler.getInstance().isMacroToggled()) {
            boolean old = FarmHelperConfig.schedulerResetOnDisable;
            FarmHelperConfig.schedulerResetOnDisable = true;
            Scheduler.getInstance().stop();
            FarmHelperConfig.schedulerResetOnDisable = old;
        }
         */
    };
    public static boolean leaveTimer = false;
    public static int leaveTime = 60;
    public static boolean enablePetSwapper = false;
    public static int petSwapperDelay = 1000;
    public static String petSwapperName = "";
    public static boolean enableJacobFailsafes = false;
    public static boolean jacobFailsafeAction = true;
    public static int jacobNetherWartCap = 800000;
    public static int jacobPotatoCap = 830000;
    public static int jacobCarrotCap = 860000;
    public static int jacobWheatCap = 265000;
    public static int jacobSugarCaneCap = 575000;
    public static int jacobMushroomCap = 250000;
    public static int jacobMelonCap = 1234000;
    public static int jacobPumpkinCap = 240000;
    public static int jacobCocoaBeansCap = 725000;
    public static int jacobCactusCap = 470000;
    public static boolean visitorsMacroWarning2;
    public static boolean infoCookieBuffRequired;
    public static boolean visitorsMacro = false;
    public static int visitorsMacroMinVisitors = 5;
    public static boolean visitorsMacroAutosellBeforeServing = false;
    public static boolean pauseVisitorsMacroDuringJacobsContest = true;
    public static int visitorsMacroMinMoney = 2_000;
    public static boolean infoCompactors;
    public static float visitorsMacroMaxSpendLimit = 0.7f;
    public static boolean visitorsMacroAfkInfiniteMode = false;
    public static boolean visitorsExchangeTravelMethodInfo;
    public static boolean visitorsExchangeTravelMethod = false;
    public static Runnable triggerVisitorsMacro = () -> {
        //TODO:
        /*
        if (!PlayerUtils.isInBarn()) {
            LogUtils.sendError("[Visitors Macro] You need to be in the barn to start the macro!");
            return;
        }
        VisitorsMacro.getInstance().setManuallyStarted(true);
        VisitorsMacro.getInstance().start();
         */
    };
    public static boolean visitorsFilteringMethod = false;
    public static boolean fullInventoryAction = true;
    public static boolean filterVisitorsByName = false;
    public static boolean nameFilteringType = false;
    public static boolean nameActionType = true;
    public static String nameFilter = "Librarian|Maeve|Spaceman";
    public static boolean filterVisitorsByRarity = true;
    public static boolean nameFilterInfo;
    public static int visitorsActionUncommon = 0;
    public static int visitorsActionRare = 0;
    public static int visitorsActionLegendary = 0;
    public static int visitorsActionMythic = 0;
    public static int visitorsActionSpecial = 3;
    public static boolean pestsDestroyerWarning1;
    public static boolean pestsDestroyerWarning2;
    public static boolean pestsDestroyerWarning3;
    public static boolean pestsDestroyerInfo;
    public static boolean enablePestsDestroyer = false;
    public static int startKillingPestsAt = 3;
    public static int pestAdditionalGUIDelay = 0;
    public static boolean sprintWhileFlying = false;
    public static boolean useAoteVInPestsDestroyer = false;
    public static boolean dontTeleportToPlots = false;
    public static boolean pausePestsDestroyerDuringJacobsContest = true;
    public static void triggerManuallyPestsDestroyer() {
        //TODO:
        /*
        if (PestsDestroyer.getInstance().canEnableMacro(true)) {
            PestsDestroyer.getInstance().start();
        }
         */
    }
    public static boolean pestsDestroyerAfkInfiniteMode = false;
    public static boolean pestsDestroyerOnTheTrack = false;
    public static int pestsDestroyerOnTheTrackFOV = 360;
    public static int pestsDestroyerOnTheTrackTimeForPestToStayInRange = 750;
    public static int pestsDestroyerOnTheTrackStuckTimer = 5_000;
    public static boolean dontKillPestsOnTrackDuringJacobsContest = true;
    public static KeyMapping enablePestsDestroyerKeyBind = new KeyMapping(
            "key.farm-helper.enablePestsDestroyerKeybind",
            GLFW.GLFW_PLATFORM_NULL, //unbound
            KeyMapping.Category.MISC
    );
    public static boolean pestSwapArmorBefore = false;
    public static int pestArmorSlot0 = 1;
    public static boolean pestSwapArmorAfter = false;
    public static int pestArmorSlot1 = 1;
    public static boolean pestSwapEquipments = false;
    public static String pestSwapEq = "";
    public static Color rewarpColor = new Color(0, 255, 217, 171);
    public static Color spawnColor = new Color(0, 255, 217, 171);
    public static boolean pestsESP = true;
    public static Color pestsESPColor = new Color(0, 255, 217, 171);
    public static boolean pestsTracers = true;
    public static Color pestsTracersColor = new Color(0, 255, 217, 171);
    public static boolean highlightPlotWithPests = true;
    public static Color plotHighlightColor = new Color(0, 255, 217, 40);
    public static boolean sendWebhookLogIfPestsDetectionNumberExceeded = true;
    public static boolean pingEveryoneOnPestsDetectionNumberExceeded = false;
    public static boolean sendNotificationIfPestsDetectionNumberExceeded = true;
    public static boolean sendWebhookLogWhenPestDestroyerStartsStops = true;
    public static boolean ignored1;
    public static boolean pestFarming = false;
    public static boolean pestFarmingUseMousemat = false;
    public static int pestFarmingFermentoSlot = 1;
    public static int pestFarmingBiohazardSlot = 1;
    public static int pestFarmingWaitTime = 255;
    public static boolean pestFarmingSetSpawn = false;
    public static boolean pestFarmingSwapEq = false;
    public static String pestFarmingEq0 = "";
    public static String pestFarmingEq1 = "";
    public static int pestFarmerEquipmentClickDelay = 400;
    public static boolean pestFarmerKillPests = false;
    public static boolean pestFarmerCastRod = false;
    public static int pestFarmerStartKillAt = 1;
    public static boolean enableWebHook = false;
    public static boolean sendLogs = false;
    public static boolean sendStatusUpdates = false;
    public static int statusUpdateInterval = 5;
    public static boolean sendVisitorsMacroLogs = true;
    public static boolean pingEveryoneOnVisitorsMacroLogs = false;
    public static boolean sendMacroEnableDisableLogs = true;
    public static boolean sendFHBanLogs = false;
    public static String webHookURL = "";
    public static boolean enableRemoteControl = false;
    public static String discordRemoteControlToken;
    public static String discordRemoteControlAddress = "localhost";
    public static int remoteControlPort = 21370;
    public static boolean infoRemoteControl;
    public static boolean info2RemoteControl;
    public static boolean autoPestExchangeInfo1;
    public static boolean autoPestExchange = false;
    public static boolean pauseAutoPestExchangeDuringJacobsContest = true;
    public static boolean autoPestExchangeIgnoreJacobsContest = false;
    public static boolean autoPestExchangeOnlyStartRelevant = false;
    public static boolean autoPestExchangeTravelMethod = false;
    public static boolean autoPestExchangeTravelMethodInfo;
    public static boolean autoPestExchangeTpDestiination = false;
    public static int autoPestExchangeTriggerBeforeContestStarts = 5;
    public static int autoPestExchangeMinPests = 10;
    public static boolean logAutoPestExchangeEvents = true;
    public static boolean highlightPestExchangeDeskLocation = true;
    public static boolean autoPestExchangeInfo;
    public static void triggerManuallyAutoPestExchange() {
        //TODO:
        /*
        AutoPestExchange.getInstance().setManuallyStarted(true);
        AutoPestExchange.getInstance().start();
         */
    }
    public static Runnable setPestExchangeLocation = () -> {
        //TODO:
        /*
        if (!PlayerUtils.isInBarn()) {
            LogUtils.sendError("[Auto Pest Exchange] You need to be in the barn to set the pest exchange location!");
            return;
        }
        pestExchangeDeskX = mc.thePlayer.getPosition().getX();
        pestExchangeDeskY = mc.thePlayer.getPosition().getY();
        pestExchangeDeskZ = mc.thePlayer.getPosition().getZ();
        LogUtils.sendSuccess("[Auto Pest Exchange] Set the pest exchange location to "
                + FarmHelperConfig.pestExchangeDeskX + ", "
                + FarmHelperConfig.pestExchangeDeskY + ", "
                + FarmHelperConfig.pestExchangeDeskZ);
         */
    };
    public static Runnable resetPestExchangeLocation = () -> {
        //TODO:
        /*
        pestExchangeDeskX = 0;
        pestExchangeDeskY = 0;
        pestExchangeDeskZ = 0;
        LogUtils.sendSuccess("[Auto Pest Exchange] Reset the pest exchange location");
         */
    };
    public static int pestExchangeDeskX = 0;
    public static int pestExchangeDeskY = 0;
    public static int pestExchangeDeskZ = 0;
    public static boolean autoPestExchangeInfo2;
    public static boolean autoGodPot = false;
    public static boolean autoGodPotFromBackpack = true;
    public static boolean autoGodPotStorageType = true;
    public static int autoGodPotBackpackNumber = 1;
    public static boolean autoGodPotFromBits = false;
    public static boolean autoGodPotFromAH = false;
    private static int godPotInfo;
    public static boolean autoSellInfo;
    public static boolean enableAutoSell = false;
    public static boolean autoSellMarketType = false;
    public static boolean autoSellSacks = false;
    public static boolean autoSellSacksPlacement = true;
    public static boolean pauseAutoSellDuringJacobsContest = false;
    public static int inventoryFullTime = 6;
    public static int inventoryFullRatio = 65;
    Runnable autoSellFunction = () -> {
        //TODO:
        /*
        PlayerUtils.closeScreen();
        AutoSell.getInstance().enable(true);
         */
    };
    public static boolean autoSellRunes = true;
    public static boolean autoSellDeadBush = true;
    public static boolean autoSellIronHoe = true;
    public static boolean autoSellPestVinyls = true;
    public static String autoSellCustomItems = "";
    public static boolean autoPestRepellent = false;
    public static boolean pestRepellentType = true;
    public static boolean pauseAutoPestRepellentDuringJacobsContest = false;
    Runnable resetFailsafe = () -> {
        //TODO:
        //AutoRepellent.repellentFailsafeClock.schedule(0);
    };
    public static boolean autoSprayonator = false;
    public static int autoSprayonatorSprayMaterial = 0;
    public static int autoSprayonatorAdditionalDelay = 500;
    public static boolean autoSprayonatorAutoBuyItem = false;
    public static int autoSprayonatorAutoBuyAmount = 1;
    public static int autoSprayonatorStartDelay = 0;
    public static boolean autoComposterInfo1;
    public static boolean autoComposter = false;
    public static boolean pauseAutoComposterDuringJacobsContest = true;
    public static int autoComposterMinMoney = 2_000;
    public static float autoComposterMaxSpendLimit = 1.5f;
    public static int autoComposterOrganicMatterLeft = 30_000;
    public static int autoComposterFuelLeft = 15_000;
    public static boolean autoComposterAutosellBeforeFilling = false;
    public static boolean autoComposterTravelMethod = false;
    public static boolean autoComposterTravelMethodInfo;
    public static boolean logAutoComposterEvents = true;
    public static boolean highlightComposterLocation = true;
    public static boolean autoComposterInfo;
    public static void triggerManuallyAutoComposter() {
        //TODO:
        //AutoComposter.getInstance().setManuallyStarted(true);
        //AutoComposter.getInstance().start();
    }
    public static Runnable setComposterLocation = () -> {
        //TODO:
        /*
        if (!PlayerUtils.isInBarn()) {
            LogUtils.sendError("[Auto Composter] You need to be in the barn to set the Composter location!");
            return;
        }
        composterX = mc.thePlayer.getPosition().getX();
        composterY = mc.thePlayer.getPosition().getY();
        composterZ = mc.thePlayer.getPosition().getZ();
        LogUtils.sendSuccess("[Auto Composter] Set the Composter location to "
                + FarmHelperConfig.composterX + ", "
                + FarmHelperConfig.composterY + ", "
                + FarmHelperConfig.composterZ);
         */
    };
    public static Runnable resetComposterLocation = () -> {
        composterX = 0;
        composterY = 0;
        composterZ = 0;
        LogUtils.sendSuccess("[Auto Composter] Reset the Composter location");
    };
    public static int composterX = 0;
    public static int composterY = 0;
    public static int composterZ = 0;
    public static boolean autoComposterInfo2;
    public static float timeBetweenChangingRows = 400f;
    public static float randomTimeBetweenChangingRows = 200f;
    public static boolean customRowChangeDelaysDuringJacob = false;
    public static float timeBetweenChangingRowsDuringJacob = 400f;
    public static float randomTimeBetweenChangingRowsDuringJacob = 200f;
    public static float rotationTime = 500f;
    public static float rotationTimeRandomness = 300;
    public static boolean customRotationDelaysDuringJacob = false;
    public static float rotationTimeDuringJacob = 500f;
    public static float rotationTimeRandomnessDuringJacob = 300;
    public static float flyPathExecutionerRotationTime = 500f;
    public static float flyPathExecutionerRotationTimeRandomness = 300;
    public static float pestsKillerStuckTime = 3;
    public static int pestsKillerTicksOfNotSeeingPestWhileAttacking = 100;
    public static float macroGuiDelay = 400f;
    public static float macroGuiDelayRandomness = 350f;
    public static float plotCleaningHelperRotationTime = 50;
    public static float plotCleaningHelperRotationTimeRandomness = 50;
    public static float rewarpDelay = 400f;
    public static float rewarpDelayRandomness = 350f;
    public static boolean streamerMode = false;
    public static boolean streamerModeInfo;
    public static boolean streamerModeInfo2;
    //TODO: public static StatusHUD statusHUD = new StatusHUD();
    public static boolean showStatusHudOutsideGarden = false;
    public static boolean countRNGToProfitCalc = false;
    public static boolean resetStatsBetweenDisabling = false;
    public static boolean profitCalcCountPestDrop = false;
    //TODO: public static ProfitCalculatorHUD profitHUD = new ProfitCalculatorHUD();
    public static boolean colourCode24H = true;
    public static boolean showStats24H = true;
    public static boolean showStats7D = false;
    public static boolean longTermUserStats = false;
    public static boolean usageStatsInfo;
    public static boolean showStats30D = false;
    public static boolean showStatsLifetime = true;
    public static boolean showStatsTitle = false;
    //TODO: public static UsageStatsHUD UsageStatsHUD = new UsageStatsHUD();
    //TODO: public static OneKeyBind debugKeybind = new OneKeyBind(Keyboard.KEY_NONE);
    public static boolean debugMode = false;
    public static boolean debugNewFly = true;
    //TODO: public static DebugHUD debugHUD = new DebugHUD();
    public static boolean fastBreak = false;
    public static boolean fastBreakRandomization = false;
    public static int fastBreakRandomizationChance = 5;
    public static boolean fastBreakWarning;
    public static int fastBreakSpeed = 1;
    public static boolean disableFastBreakDuringBanWave = true;
    public static boolean disableFastBreakDuringJacobsContest = true;
    public static boolean autoSwitchTool = true;
    public static boolean profitCalculatorCultivatingEnchant = true;
    public static boolean jacobContestCurrentCropsOnly = true;
    public static boolean showDebugLogsAboutPDOTT = false;
    public static int configVersion = 6;
    public static boolean shownWelcomeGUI2 = false;

    public FarmHelperConfig(){
    }


    public static void addRewarp() {
        //TODO:
        /*
        if (FarmHelperConfig.rewarpList.stream().anyMatch(rewarp -> rewarp.isTheSameAs(BlockUtils.getRelativeBlockPos(0, 0, 0)))) {
            LogUtils.sendError("Rewarp location has already been set!");
            return;
        }
        Rewarp newRewarp = new Rewarp(BlockUtils.getRelativeBlockPos(0, 0, 0));
        if (newRewarp.getDistance(new BlockPos(PlayerUtils.getSpawnLocation())) < 2) {
            LogUtils.sendError("Rewarp location is too close to the spawn location! You must put it AT THE END OF THE FARM!");
            return;
        }
        rewarpList.add(newRewarp);
        LogUtils.sendSuccess("Added rewarp: " + newRewarp);
        saveRewarpConfig();
         */
    }

    public static void removeRewarp() {
        //TODO:
        /*
        Rewarp closest = null;
        if (rewarpList.isEmpty()) {
            LogUtils.sendError("No rewarp locations set!");
            return;
        }
        double closestDistance = Double.MAX_VALUE;
        for (Rewarp rewarp : rewarpList) {
            double distance = rewarp.getDistance(BlockUtils.getRelativeBlockPos(0, 0, 0));
            if (distance < closestDistance) {
                closest = rewarp;
                closestDistance = distance;
            }
        }
        if (closest != null) {
            rewarpList.remove(closest);
            LogUtils.sendSuccess("Removed the closest rewarp: " + closest);
            saveRewarpConfig();
        }
         */
    }

    public static void removeAllRewarps() {
        rewarpList.clear();
        LogUtils.sendSuccess("Removed all saved rewarp positions");
        saveRewarpConfig();
    }

    public static void saveRewarpConfig() {
        //TODO:
        /*
        try {
            if (!configRewarpFile.exists()) {
                Files.createFile(configRewarpFile.toPath());
            }

            Files.write(configRewarpFile.toPath(), FarmHelper.gson.toJson(rewarpList).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    public static MacroEnum getMacro() {
        return MacroEnum.values()[macroType];
    }

    public static long getRandomTimeBetweenChangingRows() {
        //TODO:
        /*
        if (customRowChangeDelaysDuringJacob && GameStateHandler.getInstance().inJacobContest()) {
            return (long) (timeBetweenChangingRowsDuringJacob + (float) Math.random() * randomTimeBetweenChangingRowsDuringJacob);
        }
        return (long) (timeBetweenChangingRows + (float) Math.random() * randomTimeBetweenChangingRows);
         */
        return 0;
    }

    public static long getMaxTimeBetweenChangingRows() {
        return (long) (timeBetweenChangingRows + randomTimeBetweenChangingRows);
    }

    public static long getRandomRotationTime() {
        //TODO:
        /*
        if (customRotationDelaysDuringJacob && GameStateHandler.getInstance().inJacobContest()) {
            return (long) (rotationTimeDuringJacob + (float) Math.random() * rotationTimeRandomnessDuringJacob);
        }
        return (long) (rotationTime + (float) Math.random() * rotationTimeRandomness);
         */
        return 0;
    }

    public static long getRandomFlyPathExecutionerRotationTime() {
        return (long) (flyPathExecutionerRotationTime + (float) Math.random() * flyPathExecutionerRotationTimeRandomness);
    }

    public static long getRandomGUIMacroDelay() {
        return (long) (macroGuiDelay + (float) Math.random() * macroGuiDelayRandomness);
    }

    public static long getRandomPlotCleaningHelperRotationTime() {
        return (long) (plotCleaningHelperRotationTime + (float) Math.random() * plotCleaningHelperRotationTimeRandomness);
    }

    public static long getRandomRewarpDelay() {
        return (long) (rewarpDelay + (float) Math.random() * rewarpDelayRandomness);
    }

    public String getJson() {
        //TODO:
        /*
        String json = gson.toJson(this);
        if (json == null || json.equals("{}")) {
            json = nonProfileSpecificGson.toJson(this);
        }
        return json;
         */
        return "";
    }

    public static void checkForUpdate() {
        //TODO:
        /*
        AutoUpdaterGUI.checkedForUpdates = true;
        AutoUpdaterGUI.shownGui = false;
        AutoUpdaterGUI.getLatestVersion();
        if (AutoUpdaterGUI.isOutdated) {
            LogUtils.sendWarning("You are using an outdated version! The latest version is " + AutoUpdaterGUI.latestVersion + "!");
            AutoUpdaterGUI.showGUI();
        } else {
            LogUtils.sendSuccess("You are using the latest version!");
        }
         */
    }

    public enum MacroEnum {
        S_V_NORMAL_TYPE,
        S_PUMPKIN_MELON,
        S_PUMPKIN_MELON_MELONGKINGDE,
        S_PUMPKIN_MELON_DEFAULT_PLOT,
        S_SUGAR_CANE,
        S_CACTUS,
        S_CACTUS_SUNTZU,
        S_COCOA_BEANS,
        S_COCOA_BEANS_TRAPDOORS,
        S_COCOA_BEANS_LEFT_RIGHT,
        S_MUSHROOM,
        S_MUSHROOM_ROTATE,
        S_MUSHROOM_SDS,
        C_NORMAL_TYPE
    }

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