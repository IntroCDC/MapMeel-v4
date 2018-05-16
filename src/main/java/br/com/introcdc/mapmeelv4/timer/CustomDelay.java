package br.com.introcdc.mapmeelv4.timer;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv4.MapMain;

public class CustomDelay {

    private Runnable runnable;
    private Thread thread;
    private Timer timer;

    public CustomDelay(Runnable runnable, long minutes, long seconds, long milliseconds) {
        if (runnable == null) {
            return;
        }
        this.runnable = runnable;
        this.timer = new Timer();
        long totaltime;
        totaltime = seconds * 1000;
        totaltime = totaltime + minutes * 60000;
        totaltime = totaltime + milliseconds * 3;
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                CustomDelay.this.thread = new Thread(() -> new BukkitRunnable() {
                    @Override
                    public void run() {
                        runnable.run();
                    }
                }.runTask(MapMain.getPlugin()));
                CustomDelay.this.thread.start();
            }
        }, totaltime);
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    public Thread getThread() {
        return this.thread;
    }

    public Timer getTimer() {
        return this.timer;
    }

}
