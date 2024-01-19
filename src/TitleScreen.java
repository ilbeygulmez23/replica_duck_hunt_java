import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;

/**
 * The TitleScreen class represents the title screen of the Duck Hunt game.
 * It extends the StackPane class and contains the configuration and behavior for the title screen.
 */
public class TitleScreen extends StackPane {
    private Stage stage;
    private static final StackPane TitleScreenPane = new StackPane();
    private static final Scene TitleScreenScene = new Scene(TitleScreenPane, (640 / 3f) * DuckHunt.getSCALE(), (480 / 3f) * DuckHunt.getSCALE());
    private static final Image faviconI = new Image("assets/favicon/1.png");
    private static final ImageView favicon = new ImageView("assets/favicon/1.png");
    private static final Media titleMusic = new Media(new File("assets/effects/Title.mp3").toURI().toString());
    private static final MediaPlayer titleMusicPlayer = new MediaPlayer(titleMusic);
    private static final MediaView mediaView = new MediaView(titleMusicPlayer);

    /**
     * Constructs a new TitleScreen object.
     */
    public TitleScreen() {
        favicon.setFitWidth(TitleScreenScene.getWidth());
        favicon.setFitHeight(TitleScreenScene.getHeight());

        Text playText = new Text("PRESS ENTER TO PLAY");
        playText.setFill(Color.ORANGE);
        Text exitText = new Text("PRESS ESC TO EXIT");
        exitText.setFill(Color.ORANGE);
        playText.setFont(Font.font("Arial", FontWeight.BOLD, (40 / 3f) * DuckHunt.getSCALE()));
        exitText.setFont(Font.font("Arial", FontWeight.BOLD, (40 / 3f) * DuckHunt.getSCALE()));

        // Blink the texts
        DuckHunt.TextBlinker(playText);
        DuckHunt.TextBlinker(exitText);

        StackPane.setAlignment(playText, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(exitText, Pos.BOTTOM_CENTER);
        StackPane.setMargin(playText, new Insets(0, 0, ((TitleScreenScene.getHeight() / 3f) / 3f) * DuckHunt.getSCALE(), 0));
        StackPane.setMargin(exitText, new Insets(0, 0, ((TitleScreenScene.getHeight() / 3f - 40) / 3f) * DuckHunt.getSCALE(), 0));

        titleMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        titleMusicPlayer.setVolume(DuckHunt.getVOLUME());
        titleMusicPlayer.play();

        TitleScreenPane.getChildren().addAll(favicon, playText, exitText, mediaView);

        TitleScreenPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                BackgroundSelectionScreen backgroundSelectionScreen = new BackgroundSelectionScreen();
                TitleScreenPane.getChildren().clear();
                backgroundSelectionScreen.setStage(stage);

                stage.setScene(BackgroundSelectionScreen.getBackgroundSelectionScene());
            } else if (e.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            }
        });
        TitleScreen.getTitleScreenPane().requestFocus();
    }

    /**
     * Returns the stack pane of the title screen.
     * @return the stack pane of the title screen
     */
    public static StackPane getTitleScreenPane() {
        return TitleScreenPane;
    }

    /**
     * Returns the scene of the title screen.
     * @return the scene of the title screen
     */
    public static Scene getTitleScreenScene() {
        return TitleScreenScene;
    }

    /**
     * Returns the favicon image of the title screen.
     * @return the favicon image of the title screen
     */
    public static Image getFaviconI() {
        return faviconI;
    }

    /**
     * Sets the stage for the title screen.
     * @param stage the stage to set
     */


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Returns the media player for the title music.
     * @return the media player for the title music
     */
    public static MediaPlayer getTitleMusicPlayer() {
        return titleMusicPlayer;
    }
}