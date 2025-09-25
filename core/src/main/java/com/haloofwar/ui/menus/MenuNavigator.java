package com.haloofwar.ui.menus;

public class MenuNavigator {
    private final float SELECTOR_COOLDOWN_MAX;  // segundos
    private final int OPTION_COUNT;

    private int selectedIndex = 0;
    private float selectorCooldown = 0f;  // ahora es float (segundos)

    public MenuNavigator(int optionCount, float selectorCooldownMax) {
        this.OPTION_COUNT = optionCount;
        this.SELECTOR_COOLDOWN_MAX = selectorCooldownMax;
    }

    public boolean canMove() {
        return this.selectorCooldown <= 0f;
    }

    public void updateCooldown(float delta) {
        if (this.selectorCooldown > 0f) {
            this.selectorCooldown -= delta;
            if (this.selectorCooldown < 0f) this.selectorCooldown = 0f;
        }
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

    public void forceCooldown(float value) {
        this.selectorCooldown = value;
    }

    public boolean isSelectedIndex(int index) {
        return this.selectedIndex == index;
    }
}
