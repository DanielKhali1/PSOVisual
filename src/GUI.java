import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;


public class GUI extends Application{

//Attributes
	Circle x = new Circle();
	boolean f = false;
	
	Pane SwarmPane = new Pane();
	double mouseX = 1;
	double mouseY = 1;
	
	
	
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage)throws Exception 
  {
	SwarmPane.setPrefSize(500, 500);
	Swarm swarm = new Swarm(100, mouseX, mouseY);


	Pane pane = new Pane();
	
	for(int i = 0; i < swarm.particles.length; i++)
	{
		Circle particle = new Circle(5);
		particle.setLayoutX(swarm.particles[i].getPosition().getX());
		particle.setLayoutY(swarm.particles[i].getPosition().getY());
		particle.setFill(Color.BLUE);
		SwarmPane.getChildren().add(particle);
	}
	
	

	
	
	
	pane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e)
		{
			f = true;
			
			mouseX = e.getX();
			mouseY = e.getY();
			
			swarm.setMouseXLocation(e.getX());
			swarm.setMouseYLocation(e.getY());
			
			for(int i = 0; i < swarm.particles.length; i++)
			{
				swarm.particles[i].setRandomVelocity(-500, 300);
				//swarm.particles[i].getVelocity().mul(100);
				swarm.particles[i].bestEval = Double.POSITIVE_INFINITY;
			}
			
			swarm.bestEval = Double.POSITIVE_INFINITY;
			
			
			
			x.setRadius(20);
			x.setLayoutX(e.getX());
			x.setLayoutY(e.getY());
			
		}});
	pane.getChildren().add(x);
	pane.getChildren().add(SwarmPane);
	
	

    Scene scene = new Scene(pane, 500, 500);
    primaryStage.setTitle("Particle Swarm Optimization Visual"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    

    
	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) 
        {
            if(f)
            {
            	swarm.updateVelocities();
            	SwarmPane.getChildren().clear();
        		for(int i = 0; i < swarm.particles.length; i++)
        		{
					Circle particle = new Circle(5);
					particle.setLayoutX(swarm.particles[i].getPosition().getX());
					particle.setLayoutY(swarm.particles[i].getPosition().getY());
					particle.setFill(Color.BLUE);
					SwarmPane.getChildren().add(particle);
        		}
        		//System.out.println(swarm.particles[0].getPosition().getX());
        		System.out.println(swarm.particles[0].eval(mouseX, mouseY));
        		
        		
            }
        	

            
        }
      }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  public static void main(String[] args) {
    launch(args);
  }
  
}



