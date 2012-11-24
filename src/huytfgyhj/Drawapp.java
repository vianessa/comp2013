package huytfgyhj;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

//import javax.swing.SwingUtilities;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Drawapp extends Application
{
		public void initial(Stage primaryStage){
		final MainWindow main = new MainWindow();

	    Platform.runLater(
	    new Runnable()
	    {
	      public void run()
	      {
	        ImagePanel imagePanel = main.getImagePanel();
	        //Reader reader = new InputStreamReader(System.in);
	        Reader r = new StringReader(
	        		"DR 50 100 200 150\n"
                    + "DL 50 100 150 25\n"
                    + "DL 150 25 250 100\n"
                    + "IM 300 200 60 60 0 @g.jpg\n" 
                    + "DR 130 190 40 60\n"
                    + "FR 130 190 40 60 1\n"
                    + "DR 70 195 40 30 10\n"
                    + "DR 70 195 40 30 10\n"
                    + "FR 190 195 40 30 0\n"
                    + "DR 190 195 40 30\n"
                    //turtle
                    +"TS 350 100 45\n" // start
                    +"TD 0\n" // 0- pen down; 1- pen up
                    +"TM 50\n" //move
                    +"TR 90\n" //turn right
                    +"TM 90\n"
                    +"TL 90\n" // turn left
                    +"TM 50\n"
	        		
	        		);
	        Parser parser = new Parser(r,imagePanel,main);
	        try {
				parser.parse();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//	        imagePanel.repaint();
	      }
	    
	    }
	  );
		}
		
	    @Override
	    public void start(Stage primaryStage) {
	    	initial(primaryStage);
	    }
	

	    public static void main(String[] args) {
	        launch(args);
	        }
	}

	

