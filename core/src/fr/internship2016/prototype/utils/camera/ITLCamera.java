package fr.internship2016.prototype.utils.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.internship2016.prototype.movable.MovableElement;
import fr.internship2016.prototype.movable.spells.Spell;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by bastien on 02/05/16.
 * Camera class
 */
public class ITLCamera implements Observer {

    private Viewport viewport;

    private float currentPosition;
    private float targetPosition;

    private float minimumPosition;
    private float maximumPosition;
    private double maximumDistance;

    private float worldWidth;
    private float worldHeight;
    private float widthViewport;

    public ITLCamera(final float currentPosition, final float minimumPosition, final float maximumPosition,
                     final double maximumDistance, final float worldWidth, final float worldHeight) {
        widthViewport = (float) Gdx.graphics.getWidth() * worldHeight / (float) Gdx.graphics.getHeight();

        this.currentPosition = currentPosition;
        this.minimumPosition = minimumPosition;
        this.maximumPosition = maximumPosition - widthViewport;
        this.maximumDistance = maximumDistance;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;


        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FillViewport(widthViewport * 2, worldHeight, camera);
        viewport.setScreenPosition(0, Gdx.graphics.getHeight());
        viewport.apply();
        viewport.getCamera().position.set(currentPosition, worldHeight / 2f, 0);
        viewport.getCamera().update();
    }

    @Override
    public void update(Observable observable, Object o) {
        //To avoid changing viewport during direction changes
        boolean moveCam = true;
        if (observable instanceof Spell) {
            //If the observable object is a spell, no need to move the cam
            moveCam = false;
            float spellPosition = ((Spell) observable).getX();
            if (spellPosition < currentPosition || spellPosition > currentPosition + widthViewport) {
                ((Spell) observable).setDisappear();
                Gdx.app.debug("CAMERA", "Set spell " + observable.toString() + " on disappear");
            }
        } else if (observable instanceof MovableElement) {
            //Get the observable element position
            float observablePosition = ((MovableElement) observable).getX();
            float moveCameraWidth = ((MovableElement) observable).getW() * 2;

            //Determination if a camera movement is needed
            //width * 2 is needed because of observablePosition not centered on the body
            if (observablePosition > currentPosition + moveCameraWidth
                    && observablePosition < currentPosition + widthViewport - moveCameraWidth * 2) {
                moveCam = false;
                Gdx.app.debug("CAMERA", "Observable outside of moving zone");
            }

            //Calculus of camera position in case of moving
            {
                targetPosition = observablePosition;
            }

            if (((MovableElement) observable).isRightFacing()) {
                //For space
                targetPosition += moveCameraWidth * 2;
                targetPosition -= widthViewport;
            } else {
                targetPosition -= moveCameraWidth;
            }
        }

        if (moveCam) {
            if (currentPosition != 0) {
                double distance = Math.sqrt(Math.pow(Math.abs(targetPosition - currentPosition), 2));
                if (distance > maximumDistance || distance < -maximumDistance) {
                    currentPosition = targetPosition;
                }
            } else {
                if (targetPosition > maximumDistance) currentPosition = targetPosition;
            }
        }

        //Set boundaries
        if (currentPosition < minimumPosition) {
            currentPosition = minimumPosition;
        } else if (currentPosition > maximumPosition) {
            currentPosition = maximumPosition;
        }

        viewport.getCamera().position.set(currentPosition, worldHeight / 2f, 0);
        viewport.getCamera().update();
    }

    public void resize(int height) {
        viewport.update(0, height);
    }

    public void reset() {
        currentPosition = 0f;
        targetPosition = 0f;
        viewport.getCamera().position.set(currentPosition, worldHeight / 2f, 0);
        viewport.getCamera().update();
    }

    public Matrix4 getCameraCombined() {
        return viewport.getCamera().combined;
    }
}
