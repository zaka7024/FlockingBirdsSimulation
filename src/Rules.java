
public class Rules {

    public static Vector2 CohesionRule(Bird [] birds, Bird a){
        Vector2 averagePosition = new Vector2(0,0);
        int count = 0;

        // iterate over all birds and check it's nearby if it is the add iy to the [averagePosition]
        for(int i = 0; i < birds.length; i++){
            if(birds[i] != a ){
                double neighborsDistance = Vector2.subtraction(birds[i].position, a.position).magnitude();
                if(neighborsDistance < a.distance){
                    averagePosition = Vector2.add(birds[i].position, averagePosition);
                    count++;
                }
            }
        }

        if(count > 0){
            // calculate the average position
            averagePosition = Vector2.divide(averagePosition,2.0);
            // vector towards the center [averagePosition]
            Vector2 distanceToCenter = Vector2.subtraction(averagePosition, a.position);

            if(distanceToCenter.magnitude() > 0)// normalize the vector
                distanceToCenter = Vector2.mul(Vector2.divide(distanceToCenter, distanceToCenter.magnitude()), a.maxSpeed);

            // the new acceleration vector towards the center
            Vector2 steering  = Vector2.subtraction(distanceToCenter, a.velocity);

            if(steering.magnitude() > 0) // normalize the vector
                steering = Vector2.mul(Vector2.divide(steering, steering.magnitude()), a.maxForce);

            return steering;
        }

        return new Vector2(0,0);
    }

    public static Vector2 SeparationRule(Bird [] birds, Bird a){
        int count = 0;
        Vector2 escapeDir = new Vector2(0,0);

        for(int i = 0; i < birds.length; i++){
            if(birds[i] != a){
                // the distance between a and bird[i]
                double neighborsDistance = Vector2.subtraction(birds[i].position, a.position).magnitude();
                // it close but not me
                if(neighborsDistance < a.distance){
                    Vector2 diff = Vector2.subtraction(a.position, birds[i].position);// direction of escape

                    diff = Vector2.divide(diff, neighborsDistance); // (Unit Vector)take just the direction of escape

                    escapeDir = Vector2.add(escapeDir, diff);
                    count++;
                }
            }
        }

        if(count > 0){
            escapeDir = Vector2.divide(escapeDir, count);// average escape direction

            escapeDir = Vector2.mul(Vector2.divide(escapeDir, escapeDir.magnitude()), a.maxForce);

            return Vector2.subtraction(escapeDir, a.velocity);// acceleration direction
        }

        return new Vector2(0,0);
    }

    public static Vector2 AlignmentRule(Bird [] birds, Bird a){
        Vector2 velocity = new Vector2(0,0); // to calculate the average velocity

        int count = 0;

        for(int i = 0; i < birds.length; i++){
            if(birds[i] != a){
                double neighborsDistance = Vector2.subtraction(birds[i].position, a.position).magnitude();
                if(neighborsDistance < a.distance){
                    velocity = Vector2.add(birds[i].velocity, velocity);
                    count++;
                }
            }
        }

        if(count > 0)
            velocity = Vector2.divide(velocity, count);
        velocity = Vector2.mul(Vector2.divide(velocity, velocity.magnitude()), a.maxSpeed);
        return Vector2.divide(Vector2.subtraction(a.velocity, velocity), 1);
    }

    public static Vector2 StayInThePosition(Bird a, int factor){
        Vector2 vector2 = new Vector2(0,0);
        if(a.position.x >= 1) vector2.x = -factor;
        else if(a.position.x <= 0) vector2.x = factor;

        if(a.position.y >= 1) vector2.y = -factor;
        else if(a.position.y <= 0) vector2.y = factor;

        return vector2;
    }
}
