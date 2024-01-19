import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 * The Level2 class represents the second level of the Duck Hunt game.
 * It extends the GameScreen class and contains the specific configuration and behavior for level 2.
 */
public class Level2 extends GameScreen {

    /**
     * Constructs a new Level2 object.
     */
    public Level2() {
        super();
        Duck ObjectDuck1 = new Duck();
        setInfoTexts(2, 1);

        // Create a transparent square as a shooting area
        Rectangle square = new Rectangle((320 / 3f) * DuckHunt.getSCALE(), (320 / 3f) * DuckHunt.getSCALE());
        square.setFill(Color.TRANSPARENT);

        // Calculate the center coordinates for positioning the square
        double centerX = (getGameScreenPane().getWidth() - (320 / 3f) * DuckHunt.getSCALE()) / 2;
        double centerY = (getGameScreenPane().getHeight() - (320 / 3f) * DuckHunt.getSCALE()) / 2;
        square.setLayoutX(centerX);
        square.setLayoutY(centerY);

        // Rotate the square to create a kite shape
        Rotate kite = new Rotate(45, (160 / 3f) * DuckHunt.getSCALE(), (160 / 3f) * DuckHunt.getSCALE());
        square.getTransforms().add(kite);

        // Create the ImageView for the duck
        ImageView duck1 = ObjectDuck1.getBlueDuckFacingUpperRightNeutral();

        // Set the size of the duck based on the scale factor
        duck1.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck1.setFitHeight((40 / 3f) * DuckHunt.getSCALE());

        // Animate the duck's movement
        ObjectDuck1.animateDuck(
                duck1,
                ObjectDuck1.getBlueDuckFacingUpperRightWingsUp(),
                ObjectDuck1.getBlueDuckFacingUpperRightWingsDown()
        );

        // Set the duck to follow a predefined path within the square
        setDucktoPath(ObjectDuck1, duck1, square, 6, false);

        // Handle mouse click events on the game screen
        getGameScreenPane().setOnMouseClicked(e -> {
            updateAmmoText();
            if (this.getAmmo() > 0) {
                setGunshot();
                if (duck1.getBoundsInParent().contains(e.getX(), e.getY())) {
                    shootDuck(
                            ObjectDuck1,
                            duck1,
                            ObjectDuck1.getBlueDuckShot().getImage(),
                            ObjectDuck1.getBlueDuckFalls().getImage()
                    );
                }
            } else if (isLevelEnded()) {
                setGameOver();
            }
        });
    }
}