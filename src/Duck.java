import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Duck {
    private final ImageView BlackDuckFacingRightNeutral = new ImageView("assets/duck_black/5.png");
    private final ImageView BlackDuckFacingRightWingsUp = new ImageView("assets/duck_black/4.png");
    private final ImageView BlackDuckFacingRightWingsDown = new ImageView("assets/duck_black/6.png");
    private final ImageView BlackDuckFacingUpperRightNeutral = new ImageView("assets/duck_black/2.png");
    private final ImageView BlackDuckFacingUpperRightWingsUp = new ImageView("assets/duck_black/1.png");
    private final ImageView BlackDuckFacingUpperRightWingsDown = new ImageView("assets/duck_black/3.png");

    private final ImageView BlackDuckShot = new ImageView("assets/duck_black/7.png");
    private final ImageView BlackDuckFalls = new ImageView("assets/duck_black/8.png");

    private final ImageView BlueDuckFacingRightNeutral = new ImageView("assets/duck_blue/5.png");
    private final ImageView BlueDuckFacingRightWingsUp = new ImageView("assets/duck_blue/4.png");
    private final ImageView BlueDuckFacingRightWingsDown = new ImageView("assets/duck_blue/6.png");
    private final ImageView BlueDuckFacingUpperRightNeutral = new ImageView("assets/duck_blue/2.png");
    private final ImageView BlueDuckFacingUpperRightWingsUp = new ImageView("assets/duck_blue/1.png");
    private final ImageView BlueDuckFacingUpperRightWingsDown = new ImageView("assets/duck_blue/3.png");
    private final ImageView BlueDuckShot = new ImageView("assets/duck_blue/7.png");
    private final ImageView BlueDuckFalls = new ImageView("assets/duck_blue/8.png");

    private final ImageView RedDuckFacingRightNeutral = new ImageView("assets/duck_red/5.png");
    private final ImageView RedDuckFacingRightWingsUp = new ImageView("assets/duck_red/4.png");
    private final ImageView RedDuckFacingRightWingsDown = new ImageView("assets/duck_red/6.png");
    private final ImageView RedDuckFacingUpperRightNeutral = new ImageView("assets/duck_red/2.png");
    private final ImageView RedDuckFacingUpperRightWingsUp = new ImageView("assets/duck_red/1.png");
    private final ImageView RedDuckFacingUpperRightWingsDown = new ImageView("assets/duck_red/3.png");


    private final ImageView RedDuckShot = new ImageView("assets/duck_red/7.png");
    private final ImageView RedDuckFalls = new ImageView("assets/duck_red/8.png");
    private Timeline animation;
    private final PathTransition pt = new PathTransition();
    private boolean isShot = false;


    public ImageView getBlackDuckFacingRightNeutral() {
        return BlackDuckFacingRightNeutral;
    }

    public ImageView getBlackDuckFacingRightWingsUp() {
        return BlackDuckFacingRightWingsUp;
    }

    public ImageView getBlackDuckFacingRightWingsDown() {
        return BlackDuckFacingRightWingsDown;
    }

    public ImageView getBlackDuckFacingUpperRightNeutral() {
        return BlackDuckFacingUpperRightNeutral;
    }

    public ImageView getBlackDuckFacingUpperRightWingsUp() {
        return BlackDuckFacingUpperRightWingsUp;
    }

    public ImageView getBlackDuckFacingUpperRightWingsDown() {
        return BlackDuckFacingUpperRightWingsDown;
    }

    public ImageView getBlackDuckShot() {
        return BlackDuckShot;
    }

    public ImageView getBlackDuckFalls() {
        return BlackDuckFalls;
    }

    public ImageView getBlueDuckFacingRightNeutral() {
        return BlueDuckFacingRightNeutral;
    }

    public ImageView getBlueDuckFacingRightWingsUp() {
        return BlueDuckFacingRightWingsUp;
    }

    public ImageView getBlueDuckFacingRightWingsDown() {
        return BlueDuckFacingRightWingsDown;
    }

    public ImageView getBlueDuckFacingUpperRightNeutral() {
        return BlueDuckFacingUpperRightNeutral;
    }

    public ImageView getBlueDuckFacingUpperRightWingsUp() {
        return BlueDuckFacingUpperRightWingsUp;
    }

    public ImageView getBlueDuckFacingUpperRightWingsDown() {
        return BlueDuckFacingUpperRightWingsDown;
    }

    public ImageView getBlueDuckShot() {
        return BlueDuckShot;
    }

    public ImageView getBlueDuckFalls() {
        return BlueDuckFalls;
    }

    public ImageView getRedDuckFacingRightNeutral() {
        return RedDuckFacingRightNeutral;
    }

    public ImageView getRedDuckFacingRightWingsUp() {
        return RedDuckFacingRightWingsUp;
    }

    public ImageView getRedDuckFacingRightWingsDown() {
        return RedDuckFacingRightWingsDown;
    }

    public ImageView getRedDuckFacingUpperRightNeutral() {
        return RedDuckFacingUpperRightNeutral;
    }

    public ImageView getRedDuckFacingUpperRightWingsUp() {
        return RedDuckFacingUpperRightWingsUp;
    }

    public ImageView getRedDuckFacingUpperRightWingsDown() {
        return RedDuckFacingUpperRightWingsDown;
    }

    public ImageView getRedDuckShot() {
        return RedDuckShot;
    }

    public ImageView getRedDuckFalls() {
        return RedDuckFalls;
    }

    public Timeline getAnimation() {
        return animation;
    }

    public void animateDuck(ImageView duckNeutral, ImageView duckWingsUp, ImageView duckWingsDown){
        Image[] frames = new Image[3];

        final int[] currentFrame = {0};
        frames[0] = duckNeutral.getImage();
        frames[1] = duckWingsUp.getImage();
        frames[2] = duckWingsDown.getImage();

        animation = new Timeline(new KeyFrame(Duration.seconds(0.2), event -> {
            currentFrame[0] = (currentFrame[0] + 1) % 3;
            duckNeutral.setImage(frames[currentFrame[0]]);
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public boolean isShot() {
        return !isShot;
    }

    public void setShot(boolean shot) {
        isShot = shot;
    }

    public PathTransition pt() {
        return pt;
    }
}