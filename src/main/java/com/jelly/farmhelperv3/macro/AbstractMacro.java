package com.jelly.farmhelperv3.macro;

import com.jelly.farmhelperv3.config.FarmHelperConfig;
import com.jelly.farmhelperv3.handler.GameStateHandler;
import com.jelly.farmhelperv3.util.helper.Clock;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import org.lwjgl.system.APIUtil;

import java.util.Optional;

public abstract class AbstractMacro{
    public static final Minecraft mc = Minecraft.getInstance();
    //private final RotationHandler rotation = RotationHandler.getInstance(); //TODO
    private final Clock rewarpDelay = new Clock();
    private final Clock delayBeforeBreakTime = new Clock();
    private final Clock breakTime = new Clock();

    @Setter
    public State currentState = State.NONE;
    @Setter
    public State previousState = State.NONE;
    @Setter @Getter
    private boolean enabled = false;
    @Setter
    private Optional<SavedState> savedState = Optional.empty();
    @Setter
    private boolean restoredState = false;
    @Setter
    private int layerY = 0;
    private Optional<Float> yaw = Optional.empty();
    private Optional<Float> pitch = Optional.empty();
    @Setter
    private Optional<Float> closest90Deg = Optional.empty();
    @Setter
    private boolean rotated = false;
    @Setter
    private RewarpState rewarpState = RewarpState.NONE;
    @Setter
    private WalkingDirection walkingDirection = WalkingDirection.X;
    @Setter
    private int previousWalkingCoord = 0;

    public boolean isEnabledAndNoFeature(){
        //return enabled && !FeatureManager.getInstance().shouldPauseMacroExecution(); //TODO
        return false;
    }

    public boolean isPaused(){
        return !enabled;
    }

    private final Clock checkOnSpawnClock = new Clock();

    private boolean sentWarning = false;

    public boolean isYawSet(){
        return yaw.isPresent();
    }

    public boolean isPitchSet(){
        return pitch.isPresent();
    }

    public float getYaw(){
        return yaw.orElse(0f);
    }

    public float getPitch(){
        return pitch.orElse(0f);
    }

    public void setYaw(float yaw){
        this.yaw = Optional.of(yaw);
    }

    public void setPitch(float pitch){
        this.pitch = Optional.of(pitch);
    }

    public void onTick(){
        //TODO
    }

    public void onLastRender(){ }
    public void onChatMessageRecieved(String msg){ }
    public void onOverlayRender(){ }
    public void onPacketRecieved(){ }

    public abstract void updateState();
    public abstract void invokeState();

    public void onEnable(){
        //TODO
    }

    public void onDisable(){
        //TODO
    }

    public void saveState(){
        //TODO
    }

    public void changeState(){
        //TODO
    }

    public abstract void actionAfterTeleport();

    public State calculateDirection(){
        //TODO
        return State.NONE;
    }

    public void doAfterRewarpRotation(){
        //TODO
    }

    protected void setWalkingDirection(){
        //TODO
    }

    public boolean shouldRotateAfterWarp(){
        //TODO
        return false;
    }

    public void setBreakTime(double time, double timeBefore){
        breakTime.schedule((long) time);
        delayBeforeBreakTime.schedule((long) timeBefore);
    }

    public enum State {
        // Add default values like NONE and DROPPING
        // DO NOT REARRAGE, IT WILL BREAK PEST FARMER IF YOU DO - osama
        // Pest farmer doesn't exist, still maybe don't rearrange ;3 - Nils Ritter
        NONE,
        DROPPING,
        SWITCHING_SIDE,
        SWITCHING_LANE,
        LEFT,
        RIGHT,
        BACKWARD,
        FORWARD,

        A,
        D,
        S,
        W
    }

    public enum RewarpState {
        NONE,
        TELEPORTING,
        TELEPORTED,
        POST_REWARP
    }

    public enum WalkingDirection {
        X,
        Z,
    }

    @Getter
    @Setter
    public static class SavedState {
        private State state;
        private float yaw;
        private float pitch;
        private Optional<Float> closest90Deg;
        private FarmHelperConfig.CropEnum crop;

        public SavedState(State state, float yaw, float pitch, float closest90Deg, FarmHelperConfig.CropEnum crop) {
            this.state = state;
            this.yaw = yaw;
            this.pitch = pitch;
            this.closest90Deg = Optional.of(closest90Deg);
            this.crop = crop;
        }

        @Override
        public String toString() {
            return "SavedState{" +
                    "state=" + state +
                    ", yaw=" + yaw +
                    ", pitch=" + pitch +
                    ", closest90Deg=" + closest90Deg +
                    ", crop=" + crop +
                    '}';
        }
    }
}
