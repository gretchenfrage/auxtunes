package kahloringler.auxtunes;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.junit.Assert;
import org.junit.internal.runners.statements.Fail;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class AcceptanceTest extends ApplicationAdapter {

    public static void run(ApplicationListener listener, String description) {
        // spawn the app
        Application app = new LwjglApplication(listener, new LwjglApplicationConfiguration());

        // create a result queue
        BlockingQueue<Boolean> result = new LinkedBlockingDeque<>();

        // spawn the acceptance dialog
        JFrame jframe = new JFrame("Acceptance Test");
        JPanel jpanel = new JPanel();
        //JList jlist = new JList();
        //jlist.add(new JLabel(description));
        jpanel.add(new JLabel(description));
        JButton accept = new JButton("Pass Test");
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               result.add(true);
            }
        });
        jpanel.add(accept);
        //jlist.add(accept);
        JButton fail = new JButton("Fail Test");
        fail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.add(false);
            }
        });
        jpanel.add(fail);
        //jlist.add(fail);

        jframe.setContentPane(jpanel);
        jframe.pack();
        jframe.setVisible(true);

        // wait for the result and then process it
        try {
            Assert.assertTrue(result.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            jframe.dispose();
            app.exit();
        }
    }


}