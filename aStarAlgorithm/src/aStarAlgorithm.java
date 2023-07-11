/*
* Author: Hasan Ahmad
* Simple A* search algo on an 8x8 grid, takes in coordinates input of start and end node
* outputs the grid and shortest path to end node from start.
*/

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;
public class aStarAlgorithm {
    public static void main(String[] args){
        createGrid();
    }

    public static void createGrid() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the Start point: ");
        int startX = input.nextInt() - 1;
        int startY = input.nextInt() - 1;
        System.out.println("Please enter the End point: ");
        int endX = input.nextInt() - 1;
        int endY = input.nextInt() - 1;
        Node[][] grid = new Node[8][8];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                boolean isStart = (row == startX && col == startY);
                boolean isEnd = (row == endX && col == endY);
                grid[row][col] = new Node(row, col, false, isStart, isEnd);
            }
        }
        searchAndRetreive(grid);
    }

    public static void searchAndRetreive(Node[][] grid){
        List<Node> open = new ArrayList<>();
        List<Node> closed = new ArrayList<>();
        boolean flag = true;
        Node start = null;
        Node end = null;
        Node current = null;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if(grid[row][col].getIsStart()) {
                    start = grid[row][col];
                    current = start;
                }
                if(grid[row][col].getIsEnd()) {
                    end = grid[row][col];
                }
            }
        }


        open.add(start);
        while(flag){
            open.sort(Comparator.comparing(Node::getCost));
            current  = open.get(0); // Node with lowest F cost;
            open.remove(current);
            closed.add(current);

            if(current.getIsEnd()){
                flag = false;
            }


            //Traversing neighbors
            // Define the range of neighboring cells
            int startRow = Math.max(0, current.getRow() - 1);
            int endRow = Math.min(8 - 1, current.getRow() + 1);
            int startCol = Math.max(0, current.getCol() - 1);
            int endCol = Math.min(8 - 1, current.getCol() + 1);

            // Iterate over the neighboring cells
            for (int row = startRow; row <= endRow; row++) {
                for (int col = startCol; col <= endCol; col++) {
                    // Exclude the current cell itself
                    if (row == current.getRow() && col == current.getCol()) {
                        continue;
                    }

                    // Skip if the node is in the closed list
                    Node neighbor = grid[row][col];
                    if (closed.contains(neighbor)) {
                        continue;
                    }

                    if(neighbor.getCost() > cost(neighbor, start, end) || !open.contains(neighbor)){
                        neighbor.setParent(current);
                        if(!open.contains(neighbor))
                            open.add(neighbor);
                    }
                }
            }
        }
        List<Node> path = new ArrayList<>();
        Node node = end;
        while (node != null) {
            path.add(0, node);
            node = node.getParent();
        }

        // Mark the nodes in the path as part of the path
        System.out.println(path.size());
        for (Node pathNode : path) {
            pathNode.setIsPath(true);
        }
        printGrid(grid, path);

    }

    public static int cost(Node current, Node end, Node start){
        int dx = Math.abs(end.getRow() - current.getRow());
        int dy = Math.abs(end.getCol() - current.getCol());
        int h  = Math.abs((int) Math.sqrt(dx * dx + dy * dy));


        int ddx = Math.abs(start.getRow() - current.getRow());
        int ddy = Math.abs(start.getCol() - current.getCol());
        int g = Math.abs((int) Math.sqrt(ddx * ddx + ddy * ddy));
        current.setHCost(h);
        current.setGCost(g);
        return g+h;
    }

    public static void printGrid(Node[][] grid, List<Node> path){
        System.out.println("-----------------------");
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                System.out.print((grid[row][col].getIsStart() ? "s  " : grid[row][col].getIsEnd() ? "e  " : path.contains(grid[row][col]) ? "-  " : "*  "));
            }
            System.out.println();
        }
        System.out.println("-----------------------");

    }
}

