package com.example.lab6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        //images
        Image imageParDefault = new Image("file:default.jpg");
        Image image1 = new Image("file:image1.jpg");
        Image image2 = new Image("file:image2.jpg");
        Image image3 = new Image("file:image3.jpg");

        ImageView imageViewDefault = new ImageView(imageParDefault);
        imageViewDefault.setFitWidth(500);
        imageViewDefault.setFitHeight(500);

        //Menu
        Menu fichiers = new Menu("Fichiers");
        Menu actions = new Menu("Action");
        Menu charger = new Menu("Charger une image");

        MenuItem item1 = new MenuItem("Image #1");
        MenuItem item2 = new MenuItem("Image #2");
        MenuItem item3 = new MenuItem("Image #3");
        MenuItem item4 = new MenuItem("Réinitialiser");

        charger.getItems().addAll(item1, item2, item3);
        fichiers.getItems().add(charger);
        actions.getItems().add(item4);

        MenuBar menuBar = new MenuBar(fichiers, actions);
        ContextMenu contextMenu = new ContextMenu(fichiers,actions);

        //Toolips
        Tooltip tooltip1 = new Tooltip("Rend l'image plus claire ou plus sombre");
        Tooltip tooltip2 = new Tooltip("Diminue ou augmente la différence entre les couleurs");
        Tooltip tooltip3 = new Tooltip("Change la teinte (couleur) de l'image");
        Tooltip tooltip4 = new Tooltip("Diminue ou augmente l'intensité des couleurs");

        //Sliders
        Label lum = new Label("Luminosité");
        Slider luminosite = new Slider(-1, 1, 0);
        luminosite.setTooltip(tooltip1);
        VBox vBox1 = new VBox(lum, luminosite);
        vBox1.setSpacing(10);
        vBox1.setAlignment(Pos.CENTER);

        Label cont = new Label("Contraste");
        Slider contraste = new Slider(-1, 1, 0);
        contraste.setTooltip(tooltip2);
        VBox vBox2 = new VBox(cont, contraste);
        vBox2.setSpacing(10);
        vBox2.setAlignment(Pos.CENTER);

        Label tein = new Label("Teinte");
        Slider teinte = new Slider(-1, 1, 0);
        teinte.setTooltip(tooltip3);
        VBox vBox3 = new VBox(tein, teinte);
        vBox3.setSpacing(10);
        vBox3.setAlignment(Pos.CENTER);

        Label sat = new Label("Saturation");
        Slider saturation = new Slider(-1, 1, 0);
        saturation.setTooltip(tooltip4);
        VBox vBox4 = new VBox(sat, saturation);
        vBox4.setSpacing(10);
        vBox4.setAlignment(Pos.CENTER);

        VBox sliders = new VBox(vBox1, vBox2, vBox3, vBox4);
        sliders.setSpacing(30);
        sliders.setAlignment(Pos.CENTER);
        sliders.setPadding(new Insets(150));

        //texte du base
        Label label = new Label("Bienvenue dans le modificateur d'images!");
        HBox hBoxLabel = new HBox(label);
        hBoxLabel.setBackground(new Background(new BackgroundFill((Paint)Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        //set on action des sliders
        ColorAdjust colorAdjust1 = new ColorAdjust();

        luminosite.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust1.setBrightness(newValue.doubleValue());
            imageViewDefault.setEffect(colorAdjust1);
        });

        contraste.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust1.setContrast(newValue.doubleValue());
            imageViewDefault.setEffect(colorAdjust1);
        });

        teinte.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust1.setHue(newValue.doubleValue());
            imageViewDefault.setEffect(colorAdjust1);
        });

        saturation.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust1.setSaturation(newValue.doubleValue());
            imageViewDefault.setEffect(colorAdjust1);
        });

        //images
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(imageViewDefault);
        borderPane.setRight(sliders);
        borderPane.setBottom(hBoxLabel);
        borderPane.getCenter().setOnContextMenuRequested( event->
                contextMenu.show(borderPane, event.getScreenX(), event.getScreenY())
        );

        //set on action des items
        item1.setOnAction(event -> {
            imageViewDefault.setImage(image1);
            label.setText("Image 1 changée");
        });

        item2.setOnAction(event -> {
            imageViewDefault.setImage(image2);
            label.setText("Image 2 changée");
        });

        item3.setOnAction(event -> {
            imageViewDefault.setImage(image3);
            label.setText("Image 3 changée");
        });

        item4.setOnAction(event -> {
            borderPane.setCenter(imageViewDefault);
            luminosite.setValue(0);
            contraste.setValue(0);
            teinte.setValue(0);
            saturation.setValue(0);
            label.setText("Réinitialisation des ajustements de couleurs");
        });

        //scene
        Scene scene = new Scene(borderPane);
        stage.setTitle("Laboratoire 5");
        stage.show();
        stage.setScene(scene);
        stage.setFullScreen(true);
    }
}