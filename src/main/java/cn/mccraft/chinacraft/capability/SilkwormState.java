package cn.mccraft.chinacraft.capability;

public enum SilkwormState {
    EGG(10000), LARVA(30000), COCOON_1(15000), ADULT(10000), DEAD(-1);
    private int duration;

    /**
     * The state of a silkworm.
     * @param duration duration in ticks
     */
    SilkwormState(int duration) {
        this.duration = duration;
    }
}
