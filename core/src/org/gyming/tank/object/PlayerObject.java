package org.gyming.tank.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.gyming.tank.client.TankGame;
import org.gyming.tank.connection.GameAction;

public class PlayerObject extends GameObject {
    public static int playerSize = 20;
    public static int playerSpeed = 5;
    public static int playerHP = 100;
    public static int playerFireGap = 60;
    private int playerID;
    private String playerName;
    private Stage stage;

    public PlayerObject(float speed, float direction, float posX, float posY, int hp, int playerID,
                        String playerName, TankGame game, Stage stage) {
        super(speed, direction, posX, posY, hp, game, stage);
        this.playerID = playerID;
        this.playerName = playerName;
        this.stage = stage;
        this.identifier = playerID;
    }

    public final int getPlayerID() {
        return playerID;
    }

    public final void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public final String getPlayerName() {
        return playerName;
    }

    public final void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    protected Texture createTexture() {
//        System.out.println(colorPool.getUserColor(playerID));
//        System.out.println("***");
        return drawCircle(playerSize, colorPool.getUserColor(playerID));
    }

    @Override
    public void fire(GameAction action, float posX, float posY) {
        BulletObject bullet = new BulletObject(BulletObject.bulletSpeed, action.getDirection(), posX, posY, BulletObject.bulletHP, playerID, game, stage);
        stage.addActor(bullet);
    }

    @Override
    protected void recoverSpeed() {
        speed = 0;
    }
}
