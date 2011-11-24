package Phi.Controls;

import Phi.Application;
import Phi.Viewpoint;
import com.sun.media.sound.AlawCodec;
import java.awt.event.*;

public class ViewpointListener implements KeyListener, MouseListener
{

	protected Viewpoint myViewpoint;
        protected Application app;
	
    public ViewpointListener(Viewpoint myViewpoint, Application application)
    {
        this.myViewpoint = myViewpoint;
        this.app = application;
    }

    public void keyPressed(KeyEvent e)
    {
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Up"))
            myViewpoint.lookUp();
        else
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Down"))
            myViewpoint.lookDown();
        else
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Right"))
            myViewpoint.lookRight();
        else
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Left"))
            myViewpoint.lookLeft();

            app.KeyPressed(e);
            
    }

    public void keyReleased(KeyEvent keyevent)
    {
       
        app.KeyReleased(keyevent);
    }

    public void keyTyped(KeyEvent e)
    {
        if(e.getKeyChar() == 's')
            myViewpoint.moveBackward();
        else
        if(e.getKeyChar() == 'w')
            myViewpoint.moveForward();
        else
        if(e.getKeyChar() == 'a')
            myViewpoint.moveLeft();
        else
        if(e.getKeyChar() == 'd')
            myViewpoint.moveRight();
        else
        if(e.getKeyChar() == 'e')
            myViewpoint.moveUp();
        else
        if(e.getKeyChar() == 'q')
            myViewpoint.moveDown();
    }

    @Override
    public void mouseClicked(MouseEvent me)
    {
       
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
       app.MousePressed(me);
    }

    @Override
    public void mouseReleased(MouseEvent me)
    {
        app.MouseReleased(me);
    }

    @Override
    public void mouseEntered(MouseEvent me)
    {
       
    }

    @Override
    public void mouseExited(MouseEvent me)
    {
        
    }
}