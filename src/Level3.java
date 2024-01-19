import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * The Level3 class represents the third level of the Duck Hunt game.
 * It extends the GameScreen class and contains the specific configuration and behavior for level 3.
 */
public class Level3 extends GameScreen {

    /**
     * Constructs a new Level3 object.
     */
    public Level3() {
        super();
        Duck ObjectDuck1 = new Duck();
        Duck ObjectDuck2 = new Duck();
        setInfoTexts(3, 2);

        // Create two invisible lines to define the shooting areas
        Line line1 = new Line(0, (100 / 3f) * DuckHunt.getSCALE(), this.getGameScreenScene().getWidth(), (100 / 3f) * DuckHunt.getSCALE());
        line1.setStroke(Color.TRANSPARENT);
        Line line2 = new Line(this.getGameScreenScene().getWidth(), (40 / 3f) * DuckHunt.getSCALE(),0 , (40 / 3f) * DuckHunt.getSCALE());
        line2.setStroke(Color.TRANSPARENT);

        // Create the ImageView for duck1
        ImageView duck1 = ObjectDuck1.getRedDuckFacingRightNeutral();
        duck1.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck1.setFitHeight((40 / 3f) * DuckHunt.getSCALE());

        // Create the ImageView for duck2
        ImageView duck2 = ObjectDuck2.getBlueDuckFacingRightNeutral();
        duck2.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck2.setFitHeight((40 / 3f) * DuckHunt.getSCALE());
        duck2.setScaleY(-1);

        // Animate the movement of duck1
        ObjectDuck1.animateDuck(
                duck1,
                ObjectDuck1.getRedDuckFacingRightWingsUp(),
                ObjectDuck1.getRedDuckFacingRightWingsDown()
        );

        // Animate the movement of duck2
        ObjectDuck2.animateDuck(
                duck2,
                ObjectDuck2.getBlueDuckFacingRightWingsUp(),
                ObjectDuck2.getBlueDuckFacingRightWingsDown()
        );

        // Set duck1 to follow a predefined path within line1
        setDucktoPath(ObjectDuck1, duck1, line1, 4, true);

        // Set duck2 to follow a predefined path within line2
        setDucktoPath(ObjectDuck2, duck2, line2, 4, true);

        // Handle mouse click events on the game screen
        getGameScreenPane().setOnMouseClicked(e -> {
            updateAmmoText();
            if (this.getAmmo() > 0) {
                setGunshot();
                if (duck1.getBoundsInParent().contains(e.getX(), e.getY())) {
                    shootDuck(
                            ObjectDuck1,
                            duck1,
                            ObjectDuck1.getRedDuckShot().getImage(),
                            ObjectDuck1.getRedDuckFalls().getImage()
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
