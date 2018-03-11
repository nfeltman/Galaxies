public class MovingPoint {
    private final Vector2d location;
    private final Vector2d velocity;

    public MovingPoint(Vector2d location, Vector2d velocity) {
        this.location = location;
        this.velocity = velocity;
    }

    // step a timestep
    // assumes no external forces on the
    public MovingPoint step(double deltaT) {
        return null;
    }

    // step a timestep, with an external force
    // uses a linear approximation
    public MovingPoint stepForceApprox(double deltaT, Vector2d externalForces) {
        return null;
    }

    // step a certain timestep with a constant external force
    public MovingPoint stepForceExact(double deltaT, Vector2d externalForces) {
        return null;
    }
}
