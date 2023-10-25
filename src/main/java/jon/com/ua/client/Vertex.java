package jon.com.ua.client;


import com.codenjoy.dojo.services.Point;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    public Point point;
    public List<Edge> edges = new ArrayList<>();
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Vertex(Point point) {
        this.point = point;
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }

    @Override
    public String toString() {
        return "{" + point + '}';
    }
}
