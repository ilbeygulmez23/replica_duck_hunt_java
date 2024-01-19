import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.util.Arrays;
import java.util.List;
 /**
  * Thr BackgroundSelectionScreen class represents the background selection screen of the Duck Hunt game.
  * It extends the StackPane class and contains the configuration and behavior for the title screen.
  */
public class BackgroundSelectionScreen extends StackPane {
    private Stage stage;
    private static final StackPane BackgroundSelectionPane = new StackPane();
    private static final Scene BackgroundSelectionScene = new Scene(BackgroundSelectionPane,(640/3f) * DuckHunt.getSCALE(),(480/3f)* DuckHunt.getSCALE());

    private static final ImageView background1 = new ImageView("assets/background/1.png");
    private static final ImageView foreground1 = new ImageView("assets/foreground/1.png");
    private static final ImageView background2 = new ImageView("assets/background/2.png");
    private static final ImageView foreground2 = new ImageView("assets/foreground/2.png");
    private static final ImageView background3 = new ImageView("assets/background/3.png");
    private static final ImageView foreground3 = new ImageView("assets/foreground/3.png");
    private static final ImageView background4 = new ImageView("assets/background/4.png");
    private static final ImageView foreground4 = new ImageView("assets/foreground/4.png");
    private static final ImageView background5 = new ImageView("assets/background/5.png");
    private static final ImageView foreground5 = new ImageView("assets/foreground/5.png");
    private static final ImageView background6 = new ImageView("assets/background/6.png");
    private static final ImageView foreground6 = new ImageView("assets/foreground/6.png");
    private static ImageView chosenBackground = background1;
    private static ImageView chosenForeground = foreground1;
    List<ImageView> backgrounds = Arrays.asList(background1,background2,background3,background4,background5,background6);
    List<ImageView> foregrounds = Arrays.asList(foreground1,foreground2,foreground3,foreground4,foreground5,foreground6);
    private static final ImageView crosshair1 = new ImageView("assets/crosshair/1.png");
    private static final ImageView crosshair2 = new ImageView("assets/crosshair/2.png");
    private static final ImageView crosshair3 = new ImageView("assets/crosshair/3.png");
    private static final ImageView crosshair4 = new ImageView("assets/crosshair/4.png");
    private static final ImageView crosshair5 = new ImageView("assets/crosshair/5.png");
    private static final ImageView crosshair6 = new ImageView("assets/crosshair/6.png");
    private static final ImageView crosshair7 = new ImageView("assets/crosshair/7.png");
    private static ImageView chosenCrosshair = crosshair1;
    private static final Media introMusic = new Media(new File("assets/effects/Intro.mp3").toURI().toString());
    private static final MediaPlayer introMusicPlayer = new MediaPlayer(introMusic);
    List<ImageView> crosshairs = Arrays.asList(crosshair1,crosshair2,crosshair3,crosshair4,crosshair5,crosshair6,crosshair7);

    /**
     * Constructs a new BackgroundSelectionScreen object.
     * */
    public BackgroundSelectionScreen(){
        // Fit the backgrounds to scene

        for(ImageView background : backgrounds){
            background.setFitWidth(BackgroundSelectionScene.getWidth());
            background.setFitHeight(BackgroundSelectionScene.getHeight());
        }
        for(ImageView foreground : foregrounds){
            foreground.setFitWidth(BackgroundSelectionScene.getWidth());
            foreground.setFitHeight(BackgroundSelectionScene.getHeight());
        }
        // Set the texts
        Text arrowText = new Text("USE ARROW KEYS TO NAVIGATE");
        arrowText.setFill(Color.ORANGE);
        arrowText.setFont(Font.font("Arial", FontWeight.BOLD,20));
        Text enterText = new Text("PRESS ENTER TO START");
        enterText.setFill(Color.ORANGE);
        enterText.setFont(Font.font("Arial", FontWeight.BOLD,20));
        Text escText = new Text("PRESS ESC TO EXIT");
        escText.setFill(Color.ORANGE);
        escText.setFont(Font.font("Arial", FontWeight.BOLD,20));

        StackPane.setAlignment(arrowText, Pos.TOP_CENTER);
        StackPane.setAlignment(enterText, Pos.TOP_CENTER);
        StackPane.setAlignment(escText, Pos.TOP_CENTER);

        crosshair1.setFitHeight((20/3f) * DuckHunt.getSCALE());
        crosshair1.setFitWidth((20/3f) * DuckHunt.getSCALE());
        crosshair2.setFitHeight((20/3f) * DuckHunt.getSCALE());
        crosshair2.setFitWidth((20/3f) * DuckHunt.getSCALE());
        crosshair3.setFitHeight((20/3f) * DuckHunt.getSCALE());
        crosshair3.setFitWidth((20/3f) * DuckHunt.getSCALE());
        crosshair4.setFitHeight((20/3f) * DuckHunt.getSCALE());
        crosshair4.setFitWidth((20/3f) * DuckHunt.getSCALE());
        crosshair5.setFitHeight((20/3f) * DuckHunt.getSCALE());
        crosshair5.setFitWidth((20/3f) * DuckHunt.getSCALE());
        crosshair6.setFitHeight((20/3f) * DuckHunt.getSCALE());
        crosshair6.setFitWidth((20/3f) * DuckHunt.getSCALE());
        crosshair7.setFitHeight((20/3f) * DuckHunt.getSCALE());
        crosshair7.setFitWidth((20/3f) * DuckHunt.getSCALE());


        StackPane.setMargin(arrowText, new Insets((20/3f) * DuckHunt.getSCALE(),0,0,0));
        StackPane.setMargin(enterText, new Insets((40/3f) * DuckHunt.getSCALE(),0,0,0));
        StackPane.setMargin(escText,new Insets((60/3f) * DuckHunt.getSCALE(),0,0,0));

        getBackgroundSelectionPane().getChildren().addAll(background1,foreground1, arrowText, enterText, escText,crosshair1);
        //Adjust background and crosshair with arrow keys
        getBackgroundSelectionPane().setOnKeyPressed(e -> {
            ImageView currentBackground = (ImageView) getBackgroundSelectionPane().getChildren().get(0);
            int currentIndexBackground = backgrounds.indexOf(currentBackground);

            ImageView currentForeground = (ImageView) getBackgroundSelectionPane().getChildren().get(1);
            int currentIndexForeground = foregrounds.indexOf(currentForeground);

            ImageView currentCrosshair = (ImageView) getBackgroundSelectionPane().getChildren().get(5);
            int currentIndexCrosshair = crosshairs.indexOf(currentCrosshair);

            if(e.getCode() == KeyCode.UP){
                int nextIndexCrosshair = (currentIndexCrosshair + 1) % crosshairs.size();
                ImageView nextCrosshair = crosshairs.get(nextIndexCrosshair);
                getBackgroundSelectionPane().getChildren().set(5, nextCrosshair);
                chosenCrosshair =  nextCrosshair;
            }
            else if (e.getCode() == KeyCode.DOWN) {
                int previousIndexCrosshair = (currentIndexCrosshair - 1 + crosshairs.size()) % crosshairs.size();
                ImageView previousCrosshair = crosshairs.get(previousIndexCrosshair);
                getBackgroundSelectionPane().getChildren().set(5, previousCrosshair);
                chosenCrosshair = previousCrosshair;
            }
            else if (e.getCode() == KeyCode.RIGHT) {
                int nextIndexBackground = (currentIndexBackground + 1) % backgrounds.size();
                int nextIndexForeground = (currentIndexForeground + 1) % foregrounds.size();
                ImageView nextBackground = backgrounds.get(nextIndexBackground);
                ImageView nextForeground = foregrounds.get(nextIndexForeground);
                getBackgroundSelectionPane().getChildren().set(0, nextBackground);
                getBackgroundSelectionPane().getChildren().set(1, nextForeground);
                chosenBackground = nextBackground;
                chosenForeground = nextForeground;
            }
            else if (e.getCode() == KeyCode.LEFT) {
                int previousIndexBackground = (currentIndexBackground - 1 + backgrounds.size()) % backgrounds.size();
                int previousIndexForeground = (currentIndexForeground - 1 + foregrounds.size()) % foregrounds.size();
                ImageView previousBackground = backgrounds.get(previousIndexBackground);
                ImageView previousForeground = foregrounds.get(previousIndexForeground);
                getBackgroundSelectionPane().getChildren().set(0, previousBackground);
                getBackgroundSelectionPane().getChildren().set(1, previousForeground);
                chosenBackground = previousBackground;
                chosenForeground = previousForeground;
            }
            else if (e.getCode() == KeyCode.ESCAPE) {
                getBackgroundSelectionPane().getChildren().clear();
                chosenBackground = background1;
                chosenForeground = foreground1;
                chosenCrosshair = crosshair1;
                TitleScreen titleScreen = new TitleScreen();
                titleScreen.setStage(stage);
                TitleScreen.getTitleMusicPlayer().stop();
                TitleScreen.getTitleMusicPlayer().play();
                stage.setScene(TitleScreen.getTitleScreenScene());
            }
            else if (e.getCode() == KeyCode.ENTER) {
                TitleScreen.getTitleMusicPlayer().stop();
                introMusicPlayer.setCycleCount(1);
                introMusicPlayer.setVolume(DuckHunt.getVOLUME());
                introMusicPlayer.play();
                getBackgroundSelectionPane().setDisable(true);
                introMusicPlayer.setOnEndOfMedia(() -> {
                    getBackgroundSelectionPane().setDisable(false);
                    Level1 level1 = new Level1();

                    BackgroundSelectionPane.getChildren().clear();
                    level1.setStage(stage);

                    stage.setScene(level1.getGameScreenScene());
                    introMusicPlayer.stop();
                });

            }
        });
        BackgroundSelectionScreen.getBackgroundSelectionPane().requestFocus();
    }


    public static StackPane getBackgroundSelectionPane() {
        return BackgroundSelectionPane;
    }

    public static Scene getBackgroundSelectionScene() {
        return BackgroundSelectionScene;
    }

    public static ImageView getChosenBackground() {
        return chosenBackground;
    }

    public static ImageView getChosenForeground() {
        return chosenForeground;
    }

    public static ImageView getChosenCrosshair() {
        return chosenCrosshair;
    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
