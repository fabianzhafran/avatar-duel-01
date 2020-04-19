package com.avatarduel.card;

import java.util.Hashtable;

/**
 * ElementDictionary is a class that maps an element
 * in the form of a string to that element in the form of
 * Element enum.
 */
public class ElementDictionary {
    private Hashtable<String, Element> elementDictionary;

    public ElementDictionary() {
        elementDictionary = new Hashtable<String, Element>();
        elementDictionary.put("WATER", Element.WATER);
        elementDictionary.put("FIRE", Element.FIRE);
        elementDictionary.put("AIR", Element.AIR);
        elementDictionary.put("EARTH", Element.EARTH);
        elementDictionary.put("ENERGY", Element.ENERGY);
    }

    public Element getElement(String elementString) {
        return elementDictionary.get(elementString);
    }
}