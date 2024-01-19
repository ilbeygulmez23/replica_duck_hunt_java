import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
/**

 The Level1 class represents the first level of the Duck Hunt game.

 It extends the GameScreen class and contains the specific configuration and behavior for level 1.
 */
public class Level1 extends GameScreen {

    /**

     Constructs a new Level1 object.
     */
    public Level1() {
        super();
        Duck ObjectDuck1 = new Duck();
        setInfoTexts(1, 1);

        // Create an invisible line to define the shooting area
        Line line = new Line(0, 100, this.getGameScreenScene().getWidth(), 100);
        line.setStroke(Color.TRANSPARENT);

        // Create the ImageView for the duck
        ImageView duck1 = ObjectDuck1.getBlackDuckFacingRightNeutral();

        // Set the size of the duck based on the scale factor
        duck1.setFitWidth((68 / 3f) * DuckHunt.getSCALE());
        duck1.setFitHeight((40 / 3f) * DuckHunt.getSCALE());

        // Animate the duck's movement
        ObjectDuck1.animateDuck(
                ObjectDuck1.getBlackDuckFacingRightNeutral(),
                ObjectDuck1.getBlackDuckFacingRightWingsUp(),
                ObjectDuck1.getBlackDuckFacingRightWingsDown()
        );

        // Set the duck to follow a predefined path
        setDucktoPath(ObjectDuck1, duck1, line, 4, true);

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
            } else if (isLevelEnded()) {
                setGameOver();
            }
        });
    }
}