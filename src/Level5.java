import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 * The Level5 class represents the fifth level of the Duck Hunt game.
 * It extends the GameScreen class and contains the specific configuration and behavior for level 5.
 */
public class Level5 extends GameScreen {

    /**
     * Constructs a new Level5 object.
     */
    public Level5() {
        super();
        Duck ObjectDuck1 = new Duck();
        Duck ObjectDuck2 = new Duck();
        Duck ObjectDuck3 = new Duck();
        setInfoTexts(5, 3);

        // Create two lines as shooting areas
        Line line1 = new Line(this.getGameScreenScene().getWidth(), (140 / 3f) * DuckHunt.getSCALE(), 0, (140 / 3f) * DuckHunt.getSCALE());
        line1.setStroke(Color.TRANSPARENT);
        Line line2 = new Line(0, (40 / 3f) * DuckHunt.getSCALE(), this.getGameScreenScene().getWidth(), (40 / 3f) * DuckHunt.getSCALE());
        line2.setStroke(Color.TRANSPARENT);

        // Create a square as a shooting area
        Rectangle square = new Rectangle((360 / 3f) * DuckHunt.getSCALE(), (360 / 3f) * DuckHunt.getSCALE());
        square.setFill(Color.TRANSPARENT);

        // Calculate the center position of the square
        double centerX = (getGameScreenPane().getWidth() - (360 / 3f) * DuckHunt.getSCALE()) / 2;
        double centerY = (getGameScreenPane().getHeight() - (360 / 3f) * DuckHunt.getSCALE()) / 2;
        square.setLayoutX(centerX);
        square.setLayoutY(centerY);

        // Apply rotation to the square
        Rotate kite = new Rotate(135, (160 / 3f) * DuckHunt.getSCALE(), (160 / 3f) * DuckHunt.getSCALE());
        square.getTransforms().add(kite);

        // Create the ImageView for duck1
        ImageView duck1 = ObjectDuck1.getBlackDuckFacingRightNeutral();
        duck1.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck1.setFitHeight((40 / 3f) * DuckHunt.getSCALE());
        duck1.setScaleY(-1);

        // Create the ImageView for duck2
        ImageView duck2 = ObjectDuck2.getBlueDuckFacingRightNeutral();
        duck2.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck2.setFitHeight((40 / 3f) * DuckHunt.getSCALE());

        // Create the ImageView for duck3
        ImageView duck3 = ObjectDuck3.getRedDuckFacingUpperRightNeutral();
        duck3.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck3.setFitHeight((40 / 3f) * DuckHunt.getSCALE());
        duck3.setScaleY(-1);

        // Animate the movement of duck1
        ObjectDuck1.animateDuck(
                duck1,
                ObjectDuck1.getBlackDuckFacingRightWingsUp(),
                ObjectDuck1.getBlackDuckFacingRightWingsDown()
        );

        // Animate the movement of duck2
        ObjectDuck2.animateDuck(
                duck2,
                ObjectDuck2.getBlueDuckFacingRightWingsUp(),
                ObjectDuck2.getBlueDuckFacingRightWingsDown()
        );

        // Animate the movement of duck3
        ObjectDuck3.animateDuck(
                duck3,
                ObjectDuck3.getRedDuckFacingUpperRightWingsUp(),
                ObjectDuck3.getRedDuckFacingUpperRightWingsDown()
        );

        // Set the paths for duck1, duck2, and duck3
        setDucktoPath(ObjectDuck1, duck1, line1, 4, true);
        setDucktoPath(ObjectDuck2, duck2, line2, 4, true);
        setDucktoPath(ObjectDuck3, duck3, square, 5, false);

        // Handle mouse click event
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
                if (duck3.getBoundsInParent().contains(e.getX(), e.getY())) {
                    shootDuck(
                            ObjectDuck3,
                            duck3,
                            ObjectDuck3.getRedDuckShot().getImage(),
                            ObjectDuck3.getRedDuckFalls().getImage()
                    );
                }
            } else if (isLevelEnded()) {
                setGameOver();
            }
        });
    }
}