package galaxies.util;

public class MovingPoint {
    public final Vector2d location;
    public final Vector2d velocity;

    public MovingPoint(Vector2d location, Vector2d velocity) {
        this.location = location;
        this.velocity = velocity;
    }

    // step a timestep
    // assumes no external forces on the
    public MovingPoint step(double deltaT) {
        return new MovingPoint(location.add((velocity.scale(deltaT))), velocity);
    }

    // step a timestep, with an external force
    // uses a linear approximation
    public MovingPoint stepForceApprox(double deltaT, Vector2d externalForces) {
        return new MovingPoint(location.add(velocity.scale(deltaT)), velocity.add(externalForces.scale(deltaT)));
    }

    // step a certain timestep with a constant external force
    public MovingPoint stepForceExact(double deltaT, Vector2d externalForces) {
        return new MovingPoint(location.add(velocity.scale(deltaT)).add(externalForces.scale(deltaT*deltaT/2)),
                velocity.add(externalForces.scale(deltaT)));
    }
}
