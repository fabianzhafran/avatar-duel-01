package com.avatarduel.Card;

public class ElementPower {
    private Element element;
    private int currentPow;
    private int maxPow;

    public ElementPower(Element element) {
        this.element = element;
        currentPow = 0;
        maxPow = 10;
    }
}