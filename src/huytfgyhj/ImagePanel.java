package huytfgyhj;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;


public class ImagePanel extends Group
{
  Group g = new Group();

  private int width;
  private int height;
  private Color c;
  private LinearGradient z;

  public ImagePanel(int width, int height)
  {
    setImageSize(width, height);
  }

  private void setImageSize(int width, int height)
  {
	  this.width = width;
	  this.height = height;
  }

  public void setBackgroundColour(Color colour)
  {
	  Rectangle r = new Rectangle(0,0,width,height);
	  r.setFill(colour);
	  this.getChildren().add(r);
	  
//    graphics.setColor(colour);
//    graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
//    graphics.setColor(Color.black);
  }

  public void clear(Color colour)
  {
    setBackgroundColour(colour);
  }

  public void setColour(Color colour)
  {
	  c = colour;
  }

  public void drawLine(double x1, double y1, double x2, double y2)
  {
//    graphics.drawLine(x1, y1, x2, y2);
  Line l = new Line(x1, y1, x2, y2);
  System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk");
  this.getChildren().add(l);
  
  }

  public void drawRect(int x1, int y1, int x2, int y2)
  {
	  Rectangle roof = new Rectangle(x1, y1, x2, y2);
	  roof.setStroke(Color.BLACK);
	  this.getChildren().add(roof);
  }

  public void fillRect(int x1, int y1, int x2, int y2, int c)
  {
//    graphics.fillRect(x1, y1, x2, y2);
	  Rectangle house = new Rectangle(x1, y1, x2, y2);
	  if(c==1){
		  setGradient();
		  house.setFill(z);
		  
		  
	  }
	  
  }

  public void drawString(int x, int y, String s)
  {
//    graphics.drawString(s,x,y);
	  Text t = new Text(x,y,s);
  }

  public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
  {
//    graphics.drawArc(x,y,width,height,startAngle,arcAngle);
	  Arc a = new Arc(x,y,width,height,startAngle,arcAngle);
	  this.getChildren().add(a);
  }

  public void drawOval(int x, int y, int width, int height)
  {
//    graphics.drawOval(x,y,width,height);
	  Ellipse e = new Ellipse(x,y,width,height);
	  this.getChildren().add(e);
  }
  
  public void drawImage(int x, int y, int width, int height, String s)
  {
//    graphics.drawOval(x,y,width,height);
	  Image g = new Image( Drawapp.class.getResourceAsStream(s), width, height, true, true);
	  ImageView i = new ImageView(g);
	  i.setLayoutX(x);
	  i.setLayoutY(y);
	  this.getChildren().add(i);
  }
  
  public void setGradient(){
	  LinearGradient z = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop[]{
			  new Stop(0,Color.BEIGE),
			  new Stop(1,Color.BLACK)
			  
	  });
  }
  
//  private void setSize(String args) throws ParseException{
//	  
//	  
//  }
  
  //turtle
  private double x = 0;
  private double y = 0;
  private int angle = 0;
  private int run = 0;
  
  Rectangle p = new Rectangle(50,50,150,100);

	public void start(double x,double y, int angle){
		p.setStroke(Color.BROWN);
		p.setLayoutX(x);
		p.setLayoutY(y);
		this.x = x;
		this.y = y;
		this.angle = angle;
		Rotate r = new Rotate();
		p.getTransforms().add(new Rotate(angle,0,0));
		this.getChildren().add(p);
	}
	
	public void move(double distance){
		try {
			double xx = this.x;
			double yy = this.y;
			double sx = Math.sin(Math.toDegrees(angle));
			double sy = Math.cos(Math.toDegrees(angle));
			this.x = xx + distance*sx;
			this.y = yy + distance*sy;
			System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
			if(run == 0){
				drawLine(xx,yy,this.x,this.y);
			}
//			postage("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
			p.setLayoutX(this.x);
			p.setLayoutY(this.y);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("dddddddddddddddddddd");
		}
	}
	
	public void draw(int s){
		this.run = s;
	}
	
	public void turnleft(int angle){
		this.angle = this.angle-angle;
		p.getTransforms().add(new Rotate(180+angle,0,0));
	}
	
	public void turnright(int angle){
		this.angle = this.angle + angle;
		p.getTransforms().add(new Rotate(angle,0,0));
		
	}
}