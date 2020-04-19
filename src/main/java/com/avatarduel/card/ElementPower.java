package com.avatarduel.card;

/**
 * ElementPower is used to store an element power
 * that is owned by a player.
 */
public class ElementPower {
    private Element element;
    /**
     * Current power that can be used for a turn.
     */
    private int currentPow;
    /**
     * Maximum power that a player have. currentPow will
     * be refreshed to maxPow at the start of each turn.
     */
    private int maxPow;

    /**
     * Construct ElementPower with 0 power.
     * @param element
     */
    public ElementPower(Element element) {
        this.element = element;
        currentPow = 0;
        maxPow = 0;
    }

    /**
     * @return the element.
     */
    public Element getElement() {
        return element;
    }

    /**
     * @return current power.
     */
    public int getCurrentPow() {
        return currentPow;
    }

    /**
     * Set current power to the parameter given
     * @param updatePow
     */
    public void setCurrentPow(int updatePow) {
        currentPow = updatePow;
    }

    /**
     * @return maximum power.
     */
    public int getMaxPow() {
        return maxPow;
    }

    /**
     * Set maximum power to the parameter given
     * @param updatePow
     */
    public void setMaxPow(int updatePow) {
        maxPow = updatePow;
    }
}