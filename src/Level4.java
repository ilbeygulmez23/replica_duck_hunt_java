import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 * The Level4 class represents the fourth level of the Duck Hunt game.
 * It extends the GameScreen class and contains the specific configuration and behavior for level 4.
 */
public class Level4 extends GameScreen {

    /**
     * Constructs a new Level4 object.
     */
    public Level4() {
        super();
        Duck ObjectDuck1 = new Duck();
        Duck ObjectDuck2 = new Duck();
        setInfoTexts(4, 2);

        // Create two squares as shooting areas
        Rectangle square = new Rectangle((240 / 3f) * DuckHunt.getSCALE(), (240 / 3f) * DuckHunt.getSCALE());
        square.setFill(Color.TRANSPARENT);
        Rectangle square1 = new Rectangle((240 / 3f) * DuckHunt.getSCALE(), (240 / 3f) * DuckHunt.getSCALE());
        square1.setFill(Color.TRANSPARENT);

        // Calculate the center position of the squares
        double centerX = (getGameScreenPane().getWidth() - (320 / 3f) * DuckHunt.getSCALE()) / 2;
        double centerY = (getGameScreenPane().getHeight() - (320 / 3f) * DuckHunt.getSCALE()) / 2;

        // Position the squares
        square1.setLayoutX(centerX - (60 / 3f) * DuckHunt.getSCALE());
        square1.setLayoutY(centerY);
        square.setLayoutX(centerX + (120 / 3f) * DuckHunt.getSCALE());
        square.setLayoutY(centerY);

        // Apply rotations to the squares
        Rotate kite = new Rotate(135, (120 / 3f) * DuckHunt.getSCALE(), (120 / 3f) * DuckHunt.getSCALE());
        Rotate kite1 = new Rotate(45, (120 / 3f) * DuckHunt.getSCALE(), (120 / 3f) * DuckHunt.getSCALE());
        square.getTransforms().add(kite);
        square1.getTransforms().add(kite1);

        // Create the ImageView for duck1
        ImageView duck1 = ObjectDuck1.getBlackDuckFacingUpperRightNeutral();
        duck1.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck1.setFitHeight((40 / 3f) * DuckHunt.getSCALE());
        duck1.setScaleY(-1);

        // Create the ImageView for duck2
        ImageView duck2 = ObjectDuck2.getBlueDuckFacingUpperRightNeutral();
        duck2.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck2.setFitHeight((40 / 3f) * DuckHunt.getSCALE());

        // Animate the movement of duck1
        ObjectDuck1.animateDuck(
                duck1,
                ObjectDuck1.getBlackDuckFacingUpperRightWingsUp(),
                ObjectDuck1.getBlackDuckFacingUpperRightWingsDown()
        );

        // Animate the movement of duck2
        ObjectDuck2.animateDuck(
                duck2,
                ObjectDuck2.getBlueDuckFacingUpperRightWingsUp(),
                ObjectDuck2.getBlueDuckFacingUpperRightWingsDown()
        );

        // Set duck1 to follow a predefined path within square
        setDucktoPath(ObjectDuck1, duck1, square, 6, false);

        // Set duck2 to follow a predefined path within square1
        setDucktoPath(ObjectDuck2, duck2, square1, 6, false);

        // Handle mouse click events on the game screen
        getGameScreenPane().setOnMouseClicked(e -> {


            updateAmmoText();
            if (this.getAmmo() > 0) {
                setGunshot();
                if (duck1.getBoundsInParent().contains(e.getX(), e.getY())) {
                    shootDuck(
                            ObjectDuck1,
                            duck1,
                            ObjectDuck1.getBlackDuckShot().getImage(),
                            ObjectDuck1.getBlackDuckFalls().getImage()
                    );
                }
                if (duck2.getBoundsInParent().contains(e.getX(), e.getY())) {
                    shootDuck(
                            ObjectDuck2,
                            duck2,
                            ObjectDuck2.getBlueDuckShot().getImage(),
                            ObjectDuck2.getBlueDuckFalls().getImage()
                    );
                }
            } else if (isLevelEnded()) {
                setGameOver();
            }
        });
    }
}