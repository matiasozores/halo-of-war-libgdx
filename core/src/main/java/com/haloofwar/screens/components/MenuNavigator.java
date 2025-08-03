package com.haloofwar.screens.components;

public class MenuNavigator {
    private final int SELECTOR_COOLDOWN_MAX;
    private final int OPTION_COUNT;

    private int selectedIndex = 0;
    private int selectorCooldown = 0;

    public MenuNavigator(int optionCount, int selectorCooldownMax) {
        this.OPTION_COUNT = optionCount;
        this.SELECTOR_COOLDOWN_MAX = selectorCooldownMax;
    }

    public boolean canMove() {
        return this.selectorCooldown <= 0;
    }

    public void updateCooldown() {
        if (this.selectorCooldown > 0) this.selectorCooldown--;
    }

    public void moveUp() {
        this.selectedIndex = (this.selectedIndex - 1 + this.OPTION_COUNT) % this.OPTION_COUNT;
        this.selectorCooldown = this.SELECTOR_COOLDOWN_MAX;
    }

    public void moveDown() {
        this.selectedIndex = (this.selectedIndex + 1) % this.OPTION_COUNT;
        this.selectorCooldown = this.SELECTOR_COOLDOWN_MAX;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public void resetCooldown() {
        this.selectorCooldown = this.SELECTOR_COOLDOWN_MAX;
    }
    
    public void forceCooldown(int value) {
        this.selectorCooldown = value;
    }

    public boolean isSelectedIndex(int index) {
        return this.selectedIndex == index;
    }

}
