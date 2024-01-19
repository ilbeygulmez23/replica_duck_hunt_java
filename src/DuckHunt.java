import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**

 The DuckHunt class is responsible for setting up and displaying the main stage of the application.

 It extends the Application class from the JavaFX library.
 */
public class DuckHunt extends Application {
    private static final double SCALE = 4; // The scale factor for game elements
    private static final double VOLUME = 0; // The volume level for game sounds
    /**

     The start method is called when the application is launched and is responsible for initializing and displaying the main stage.

     @param stage The primary stage of the application.
     */
    public void start(Stage stage) {
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.setStage(stage);

        stage.setScene(TitleScreen.getTitleScreenScene());
        stage.setTitle("HUBBM Duck Hunt");
        stage.getIcons().add(TitleScreen.getFaviconI());
        stage.setResizable(false);
        stage.show();
    }
    /**

     The TextBlinker method animates the visibility of the specified Text object, making it blink.
     @param text The Text object to be animated.
     */
    public static void TextBlinker(Text text) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), new KeyValue(text.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(1.0), new KeyValue(text.visibleProperty(), true)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(text.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(1.0), new KeyValue(text.visibleProperty(), true))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
    }
    /**

     Retrieves the scale factor used in the game.
     @return The scale factor.
     */
    public static double getSCALE() {
        return SCALE;
    }
    /**

     Retrieves the volume level used in the game.
     @return The volume level.
     */
    public static double getVOLUME() {
        return VOLUME;
    }
    /**

     The main method is the entry point of the application.
     @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
