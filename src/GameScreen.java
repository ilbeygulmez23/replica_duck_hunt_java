import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;


/**
 * The GameScreen class represents the game screen of the Duck Hunt game.
 * It extends the Pane class and contains the configuration and behavior for the game screen.
 * */
public class GameScreen extends Pane {
    private Stage stage;
    private int level;
    private Text levelText;
    private final Pane GameScreenPane = new Pane();
    private final Scene GameScreenScene = new Scene(GameScreenPane,(640/3f) * DuckHunt.getSCALE(),(480/3f)* DuckHunt.getSCALE());
    private int duckCount;
    private int ammo = duckCount * 3;
    private Text ammoText = new Text("Ammo Left: " + ammo);
    private boolean levelEnded = false;
    private boolean levelCompleted = false;
    private static final ImageView elGato = new ImageView("https://static.wikia.nocookie.net/evade-nextbot/images/0/0e/Evade_El_Gato.png.png/revision/latest?cb=20230411113128");


    private final Text youWinText = new Text("YOU WIN!");
    private final Text nextText = new Text("Press ENTER to play next level");
    private final Media levelCompletedSound = new Media(new File("assets/effects/LevelCompleted.mp3").toURI().toString());
    private final MediaPlayer levelCompletedSoundPlayer = new MediaPlayer(levelCompletedSound);
    private final Media gameCompletedSound = new Media(new File("assets/effects/GameCompleted.mp3").toURI().toString());
    private final MediaPlayer gameCompletedSoundPlayer = new MediaPlayer(gameCompletedSound);

    /**
     * Constructs a new GameScreen object.
     */
    public GameScreen(){
        elGato.setFitWidth(getGameScreenScene().getWidth() * DuckHunt.getSCALE()/20);
        elGato.setFitHeight(getGameScreenScene().getHeight() * DuckHunt.getSCALE()/15);

        elGato.setLayoutX(500 * DuckHunt.getSCALE() / 3f);
        elGato.setLayoutY(250 * DuckHunt.getSCALE() / 3f);
        // Set the crosshair
        Pane crosshairPane = new Pane();
        crosshairPane.getChildren().add(BackgroundSelectionScreen.getChosenCrosshair());

        BackgroundSelectionScreen.getChosenCrosshair().setFitWidth((20/3f) * DuckHunt.getSCALE());
        BackgroundSelectionScreen.getChosenCrosshair().setFitHeight((20/3f) * DuckHunt.getSCALE());
        getGameScreenScene().setCursor(Cursor.NONE);
        getGameScreenScene().setOnMouseMoved(event -> {
            // Update the position of the crosshair image within crosshairPane
            BackgroundSelectionScreen.getChosenCrosshair().setLayoutX(event.getX() - BackgroundSelectionScreen.getChosenCrosshair().getFitWidth()/2);
            BackgroundSelectionScreen.getChosenCrosshair().setLayoutY(event.getY() - BackgroundSelectionScreen.getChosenCrosshair().getFitHeight()/2);
        });
        // Set the background
        BackgroundSelectionScreen.getChosenBackground().setFitWidth(GameScreenScene.getWidth());
        BackgroundSelectionScreen.getChosenBackground().setFitHeight(GameScreenScene.getHeight());

        setEndTexts();

        GameScreenPane.getChildren().addAll(BackgroundSelectionScreen.getChosenBackground(),elGato,BackgroundSelectionScreen.getChosenForeground(),crosshairPane);

        getGameScreenPane().setOnKeyPressed(e -> {
            getGameScreenPane().setMouseTransparent(false);
            if(e.getCode() == KeyCode.ENTER){
                if(isLevelCompleted()){
                    levelCompletedSoundPlayer.stop();
                    this.GameScreenPane.getChildren().clear();
                    if(this.getLevel() == 1){
                        Level2 level2 = new Level2();
                        level2.setStage(stage);
                        stage.setScene(level2.getGameScreenScene());
                    }
                    else if (this.getLevel() == 2) {
                        Level3 level3 = new Level3();
                        level3.setStage(stage);
                        stage.setScene(level3.getGameScreenScene());
                    } else if (this.getLevel() == 3) {
                        Level4 level4 = new Level4();
                        level4.setStage(stage);
                        stage.setScene(level4.getGameScreenScene());

                    } else if (this.getLevel() == 4) {
                        Level5 level5 = new Level5();
                        level5.setStage(stage);
                        stage.setScene(level5.getGameScreenScene());

                    } else if (this.getLevel() == 5) {
                        Level6 level6 = new Level6();
                        level6.setStage(stage);
                        stage.setScene(level6.getGameScreenScene());

                    } else if (this.getLevel() == 6) {
                        BackgroundSelectionScreen.getBackgroundSelectionPane().getChildren().clear();
                        getGameScreenPane().getChildren().clear();
                        Level1 level1 = new Level1();
                        level1.setStage(stage);
                        stage.setScene(level1.getGameScreenScene());
                        getGameCompletedSoundPlayer().stop();
                    }


                }
            } else if (e.getCode() == KeyCode.ESCAPE) {
                    BackgroundSelectionScreen.getBackgroundSelectionPane().getChildren().clear();
                    getGameCompletedSoundPlayer().stop();
                    getGameScreenPane().getChildren().clear();
                    setTitleScreen();


            }
        });
        getGameScreenPane().requestFocus();
    }


    public Pane getGameScreenPane() {
        return GameScreenPane;
    }

    public Scene getGameScreenScene() {
        return GameScreenScene;
    }

    public Text getAmmoText() {
        return ammoText;
    }

    public void setAmmoText(Text ammoText) {
        this.ammoText = ammoText;
    }

    public int getAmmo() {
        return ammo;
    }
    /**
     * The method sets the ammo and update the ammo text.
     * @param ammo is the desired ammo.
     * */
    public void setAmmo(int ammo) {
        if(ammo >= 0){ this.ammo = ammo;
            setAmmoText(new Text("Ammo Left: " + getAmmo()));

            ammoText.setFill(Color.ORANGE);
            ammoText.setFont(Font.font("Arial", FontWeight.BOLD,(20/3f) * DuckHunt.getSCALE()));
            double ammoTextWidth = ammoText.getLayoutBounds().getWidth();
            double ammoTextHeight = ammoText.getLayoutBounds().getHeight();
            ammoText.setLayoutX(GameScreenPane.getWidth() - ammoTextWidth);
            ammoText.setLayoutY(ammoTextHeight);}

    }
    public void setLevelEnded(boolean levelEnded) {
        this.levelEnded = levelEnded;
    }

    public boolean isLevelCompleted() {
        return levelCompleted;
    }

    public void setLevelCompleted(boolean levelCompleted) {
        this.levelCompleted = levelCompleted;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Text getLevelText() {
        return levelText;
    }

    public void setLevelText(Text levelText) {
        this.levelText = levelText;
    }

    public Text getYouWinText() {
        return youWinText;
    }

    public Text getNextText() {
        return nextText;
    }

    public int getDuckCount() {
        return duckCount;
    }

    public void setDuckCount(int duckCount) {
        this.duckCount = duckCount;
    }

    public void minusDuckCount(int duckCount) {
        this.duckCount -= duckCount;
    }

    public MediaPlayer getLevelCompletedSoundPlayer() {
        return levelCompletedSoundPlayer;
    }

    public MediaPlayer getGameCompletedSoundPlayer() {
        return gameCompletedSoundPlayer;
    }
    /**
     * The method that arranges the information texts in the game screen.
     * @param level is current level.
     * @param duckCount is the number of ducks in that level.
     * */
    public void setInfoTexts(int level, int duckCount){
        setLevel(level);
        setDuckCount(duckCount);
        setLevelText(new Text("Level " + level + "/6"));
        setAmmo(duckCount * 3);

        getLevelText().setFill(Color.ORANGE);
        getLevelText().setFont(Font.font("Arial", FontWeight.BOLD,(20/3f) * DuckHunt.getSCALE()));

        double levelTextWidth = getLevelText().getLayoutBounds().getWidth();
        double levelTextHeight = getLevelText().getLayoutBounds().getHeight();


        getLevelText().setLayoutX((getGameScreenPane().getWidth() - levelTextWidth) / 2);
        getLevelText().setLayoutY(levelTextHeight);
        getGameScreenPane().getChildren().addAll(getLevelText(),getAmmoText());
    }
    /**
     * The method that sets the texts after the level is completed.
     * */
    public void setEndTexts(){
        getYouWinText().setFill(Color.ORANGE);
        getYouWinText().setFont(Font.font("Arial",FontWeight.BOLD,(48/3f) * DuckHunt.getSCALE()));
        getNextText().setFill(Color.ORANGE);
        getNextText().setFont(Font.font("Arial",FontWeight.BOLD, (36/3f) * DuckHunt.getSCALE()));

        //Blink the text
        DuckHunt.TextBlinker(getNextText());

        getYouWinText().setLayoutX(((getGameScreenPane().getWidth() / 2 - getYouWinText().getBoundsInLocal().getWidth() / 2) / 3f) * DuckHunt.getSCALE());
        getYouWinText().setLayoutY((((getGameScreenPane().getHeight() / 2 + getYouWinText().getBoundsInLocal().getHeight() / 4) - 20) / 3f) * DuckHunt.getSCALE() );
        getNextText().setLayoutX(((getGameScreenPane().getWidth() / 2 - getNextText().getBoundsInLocal().getWidth() / 2) / 3f) * DuckHunt.getSCALE());
        getNextText().setLayoutY((((getGameScreenPane().getHeight() / 2 + getNextText().getBoundsInLocal().getHeight() / 4) + 20) / 3f) * DuckHunt.getSCALE());
    }
    /**
     * The method that sets the duck in the desired path
     * @param duck the object representation of the duck
     * @param duckI the ImageView node representation of the duck
     * @param shape the desired path for the duck
     * @param duration the duration of the duck's path
     * @param autoReverse boolean variable that determines if the path will be reversed or not
     * */
    public void setDucktoPath(Duck duck,ImageView duckI, Shape shape, int duration, boolean autoReverse){
        this.getGameScreenPane().getChildren().add(1,duckI);
        this.getGameScreenPane().getChildren().add(shape);

        duck.pt().setDuration(Duration.seconds(duration));
        duck.pt().setPath(shape);
        duck.pt().setNode(duckI);
        duck.pt().setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        duck.pt().setCycleCount(Timeline.INDEFINITE);
        duck.pt().currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.lessThan(oldValue) && duck.isShot()) {
                duckI.setScaleX(-1); // Flip horizontally
            }else{
                duckI.setScaleX(1);
            }
        });
        duck.pt().setAutoReverse(autoReverse);
        duck.pt().play();

    }
    /**
     * The inner method that updates the ammo text.*/
    public void updateAmmoText(){
        this.getGameScreenPane().getChildren().remove(getAmmoText());
        this.setAmmo(this.getAmmo() - 1);
        this.getGameScreenPane().getChildren().add(getAmmoText());
    }
    /**
     * The method that sets the gunshot effect.*/
    public void setGunshot(){
        Media gunShot = new Media(new File("assets/effects/Gunshot.mp3").toURI().toString());
        MediaPlayer gunShotPlayer = new MediaPlayer(gunShot);
        gunShotPlayer.setCycleCount(1);
        gunShotPlayer.setVolume(DuckHunt.getVOLUME());
        gunShotPlayer.play();
        gunShotPlayer.setOnEndOfMedia(gunShotPlayer::stop);
    }
    /**
     * The method that implements the shooting mechanic.
     * @param duckO Object representation of the duck
     * @param duckI ImageView node representation of the duck
     * @param duckShot Image that represents the shot frame of the duck
     * @param duckFalls Image that represents the falling frame of the duck*/
    public void shootDuck(Duck duckO,ImageView duckI,Image duckShot,Image duckFalls){
        if(duckO.isShot()){
            minusDuckCount(1);
        }
        if(getDuckCount() == 0){
            setLevelEnded(true);
            setLevelCompleted(true);
            if(getLevel() == 6){

                getGameCompletedSoundPlayer().play();
                getGameCompletedSoundPlayer().setVolume(DuckHunt.getVOLUME());
                getGameCompletedSoundPlayer().setCycleCount(1);

                Text gameCompletedText = new Text("You have completed the game!");
                Text playAgainCText = new Text("Press ENTER to play again");
                Text escText = new Text("Press ESC to exit");

                gameCompletedText.setFill(Color.ORANGE);
                gameCompletedText.setFont(Font.font("Arial",FontWeight.BOLD,(36 / 3f) * DuckHunt.getSCALE()));
                playAgainCText.setFill(Color.ORANGE);
                playAgainCText.setFont(Font.font("Arial",FontWeight.BOLD, (36 / 3f) * DuckHunt.getSCALE()));
                escText.setFill(Color.ORANGE);
                escText.setFont(Font.font("Arial",FontWeight.BOLD, (36 / 3f) * DuckHunt.getSCALE()));

                //Blink the text
                DuckHunt.TextBlinker(playAgainCText);
                DuckHunt.TextBlinker(escText);

                gameCompletedText.setLayoutX(((getGameScreenPane().getWidth() / 2 - gameCompletedText.getBoundsInLocal().getWidth() / 2) / 3f) * DuckHunt.getSCALE());
                gameCompletedText.setLayoutY((((getGameScreenPane().getHeight() / 2 + gameCompletedText.getBoundsInLocal().getHeight() / 4) - 40) / 3f) * DuckHunt.getSCALE());

                playAgainCText.setLayoutX(((getGameScreenPane().getWidth() / 2 - playAgainCText.getBoundsInLocal().getWidth() / 2) / 3f) * DuckHunt.getSCALE());
                playAgainCText.setLayoutY((((getGameScreenPane().getHeight() / 2 + playAgainCText.getBoundsInLocal().getHeight() / 4)) / 3f) * DuckHunt.getSCALE());

                escText.setLayoutX(((getGameScreenPane().getWidth() / 2 - escText.getBoundsInLocal().getWidth() / 2) / 3f) * DuckHunt.getSCALE());
                escText.setLayoutY((((getGameScreenPane().getHeight() / 2 + escText.getBoundsInLocal().getHeight() / 4) + 40) / 3f) * DuckHunt.getSCALE());

                getGameScreenPane().getChildren().addAll(gameCompletedText,playAgainCText,escText);

            }
            else{
                getLevelCompletedSoundPlayer().setVolume(DuckHunt.getVOLUME());
                getLevelCompletedSoundPlayer().play();
                getLevelCompletedSoundPlayer().setCycleCount(1);

                getGameScreenPane().getChildren().addAll(getYouWinText(),getNextText());
            }
            getGameScreenPane().setMouseTransparent(true);
        }
        TranslateTransition tt = new TranslateTransition(Duration.seconds(1),duckI);
        duckO.setShot(true);
        duckO.pt().stop();
        duckO.getAnimation().stop();
        duckI.setImage(duckShot);
        PauseTransition pauT = new PauseTransition(Duration.seconds(0.4));
        pauT.setOnFinished(ev -> {
            Media duckFallsSound = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
            MediaPlayer duckFallsSoundPlayer = new MediaPlayer(duckFallsSound);
            duckI.setImage(duckFalls);
            duckI.setFitWidth((36/3f) * DuckHunt.getSCALE() );
            duckI.setFitHeight((55/3f) * DuckHunt.getSCALE());
            duckI.getTransforms().add(new Rotate(-duckI.getRotate()));
            tt.setToY((320/3f) * DuckHunt.getSCALE());
            tt.play(); //to be stopped
            duckFallsSoundPlayer.setVolume(DuckHunt.getVOLUME());
            duckFallsSoundPlayer.play(); //to be stopped
            duckFallsSoundPlayer.setCycleCount(1);
        });
        pauT.play();//to be stopped
    }
    /**
     * The method that sets the texts when game is over*/
    public void setGameOver(){
        Media gameOver = new Media(new File("assets/effects/GameOver.mp3").toURI().toString());
        MediaPlayer gameOverPlayer = new MediaPlayer(gameOver);
        gameOverPlayer.setCycleCount(1);
        gameOverPlayer.setVolume(DuckHunt.getVOLUME());
        gameOverPlayer.play();
        setLevelEnded(true);
        Text gameOverText = new Text("GAME OVER!");
        Text playAgainText = new Text("Press ENTER to play again");
        Text escText = new Text("Press ESC to exit");
        gameOverText.setFill(Color.ORANGE);
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD,(36/3f) * DuckHunt.getSCALE()));
        playAgainText.setFill(Color.ORANGE);
        playAgainText.setFont(Font.font("Arial",FontWeight.BOLD,(32/3f) * DuckHunt.getSCALE()));
        escText.setFill(Color.ORANGE);
        escText.setFont(Font.font("Arial",FontWeight.BOLD,(32/3f) * DuckHunt.getSCALE()));

        gameOverText.setLayoutX((((getGameScreenPane().getWidth() / 2 - gameOverText.getBoundsInLocal().getWidth() / 2)) / 3f) * DuckHunt.getSCALE());
        gameOverText.setLayoutY((((getGameScreenPane().getHeight() / 2 + gameOverText.getBoundsInLocal().getHeight() / 4) - 40) / 3f) * DuckHunt.getSCALE());
        playAgainText.setLayoutX((((getGameScreenPane().getWidth() / 2 - playAgainText.getBoundsInLocal().getWidth() / 2)) / 3f) * DuckHunt.getSCALE());
        playAgainText.setLayoutY((((getGameScreenPane().getHeight() / 2 + playAgainText.getBoundsInLocal().getHeight() / 4))/ 3f) * DuckHunt.getSCALE());
        escText.setLayoutX(((getGameScreenPane().getWidth() / 2 - escText.getBoundsInLocal().getWidth() / 2) / 3f) * DuckHunt.getSCALE());
        escText.setLayoutY((((getGameScreenPane().getHeight() / 2 + escText.getBoundsInLocal().getHeight() / 4) + 40) / 3f) * DuckHunt.getSCALE());

        DuckHunt.TextBlinker(playAgainText);
        DuckHunt.TextBlinker(escText);
        getGameScreenPane().setOnKeyPressed(ev -> {
            if(ev.getCode() == KeyCode.ENTER){
                Level1 level1 = new Level1();

                getGameScreenPane().getChildren().clear();
                level1.setStage(stage);

                stage.setScene(level1.getGameScreenScene());

            } else if (ev.getCode() == KeyCode.ESCAPE) {
                setTitleScreen();
            }
        });

        this.getGameScreenPane().getChildren().addAll(gameOverText,playAgainText,escText);
    }
    /**
     * The method that sets the scene to Title screen */
    public void setTitleScreen(){
        //Set cursor lines meant to show the default cursor, but it is a bug. it doesn't.
        getGameScreenPane().setCursor(Cursor.DEFAULT);
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.setStage(stage);
        TitleScreen.getTitleMusicPlayer().stop();
        TitleScreen.getTitleMusicPlayer().play();

        stage.setScene(TitleScreen.getTitleScreenScene());
        stage.getScene().setCursor(Cursor.DEFAULT);

    }
    public boolean isLevelEnded() {
        return !levelEnded;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
