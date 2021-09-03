import java.util.Random;

public class Swarm 
{
	
    private int numOfParticles, epochs;
    private double inertia, cognitiveComponent, socialComponent;
    Vector bestPosition;
    public double bestEval;
    public static final double DEFAULT_INERTIA = 1;
    public static final double DEFAULT_COGNITIVE = .005; // Cognitive component.
    public static final double DEFAULT_SOCIAL = .3; // Social component.
    
    
    //public static final double DEFAULT_INERTIA = 0.729844;
    //public static final double DEFAULT_COGNITIVE = 1.496180; // Cognitive component.
    //public static final double DEFAULT_SOCIAL = 1.496180; // Social component.
    
    private double beginRange, endRange;
    private static final int DEFAULT_BEGIN_RANGE = 0;
    private static final int DEFAULT_END_RANGE = 500;
    Particle[] particles;

    double mouseXLocation;
    double mouseYlocation;
	
    public Swarm (int particles1, double mouseXLocation, double mouseYlocation) {
        this.numOfParticles = particles1;
        this.mouseXLocation = mouseXLocation;
        this.mouseYlocation = mouseYlocation;
        double infinity = Double.POSITIVE_INFINITY;
        bestPosition = new Vector(infinity, infinity);
        bestEval = Double.POSITIVE_INFINITY;
        beginRange = DEFAULT_BEGIN_RANGE;
        endRange = DEFAULT_END_RANGE;
        particles = initialize();
        epochs = 0;
        
        inertia =  DEFAULT_INERTIA;
        cognitiveComponent = DEFAULT_COGNITIVE;
        socialComponent = DEFAULT_SOCIAL;
    }
    
    private Particle[] initialize () {
        Particle[] particles = new Particle[numOfParticles];
        for (int i = 0; i < numOfParticles; i++) {
            Particle particle = new Particle(beginRange, endRange, mouseXLocation, mouseYlocation);
            particles[i] = particle;
            updateGlobalBest(particle);
        }
        return particles;
    }
    private void updateGlobalBest (Particle particle) {
        if (particle.getBestEval() < bestEval) {
            bestPosition = particle.getBestPosition();
            bestEval = particle.getBestEval();
        }
    }
    
    public void updateVelocities()
    {
    	for(int i = 0; i < particles.length; i++)
    	{
    		particles[i].updatePersonalBest(mouseXLocation, mouseYlocation);
    		updateGlobalBest(particles[i]);
    		updateVelocity(particles[i]);
    		particles[i].updatePosition();
    	}
		epochs ++;

    }

	
    private void updateVelocity (Particle particle) {
        Vector oldVelocity = particle.getVelocity();
        Vector pBest = particle.getBestPosition();
        Vector gBest = bestPosition.clone();
        Vector pos = particle.getPosition();

        Random random = new Random();
        double r1 = random.nextDouble();
        double r2 = random.nextDouble();

        // The first product of the formula.
        Vector newVelocity = oldVelocity.clone();
        newVelocity.mul(inertia);

        // The second product of the formula.
        pBest.sub(pos);
        pBest.mul(cognitiveComponent);
        pBest.mul(r1);
        newVelocity.add(pBest);

        // The third product of the formula.
        gBest.sub(pos);
        gBest.mul(socialComponent);
        gBest.mul(r2);
        newVelocity.add(gBest);
        
        if(newVelocity.getX() < 0)
        {
        	newVelocity.set(newVelocity.getX(), newVelocity.getY());
        }
        if(newVelocity.getX() > 500)
        {
        	newVelocity.set(-newVelocity.getX(), newVelocity.getY());
        }
        if(newVelocity.getY() < 0)
        {
        	newVelocity.set(newVelocity.getX(), newVelocity.getY());
        }
        if(newVelocity.getY() > 500)
        {
        	newVelocity.set(newVelocity.getX(), -newVelocity.getY());
        }
        
       newVelocity.mul(0.9);
       particle.setVelocity(newVelocity);
}
    
    public void setMouseXLocation(double x)
    {
    	mouseXLocation = x;/*setParticles mouselocation*/
    	for(int i = 0; i < particles.length; i++)
    		particles[i].setMouseXLocation(x);
    }
    public void setMouseYLocation(double x)
    {
    	mouseYlocation = x;/*setParticles mouselocation*/
    	for(int i = 0; i < particles.length; i++)
    		particles[i].setMouseYLocation(x);
    }

	

}
