 package huytfgyhj;

import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;


import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MainWindow extends Group
{

  public static final int DEFAULT_WIDTH = 500;
  public static final int DEFAULT_HEIGHT = 300;

  Group g = new Group();
  Stage s = new Stage();
  
  private int width;
  private int height;
  private ImagePanel imagePanel;
  private TextArea messageView;
  public Button b2 ;
private Scene sc;

  public MainWindow()
  {
	    this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	    buildGUI();
	    sc = new Scene(g,700,700);
	    s.setScene(sc);
	    s.show();
  }

  public MainWindow(int width, int height)
  {
//    super.setTitle("Draw App");
//    super.setWidth(width+20);
//    super.setHeight(height+200);
    this.width = width;
    this.height = height;
//    this.setMinWidth(250);
//    buildGUI();
//    this.setVisible(true);
  }

  private void buildGUI()
  {
//    Group g = new Group();
//    Stage s = new Stage();
//    Scene sc = new Scene(g,500,300);
//    s.setScene(sc);
//    s.show();
    
//	BorderPane backPanel = new BorderPane();
	imagePanel = new ImagePanel(width, height);
//    backPanel.setCenter(i);
    g.getChildren().add(imagePanel);
    
    Button b1 = new Button();
    b1.setText("Save");
    b1.setTranslateX(200);
    b1.setTranslateY(400);
    g.getChildren().add(b1);
    b1.setOnMouseClicked(new EventHandler<Event>() {

		@Override
		public void handle(Event arg0) {
			// TODO Auto-generated method stub
			WritableImage wi = new WritableImage(width,height);
			sc.snapshot(wi); 
			File f = new File("g.jpg");
			postMessage("The picture has been saved!");
            try {
				ImageIO.write(SwingFXUtils.fromFXImage(wi, null), "png", f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				postMessage("Error!" + e.getMessage() );
			}
	      }
    	
	});
        
    b2 = new Button();
    b2.setText("Draw");
    b2.setTranslateX(100);
    b2.setTranslateY(400);
    g.getChildren().add(b2);
    b2.setOnMouseClicked(new EventHandler<Event>() {

		@Override
		public void handle(Event arg0) {
			// TODO Auto-generated method stub
//			l = new Reader.readline();
			
		}
    	
	});
    
        
    Button b3 = new Button();
    b3.setText("Exit");
    b3.setTranslateX(300);
    b3.setTranslateY(400);
    g.getChildren().add(b3);
    b3.setOnMouseClicked(new EventHandler<MouseEvent>(){

		public void handle(MouseEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
    });
    
    messageView = new TextArea();
    messageView.setTranslateX(10);
    messageView.setTranslateY(450);
    g.getChildren().add(messageView);  

  }

  public ImagePanel getImagePanel()
  {
    return imagePanel;
  }

  public void postMessage(final String str)
  {
     Platform.runLater(
        new Runnable()
        {
          public void run()
          {
        	  String a = messageView.getText();
        	  messageView.setText(a+"\n"+ str);

          }
        });
  }
}



