package kahloringler.auxtunes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import kahloringler.auxtunes.gui.StaticMockTest;
import kahloringler.auxtunes.gui.TableTest;
import kahloringler.auxtunes.gui.TextTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class AcceptanceTests extends ApplicationAdapter {

    public static void main(String[] args) {
        new LwjglApplication(new AcceptanceTests(), new LwjglApplicationConfiguration());
    }

    private volatile ApplicationAdapter state;

    @Override
    public void create() {
        ApplicationAdapter[] tests = {
                new TableTest(),
                new TextTest(),
                new StaticMockTest()
        };

        new Thread(() -> {
            int passed = 0;
            int failed = 0;
            for (ApplicationAdapter test : tests) {
                // create a result queue
                BlockingQueue<Boolean> result = new LinkedBlockingDeque<>();

                Gdx.app.postRunnable(() -> {
                    // enter the state
                    test.create();
                    state = test;
                });

                // spawn the acceptance dialog
                JFrame jframe = new JFrame("Acceptance Test");
                JPanel jpanel = new JPanel();
                jpanel.add(new JLabel(test.getClass().getSimpleName()));
                JButton accept = new JButton("Pass Test");
                accept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        result.add(true);
                    }
                });
                jpanel.add(accept);
                JButton fail = new JButton("Fail Test");
                fail.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        result.add(false);
                    }
                });
                jpanel.add(fail);

                jframe.setContentPane(jpanel);
                jframe.pack();
                jframe.setVisible(true);

                // wait for the result and then process it
                try {
                    if (result.take()) {
                        System.out.println("Test passed - " + test.getClass().getSimpleName());
                        passed++;
                    } else {
                        System.err.println("Test failed - " + test.getClass().getSimpleName());
                        failed++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    jframe.dispose();
                    Gdx.app.postRunnable(test::dispose);
                }
            }

            System.out.println();
            System.out.println(passed + "/" + tests.length + " tests passed.");

            Gdx.app.exit();
        }).start();
    }

    @Override
    public void resize(int width, int height) {
        if (state != null) {
            state.resize(width, height);
        }
    }

    @Override
    public void render() {
        if (state != null) {
            state.render();
        }
    }

}
