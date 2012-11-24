package huytfgyhj;

//import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class Parser
{
  private BufferedReader reader;
  private ImagePanel image;
  private MainWindow frame;
  
//  ArrayList<String> al = new ArrayList<String>();

  public Parser(Reader r, ImagePanel image, MainWindow frame)
  {
    this.reader = new BufferedReader(r);
    this.image = image;
    this.frame = frame;
  }

  public void parse() throws IOException
  {
	  frame.b2.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				 try {
					String line = reader.readLine();
					if(line != null){
						 Parser.this.parseLine(line);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, e);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, e);
				}				
			}	
		});
    frame.postMessage("Drawing...");
  }

  private void parseLine(String line) throws ParseException
  {
    if (line.length() < 2) return;
    String command = line.substring(0, 2);
    if (command.equals("DL")) { drawLine(line.substring(2,line.length())); return; }
    if (command.equals("DR")) { drawRect(line.substring(2, line.length())); return; }
    if (command.equals("FR")) { fillRect(line.substring(2, line.length())); return; }
    if (command.equals("SC")) { setColour(line.substring(3, line.length())); return; }
    if (command.equals("DS")) { drawString(line.substring(3, line.length())); return; }
    if (command.equals("DA")) { drawArc(line.substring(2, line.length())); return; }
    if (command.equals("DO")) { drawOval(line.substring(2, line.length())); return; }
    if (command.equals("IM")) { drawimage(line.substring(2, line.length())); return; }
    if (command.equals("TS")) { start(line.substring(2, line.length())); return;}
    if (command.equals("TD")) { draw(line.substring(2, line.length())); return;}
    if (command.equals("TM")) { move(line.substring(2, line.length())); return;}
    if (command.equals("TR")) { turnright(line.substring(2, line.length())); return;}
    if (command.equals("TL")) { turnleft(line.substring(2, line.length())); return;}
    throw new ParseException("Unknown drawing command");
  }

  private void turnleft(String args) {
	// TODO Auto-generated method stub
		int angle = 0;
		
	    StringTokenizer tokenizer = new StringTokenizer(args);
	    try {
			angle = getInteger(tokenizer);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			frame.postMessage("Error!" + e.getMessage());
		}
	    image.turnleft(angle);
}

private void turnright(String args) {
	// TODO Auto-generated method stub
	int angle = 0;
	
    StringTokenizer tokenizer = new StringTokenizer(args);
    try {
		angle = getInteger(tokenizer);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Error!" + e.getMessage());
	}
    image.turnright(angle);
    
}

private void draw(String args) throws ParseException{
	// TODO Auto-generated method stub
	int c = 0;
	
    StringTokenizer tokenizer = new StringTokenizer(args);
    try {
		c = getInteger(tokenizer);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Error!" +e.getMessage());
	}
    if(c==1){
    	frame.postMessage("Pen up!");
    	image.draw(c);
    }else if(c==0){
    	frame.postMessage("Pen down!");
    	image.draw(c);
    }else{
    	frame.postMessage("Error!");
    }
}

private void move(String args) {
	// TODO Auto-generated method stub
		double l = 0;
		
		StringTokenizer tokenizer = new StringTokenizer(args);
		try {
			l = getInteger(tokenizer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			frame.postMessage("Error!" + e.getMessage());
		}
		image.move(l);
}

private void start(String agrs) throws ParseException{
	// TODO Auto-generated method stub
	double x = 0;
	double y = 0;
	int angle = 0;
	
    StringTokenizer tokenizer = new StringTokenizer(agrs);
    try {
		x = getInteger(tokenizer);
		y = getInteger(tokenizer);
		angle = getInteger(tokenizer);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Error" + e.getMessage());
	}
    image.start(x, y, angle);
}

private void drawimage(String args) throws ParseException
  {
	// TODO Auto-generated method stub
	int x = 0;
	int y = 0;
	int width = 0;
	int height = 0;
	String string = "";
	
    StringTokenizer tokenizer = new StringTokenizer(args);
    try {
		x = getInteger(tokenizer);
		y = getInteger(tokenizer);
		width = getInteger(tokenizer);
		height = getInteger(tokenizer);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Error!" + e.getMessage() );
	}
    
    try {
		int a = args.indexOf("@");
		if(a==-1){
			throw new ParseException("File name missing!");
			
		}else
		{
			string = args.substring(a+1, args.length());
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Error!" + e.getMessage() );
	}
    frame.postMessage("IM" + args);
    image.drawImage(x,y,width,height,string);
    
}

private void drawLine(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    try {
		x1 = getInteger(tokenizer);
		y1 = getInteger(tokenizer);
		x2 = getInteger(tokenizer);
		y2 = getInteger(tokenizer);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Error!" + e.getMessage() );
	}
    image.drawLine(x1,y1,x2,y2);
  }

  private void drawRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    try {
		x1 = getInteger(tokenizer);
		y1 = getInteger(tokenizer);
		x2 = getInteger(tokenizer);
		y2 = getInteger(tokenizer);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Error!" + e.getMessage());
	}
    image.drawRect(x1, y1, x2, y2);
  }

  private void fillRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    try {
		x1 = getInteger(tokenizer);
		y1 = getInteger(tokenizer);
		x2 = getInteger(tokenizer);
		y2 = getInteger(tokenizer);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Error!"+e.getMessage());
	}
    image.fillRect(x1, y1, x2, y2, y2);
  }

  private void drawArc(String args) throws ParseException
  {
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    int startAngle = 0;
    int arcAngle = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    try {
		x = getInteger(tokenizer);
		y = getInteger(tokenizer);
		width = getInteger(tokenizer);
		height = getInteger(tokenizer);
		startAngle = getInteger(tokenizer);
		arcAngle = getInteger(tokenizer);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Error!" + e.getMessage());
	}
    image.drawArc(x, y, width, height, startAngle, arcAngle);
  }

  private void drawOval(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int width = 0;
    int height = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    try {
		x1 = getInteger(tokenizer);
		y1 = getInteger(tokenizer);
		width = getInteger(tokenizer);
		height = getInteger(tokenizer);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Erorr!" + e.getMessage());
	}
    image.drawOval(x1, y1, width, height);
  }

  private void drawString(String args) throws ParseException
  {
    int x = 0;
    int y = 0 ;
    String s = "";
    StringTokenizer tokenizer = new StringTokenizer(args);
    try {
		x = getInteger(tokenizer);
		y = getInteger(tokenizer);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		frame.postMessage("Erorr!" + e.getMessage());
	}
    int position = args.indexOf("@");
    if (position == -1) throw new ParseException("DrawString string is missing");
    s = args.substring(position+1,args.length());
    image.drawString(x,y,s);
  }
  
//  private void Color(String colourName) throws ParseException
//  {
//      image.setColour(setColour(colourName));
//  }

  private Color setColour(String colourName) throws ParseException
  {
    if (colourName.equals("black")) { return javafx.scene.paint.Color.BLACK;}
    if (colourName.equals("blue")) { return javafx.scene.paint.Color.BLUE;}
    if (colourName.equals("cyan")) { return javafx.scene.paint.Color.CYAN;}
    if (colourName.equals("darkgray")) { return javafx.scene.paint.Color.DARKGRAY;}
    if (colourName.equals("gray")) { return javafx.scene.paint.Color.GRAY;}
    if (colourName.equals("green")) { return javafx.scene.paint.Color.GREEN;}
    if (colourName.equals("lightgray")) { return javafx.scene.paint.Color.LIGHTGRAY;}
    if (colourName.equals("magenta")) { return javafx.scene.paint.Color.MAGENTA;}
    if (colourName.equals("orange")) { return javafx.scene.paint.Color.ORANGE;}
    if (colourName.equals("pink")) { return javafx.scene.paint.Color.PINK;}
    if (colourName.equals("red")) { return javafx.scene.paint.Color.RED;}
    if (colourName.equals("white")) { return javafx.scene.paint.Color.WHITE;}
    if (colourName.equals("yellow")) { return javafx.scene.paint.Color.YELLOW;}
    throw new ParseException("Invalid colour name");
  }

  private int getInteger(StringTokenizer tokenizer) throws ParseException
  {
    if (tokenizer.hasMoreTokens())
      return Integer.parseInt(tokenizer.nextToken());
    else
      throw new ParseException("Missing Integer value");
  }
}
