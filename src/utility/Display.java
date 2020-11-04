package utility;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

// Handles Window / Screen and objects in it

public class Display 
{	
	private JFrame frame;
	private Canvas canvas;		// a canvas allows us to "paint" graphics to it
	private String title;
	private int width, height;	// in pixels
	
	public Display(String title, int w, int h)
	{
		this.title = title;
		width = w;
		height = h;
		
		createWindow();
	}
	
	private void createWindow()
	{
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); // window invisible by default
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		//canvas.setFocusable(false);	// sets JFrame the only element to have focus
		
		frame.add(canvas);
		frame.pack();
	}
	
	public Canvas getCanvas()
	{
		return this.canvas;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}

}
