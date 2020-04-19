package com.avatarduel.controller;

import com.avatarduel.card.Element;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * CardGuiBuilder is a class that implements the
 * Builder Design Pattern to ease the creation of
 * the visualization of a card in the GUI of the
 * Avatar Duel Game.
 */
public class CardGuiBuilder {
    private String name;
    private String type;
    private String pow;
    private String att;
    private String def;
    private Element elmt;
    private String imgPath;

    /**
     * Set the default Card in the constructor
     */
    public CardGuiBuilder() {
        this.name = "Spirit Oasis";
        this.elmt = Element.WATER;
        this.imgPath = "@/../com/avatarduel/card/image/land/Spirit Oasis.png";
        this.att = "-";
        this.def = "-";
        this.pow = "-";
        this.type = "Land";
    }

    /**
     * Set the name of the builder
     * @param n
     * @return Builder with a name
     */
    public CardGuiBuilder name(String n) {
        this.name = n;
        return this;
    }

    /**
     * Set the power value of the builder
     * @param p
     * @return Builder with a power value
     */
    public CardGuiBuilder pow(int p) {
        this.pow = String.valueOf(p);
        return this;
    }

    /**
     * Set the attack value of the builder
     * @param a
     * @return Builder with a attack value
     */
    public CardGuiBuilder att(int a) {
        this.att = String.valueOf(a);
        return this;
    }

    /**
     * Set the defense value of the builder
     * @param d
     * @return Builder with a defense value
     */
    public CardGuiBuilder def(int d) {
        this.def = String.valueOf(d);
        return this;
    }

    /**
     * Set the element of the builder
     * @param e
     * @return Builder with an element
     */
    public CardGuiBuilder element(Element e) {
        this.elmt = e;
        return this;
    }

    /**
     * Set the image path of the builder
     * @param url
     * @return Builder with a image path
     */
    public CardGuiBuilder imagePath(String url) {
        this.imgPath = url;
        return this;
    }

    /**
     * Set the type of the builder
     * @param t
     * @return Builder with a type
     */
    public CardGuiBuilder type(String t) {
        this.type = t;
        return this;
    }

    /**
     * Build the card visualization
     * @return the card visualization in the form of a Group
     */
    public Group build() {
        Group newCard = new Group();
        Rectangle outer = CardUtils.createRectCard(80, 110, 15, 5, this.elmt);
        Rectangle nameRect = CardUtils.createRectCard(70, 15, 20, 10, this.elmt);
        Rectangle imgRect = CardUtils.createRectCard(70,50, 20, 28, this.elmt);
        Rectangle descRect = CardUtils.createRectCard(70, 30, 20, 80, this.elmt);
        ImageView backCardImg = CardUtils.createImageView("@/../assets/card-background.jpg", 80, 110, 15,5);
        backCardImg.setOpacity(0);

        Label nameLabel = CardUtils.createLabel(this.name, 22, 12, 8);
        nameLabel.setPrefWidth(70);
        ImageView cardImg = CardUtils.createImageView(this.imgPath, 70, 50, 20, 28);
        if (this.type.equals("Land")) {
            Label elmtLabel = CardUtils.createLabel(this.elmt.toString(), 60, 95, 8);
            newCard.getChildren().addAll(outer, nameRect, imgRect, descRect, nameLabel, elmtLabel,cardImg, backCardImg);
        } else {
            Label powLabel = CardUtils.createLabel(this.pow, 45, 92, 10);
            Label attLabel = CardUtils.createLabel(this.att, 45, 80, 10);
            Label defLabel = CardUtils.createLabel(this.def, 78, 80, 10);
            Label elmtLabel = CardUtils.createLabel(this.elmt.toString(), 55, 95, 8);
            Text powText = CardUtils.createText("Pow:", 22, 103, 10);
            Text attText = CardUtils.createText("Att:", 20, 91, 10);
            Text defText = CardUtils.createText("Def:", 55, 91, 10);
            newCard.getChildren().addAll(outer, nameRect, imgRect, descRect, nameLabel, powLabel, attLabel, defLabel, elmtLabel, powText, attText, defText, cardImg, backCardImg);
        }
        return newCard;
    }
}
