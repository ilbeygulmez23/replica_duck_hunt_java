import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 * The Level6 class represents the sixth level of the Duck Hunt game.
 * It extends the GameScreen class and contains the specific configuration and behavior for level 6.
 */
public class Level6 extends GameScreen {

    /**
     * Constructs a new Level6 object.
     */
    public Level6() {
        super();
        Duck ObjectDuck1 = new Duck();
        Duck ObjectDuck2 = new Duck();
        Duck ObjectDuck3 = new Duck();
        setInfoTexts(6, 3);

        // Create a square as a shooting area
        Rectangle square = new Rectangle((320 / 3f) * DuckHunt.getSCALE(), (320 / 3f) * DuckHunt.getSCALE());
        square.setFill(Color.TRANSPARENT);

        // Calculate the center position of the square
        double centerX = (getGameScreenPane().getWidth() - (320 / 3f) * DuckHunt.getSCALE()) / 2;
        double centerY = (getGameScreenPane().getHeight() - (320 / 3f) * DuckHunt.getSCALE()) / 2;
        square.setLayoutX(centerX);
        square.setLayoutY(centerY);

        // Apply rotation to the square
        Rotate kite = new Rotate(45, (160 / 3f) * DuckHunt.getSCALE(), (160 / 3f) * DuckHunt.getSCALE());
        square.getTransforms().add(kite);

        // Create another square as a shooting area
        Rectangle square1 = new Rectangle((240 / 3f) * DuckHunt.getSCALE(), (240 / 3f) * DuckHunt.getSCALE());
        square1.setFill(Color.TRANSPARENT);

        // Calculate the center position of the second square
        double centerX1 = (getGameScreenPane().getWidth() - (240 / 3f) * DuckHunt.getSCALE()) / 2;
        double centerY1 = (getGameScreenPane().getHeight() - (240 / 3f) * DuckHunt.getSCALE()) / 2;
        square1.setLayoutX(centerX1);
        square1.setLayoutY(centerY1);

        // Apply rotation to the second square
        Rotate kite1 = new Rotate(45, (120 / 3f) * DuckHunt.getSCALE(), (120 / 3f) * DuckHunt.getSCALE());
        square1.getTransforms().add(kite1);

        // Create a line as a shooting area
        Line line = new Line(0, getGameScreenScene().getHeight() / 2, this.getGameScreenScene().getWidth(), getGameScreenScene().getHeight() / 2);
        line.setStroke(Color.TRANSPARENT);

        // Create the ImageView for duck1
        ImageView duck1 = ObjectDuck1.getBlackDuckFacingRightNeutral();
        duck1.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck1.setFitHeight((40 / 3f) * DuckHunt.getSCALE());

        // Create the ImageView for duck2
        ImageView duck2 = ObjectDuck2.getBlueDuckFacingUpperRightNeutral();
        duck2.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck2.setFitHeight((40 / 3f) * DuckHunt.getSCALE());

        // Create the ImageView for duck3
        ImageView duck3 = ObjectDuck3.getRedDuckFacingUpperRightNeutral();
        duck3.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck3.setFitHeight((40 / 3f) * DuckHunt.getSCALE());

        // Animate the movement of duck1


        ObjectDuck1.animateDuck(
                duck1,
                ObjectDuck1.getBlackDuckFacingRightWingsUp(),
                ObjectDuck1.getBlackDuckFacingRightWingsDown()
        );

        // Animate the movement of duck2
        ObjectDuck2.animateDuck(
                duck2,
                ObjectDuck2.getBlueDuckFacingUpperRightWingsUp(),
                ObjectDuck2.getBlueDuckFacingUpperRightWingsDown()
        );

        // Animate the movement of duck3
        ObjectDuck3.animateDuck(
                duck3,
                ObjectDuck3.getRedDuckFacingUpperRightWingsUp(),
                ObjectDuck3.getRedDuckFacingUpperRightWingsDown()
        );

        // Set the paths for duck1, duck2, and duck3
        setDucktoPath(ObjectDuck1, duck1, line, 4, true);
        setDucktoPath(ObjectDuck2, duck2, square, 4, true);
        setDucktoPath(ObjectDuck3, duck3, square1, 5, false);

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

