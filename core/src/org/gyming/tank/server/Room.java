package org.gyming.tank.server;

import org.gyming.tank.connection.GameAction;
import org.gyming.tank.connection.GameFrame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Room implements Runnable {
    String roomID;
    BlockingQueue<Client> clients;
    int frameID = 0;
    public ArrayList<GameFrame> totFrame;
    ArrayList<GameAction> curFrame;
    int nums = 0;
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            for (Client client : clients) {
                while (!client.upload.isEmpty()) {
                    curFrame.add(client.upload.peek());
                    client.upload.poll();
                    nums++;
                }
            }
            GameFrame sumFrame = new GameFrame(frameID);
            for (GameAction action : curFrame) sumFrame.add(action);
            System.out.println(nums);
            curFrame.clear();
            for (Client client : clients) {
                client.download.offer(sumFrame);
            }
            frameID++;
            totFrame.add(sumFrame);
        }
    };

    public Room(String _roomID) {
        roomID = _roomID;
        clients = new LinkedBlockingQueue<>();
        curFrame = new ArrayList<>();
        totFrame = new ArrayList<>();
    }

    @Override
    public void run() {
        Timer timer = new Timer(true);
        timer.schedule(timerTask, 1, 10);
        while (true) ;
    }
}
