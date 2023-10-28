package jon.com.ua.client;

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import jon.com.ua.view.View;

import java.util.*;

/**
 * User: Ваше ім'я
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



    public String getWay(Board board) {
        String direction = Direction.UP.toString();
        Vertex[][] verticesMatrix = createMatrix(board);
        Vertex start = verticesMatrix[board.getHead().getX()][board.getHead().getY()];
        Vertex end = verticesMatrix[board.getApples().get(0).getX()][board.getApples().get(0).getY()];

        computePaths(start);

        List<Vertex> shortestPath = getShortestPathTo(end);

        if (shortestPath.size() > 1) {
            Point nextPoint = shortestPath.get(1).point;

            double y = nextPoint.getY();
            double x = nextPoint.getX();

            if (board.getHead().getY() < y) {
                direction = Direction.UP.toString();
            } else if (board.getHead().getX() > x) {
                direction = Direction.LEFT.toString();
            } else if (board.getHead().getX() < x) {
                direction = Direction.RIGHT.toString();
            } else if (board.getHead().getY() > y) {
                direction = Direction.DOWN.toString();
            }
        }

        if (shortestPath.size() < 2) {
            Vertex[][] verticesMatrixWithStone = createMatrixWithStone(board);

            start = verticesMatrixWithStone[board.getHead().getX()][board.getHead().getY()];
            end = verticesMatrixWithStone[board.getApples().get(0).getX()][board.getApples().get(0).getY()];

            computePaths(start);

            List<Vertex> shortestPathWithStone = getShortestPathTo(end);

            if (board.getSnake().size() >10 && shortestPathWithStone.size() > 1) {
                Point nextPoint = shortestPathWithStone.get(1).point;
                double y = nextPoint.getY();
                double x = nextPoint.getX();

                if (board.getHead().getY() < y) {
                    direction = Direction.UP.toString();
                } else if (board.getHead().getX() > x) {
                    direction = Direction.LEFT.toString();
                } else if (board.getHead().getX() < x) {
                    direction = Direction.RIGHT.toString();
                } else if (board.getHead().getY() > y) {
                    direction = Direction.DOWN.toString();
                }
            } else {
                List<String> availableDirections = new ArrayList<>();
                int headX = board.getHead().getX();
                int headY = board.getHead().getY();

                if (isCollision(headX, headY + 1, board)&& isCollision(headX , headY+ 2, board)
                        && isCollision(headX +1, headY+ 1, board)
                        && isCollision(headX +1, headY+2, board)) {
                    availableDirections.add(Direction.UP.toString());
                }

                if (isCollision(headX, headY - 1, board) && isCollision(headX, headY-2, board)
                        && isCollision(headX -1, headY-1, board)
                        && isCollision(headX - 1, headY-2, board)) {
                    availableDirections.add(Direction.DOWN.toString());
                }

                if (isCollision(headX - 1, headY, board)&& isCollision(headX - 2, headY, board)
                        && isCollision(headX - 1, headY-1, board)
                        && isCollision(headX - 2, headY-1, board)) {
                    availableDirections.add(Direction.LEFT.toString());
                }

                if (isCollision(headX + 1, headY, board) && isCollision(headX + 2, headY, board)
                        && isCollision(headX + 2 , headY+1, board)
                        && isCollision(headX + 1 , headY+1, board)) {
                    availableDirections.add(Direction.RIGHT.toString());
                }


                if (isCollision(headX, headY + 1, board)&& isCollision(headX , headY+ 2, board)
                        && isCollision(headX -1, headY+ 1, board)
                        && isCollision(headX -1, headY+2, board)) {
                    availableDirections.add(Direction.UP.toString());
                }

                if (isCollision(headX, headY - 1, board) && isCollision(headX, headY-2, board)
                        && isCollision(headX +1, headY-1, board)
                        && isCollision(headX + 1, headY-2, board)) {
                    availableDirections.add(Direction.DOWN.toString());
                }

                if (isCollision(headX - 1, headY, board)&& isCollision(headX - 2, headY, board)
                        && isCollision(headX - 1, headY+1, board)
                        && isCollision(headX - 2, headY+1, board)) {
                    availableDirections.add(Direction.LEFT.toString());
                }

                if (isCollision(headX + 1, headY, board) && isCollision(headX + 2, headY, board)
                        && isCollision(headX + 2 , headY-1, board)
                        && isCollision(headX + 1 , headY-1, board)) {
                    availableDirections.add(Direction.RIGHT.toString());
                }



                if (isCollision(headX, headY + 1, board)&& isCollision(headX , headY+ 2, board)
                        && isCollision(headX , headY+ 3, board)) {
                    availableDirections.add(Direction.UP.toString());
                }

                if (isCollision(headX, headY - 1, board) && isCollision(headX, headY-2, board)
                        && isCollision(headX, headY-3, board)) {
                    availableDirections.add(Direction.DOWN.toString());
                }

                if (isCollision(headX - 1, headY, board)&& isCollision(headX - 2, headY, board)
                        && isCollision(headX - 3, headY, board)) {
                    availableDirections.add(Direction.LEFT.toString());
                }

                if (isCollision(headX + 1, headY, board) && isCollision(headX + 2, headY, board)
                        && isCollision(headX + 3 , headY, board)) {
                    availableDirections.add(Direction.RIGHT.toString());
                }




                if (isCollision(headX, headY + 1, board)&& isCollision(headX , headY+ 2, board)
                        && isCollision(headX +1, headY+ 2, board)) {
                    availableDirections.add(Direction.UP.toString());
                }

                if (isCollision(headX, headY - 1, board) && isCollision(headX, headY-2, board)
                        && isCollision(headX-1, headY-2, board)) {
                    availableDirections.add(Direction.DOWN.toString());
                }

                if (isCollision(headX - 1, headY, board)&& isCollision(headX - 2, headY, board)
                        && isCollision(headX -2 , headY-1, board)) {
                    availableDirections.add(Direction.LEFT.toString());
                }

                if (isCollision(headX + 1, headY, board) && isCollision(headX + 2, headY, board)
                        && isCollision(headX +2 , headY+1, board)) {
                    availableDirections.add(Direction.RIGHT.toString());
                }




                if (isCollision(headX, headY + 1, board)&& isCollision(headX , headY+ 2, board)) {
                    availableDirections.add(Direction.UP.toString());
                }

                if (isCollision(headX, headY - 1, board) && isCollision(headX, headY-2, board)) {
                    availableDirections.add(Direction.DOWN.toString());
                }

                if (isCollision(headX - 1, headY, board)&& isCollision(headX - 2, headY, board)) {
                    availableDirections.add(Direction.LEFT.toString());
                }

                if (isCollision(headX + 1, headY, board) && isCollision(headX + 2, headY, board) ) {
                    availableDirections.add(Direction.RIGHT.toString());
                }



                if (isCollision(headX, headY + 1, board)) {
                    availableDirections.add(Direction.UP.toString());
                }

                if (isCollision(headX, headY - 1, board) ) {
                    availableDirections.add(Direction.DOWN.toString());
                }

                if (isCollision(headX - 1, headY, board)) {
                    availableDirections.add(Direction.LEFT.toString());
                }

                if (isCollision(headX + 1, headY, board) ) {
                    availableDirections.add(Direction.RIGHT.toString());
                }

                if (!availableDirections.isEmpty()) {
                    return availableDirections.get(0);
                }
            }
        }

        return direction;
    }

        private boolean isCollision(int x, int y, Board board) {
            List<Point> badPoints = new ArrayList<>();
            badPoints.addAll(board.getSnake());
            badPoints.addAll(board.getStones());
            badPoints.addAll(board.getWalls());

            for (Point point : badPoints) {
                if (point.getX() == x && point.getY() == y) {
                    return false;
                }
            }

            return true;
        }


    public static void computePaths(Vertex source) {
        source.minDistance = 0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex current = vertexQueue.poll();
            for (Edge adjEdge : current.edges) {
                Vertex neighbour = adjEdge.target();
                double edgeWeight = adjEdge.weight();
                double distanceThroughCurrent = current.minDistance + edgeWeight;
                if (distanceThroughCurrent < neighbour.minDistance) {
                    neighbour.minDistance = distanceThroughCurrent;
                    neighbour.previous = current;
                    vertexQueue.add(neighbour);
                }
            }
        }
    }

    public Vertex[][] createMatrix(Board board) {
        List<Point> emptyPoints = board.get(Elements.NONE);
        emptyPoints.add(board.getHead());
        emptyPoints.add(board.getApples().get(0));
        Vertex[][] verticesMatrix = new Vertex[board.size()][board.size()];

        for (Point point : emptyPoints) {
            verticesMatrix[point.getX()][point.getY()] = new Vertex(point);
        }

        for (int i = 0; i < verticesMatrix.length - 1; i++) {
            for (int j = 0; j < verticesMatrix.length - 1; j++) {
                if (verticesMatrix[i][j] == null) {
                    continue;
                }

                if (verticesMatrix[i][j - 1] != null) {
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i][j - 1], 1));
                }

                if (verticesMatrix[i][j + 1] != null) {
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i][j + 1], 1));
                }

                if (verticesMatrix[i - 1][j] != null) {
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i - 1][j], 1));
                }

                if (verticesMatrix[i + 1][j] != null) {
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i + 1][j], 1));
                }
            }
        }
        return verticesMatrix;
    }

    public Vertex[][] createMatrixWithStone(Board board) {
        List<Point> emptyPoints = board.get(Elements.NONE);
        emptyPoints.add(board.getHead());
        emptyPoints.add(board.getApples().get(0));
        emptyPoints.add(board.getStones().get(0));
        Vertex[][] verticesMatrix = new Vertex[board.size()][board.size()];

        for (Point point : emptyPoints) {
            verticesMatrix[point.getX()][point.getY()] = new Vertex(point);
        }

        for (int i = 0; i < verticesMatrix.length - 1; i++) {
            for (int j = 0; j < verticesMatrix.length - 1; j++) {
                if (verticesMatrix[i][j] == null) {
                    continue;
                }

                if (verticesMatrix[i][j - 1] != null) {
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i][j - 1], 1));
                }

                if (verticesMatrix[i][j + 1] != null) {
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i][j + 1], 1));
                }


                if (verticesMatrix[i - 1][j] != null) {
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i - 1][j], 1));
                }


                if (verticesMatrix[i + 1][j] != null) {
                    verticesMatrix[i][j].edges.add(new Edge(verticesMatrix[i + 1][j], 1));
                }
            }
        }

        return verticesMatrix;
    }
    public List<Point> getPath() {
        return path;
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        View.runClient(new YourSolver(null), new Board());
    }
}
