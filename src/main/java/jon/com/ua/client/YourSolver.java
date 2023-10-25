package jon.com.ua.client;

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import jon.com.ua.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {
    private Dice dice;
    private Board board;
    private List<Point> path;

    public YourSolver(Dice dice) {
        this.dice = dice;
        this.path = new ArrayList<>();
    }

    @Override
    public String get(Board board) {


        if (board.isGameOver()) {
            return Direction.UP.toString();
        }

        return getWay(board);
    }




public String getWay(Board board){

    computePaths(createMatrix(board)[board.getHead().getX()][board.getHead().getY()]);
    Point p = getShortestPathTo(createMatrix(board)[board.getApples().get(0).getX()][board.getApples().get(0).getY()]).get(0).point;

    double y = p.getY();
    double x = p.getX();

    String direction = Direction.UP.toString();

    if (board.getHead().getX() == x && board.getHead().getY() < y) {
        direction= Direction.UP.toString();
    } else if (board.getHead().getX() > x && board.getHead().getY() == y) {
        direction= Direction.LEFT.toString();
    } else if (board.getHead().getX() < x && board.getHead().getY() == y) {
        direction= Direction.RIGHT.toString();
    } else if (board.getHead().getX() == x && board.getHead().getY() > y) {
        direction= Direction.DOWN.toString();
    }
    return direction;
}



    public static void computePaths(Vertex source) {
        source.minDistance = 0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex current = vertexQueue.poll();
            // Проходим по всем исходящим дугам
            for (Edge adjEdge : current.edges) {
                // У каждой дуги берём вершину с которой дуга связывает
                Vertex neighbour = adjEdge.target();
                // Берём вес дуги для вычисления расстояния от источника
                double edgeWeight = adjEdge.weight();
                // Вычисляем расстояние от источника до текущей вершины
                double distanceThroughCurrent = current.minDistance + edgeWeight;
                // Проверяем надо ли менять пометку
                // Проверяем меньше ли расстояние от источника до текущей вершины чем текущая пометка
                if (distanceThroughCurrent < neighbour.minDistance) {
                    // Меняем пометку для смежной вершины
                    // Ставим новую пометку
                    neighbour.minDistance = distanceThroughCurrent;
                    // Проставляем каждой смежной вершине ссылку на текущую, для определения пути
                    neighbour.previous = current;
                    // Помещаем смежную вершину в очередь с обновлённой пометкой
                    vertexQueue.add(neighbour);
                }
            }
        }
    }


    //повертає матрицю пустих кітинок по яким можна ходити
    public Vertex[][] createMatrix(Board board){
        List<Point> emptyPoints = board.get(Elements.NONE);
        emptyPoints.add(board.getHead());

        emptyPoints.add(board.getApples().get(0));
        Vertex[][] verticesMatrix = new Vertex[board.size()][board.size()];
        for(Point point: emptyPoints){
                verticesMatrix[point.getX()][point.getY()] = new Vertex(point);
        }
        for (int i = 0; i < verticesMatrix.length - 1; i++) {
            for(int j = 0; j < verticesMatrix.length - 1; j++) {
                if (verticesMatrix[i][j] == null){
                    continue;
                }
                if (verticesMatrix[i][j - 1] != null){
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i][j - 1], 1));
                }
                if (verticesMatrix[i][j + 1] != null){
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i][j + 1], 1));
                }
                if (verticesMatrix[i - 1][j] != null){
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i- 1][j], 1));
                }
                if (verticesMatrix[i + 1][j] != null){
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i+ 1][j], 1));
                }
            }
        }
        return verticesMatrix;
    }

    public List<Point> getPath() {
        return path;
    }


    //шукає найкоротший шлях до яблука
    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }




    public static void main(String[] args) {
        View.runClient(
                new YourSolver(null),
                new Board());
    }
}
