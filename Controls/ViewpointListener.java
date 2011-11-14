package Phi.Controls;

import Phi.Viewpoint;
import java.awt.event.*;

public class ViewpointListener implements KeyListener
{

	protected Viewpoint myViewpoint;
	
    public ViewpointListener(Viewpoint myViewpoint)
    {
        this.myViewpoint = myViewpoint;
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
    }

    public void keyReleased(KeyEvent keyevent)
    {
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
}