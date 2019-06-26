import java.util.Random;

public class Particle 
{

	private Vector Position;
	private Vector Velocity;
	private Vector BestPosition;
	public double bestEval;
	private double mouseXLocation;
	private double mouseYLocation;
	
	public Particle(double beginRange, double endRange, double mouseXLocation, double mouseYLocation)
	{
		Position = new Vector();
		Velocity = new Vector();
		setRandomPosition(beginRange, endRange);
		setRandomVelocity(beginRange, endRange);
		BestPosition = Position.clone();
		this.setMouseXLocation(mouseXLocation);
		this.setMouseYLocation(mouseYLocation);
		bestEval = eval(mouseXLocation, mouseYLocation);
	}
	
	public double findDistance(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2), 2));
	}
	
	public double eval(double mouseXLocation, double mouseYlocation)
	{
		return findDistance(Position.getX(), Position.getY(), mouseXLocation, mouseYlocation);
	}
	
	private void setRandomPosition(double beginningRange, double endingRange)
	{
		double x = rand(beginningRange, endingRange);
		double y = rand(beginningRange, endingRange);
		
		Position.set(x, y);
	}
	
	public void setRandomVelocity(double beginningRange, double endingRange)
	{
		double x = rand(beginningRange, endingRange);
		double y = rand(beginningRange, endingRange);
		
		Velocity.set(x, y);
	}
	
    private static double rand (double beginRange, double endRange) {
        Random r = new java.util.Random();
        return r.nextDouble()*(endRange);
}
	
    void updatePersonalBest (double mouseXLocation, double mouseYlocation) {
        double eval = eval(mouseXLocation, mouseYlocation);
        if (eval < bestEval) {
            BestPosition = Position.clone();
            bestEval = eval;
        }
}

    void updatePosition () {
        this.Position.add(Velocity);
}
    Vector getVelocity () {
        return Velocity.clone();
    }

    Vector getBestPosition() {
        return BestPosition.clone();
    }

    double getBestEval () {
        return bestEval;
    }

	public void setVelocity(Vector Velocity) {this.Velocity = Velocity;}
    
	public Vector getPosition() {return Position.clone();}
	

	public void setPosition(Vector x) {Position = x;}
	public void setBestKnownPosition(Vector x) {BestPosition = x;}

	public double getMouseXLocation() {
		return mouseXLocation;
	}

	public void setMouseXLocation(double mouseXLocation) {
		this.mouseXLocation = mouseXLocation;
	}

	public double getMouseYLocation() {
		return mouseYLocation;
	}

	public void setMouseYLocation(double mouseYLocation) {
		this.mouseYLocation = mouseYLocation;
	}

	
	
}
