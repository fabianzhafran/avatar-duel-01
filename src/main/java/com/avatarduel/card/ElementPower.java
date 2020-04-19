package com.avatarduel.Card;

public class ElementPower {
    private Element element;
    private int currentPow;
    private int maxPow;

    public ElementPower(Element element) {
        this.element = element;
        currentPow = 0;
        maxPow = 0;
    }

    public Element getElement() {
        return element;
    }

    public int getCurrentPow() {
        return currentPow;
    }

    public void setCurrentPow(int updatePow) {
        currentPow = updatePow;
    }

    public int getMaxPow() {
        return maxPow;
    }

    public void setMaxPow(int updatePow) {
        maxPow = updatePow;
    }
}