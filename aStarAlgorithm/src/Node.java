public class Node {
    private int row;
    private int col;
    private boolean isWall;
    private boolean isPath;
    private boolean isStart;
    private boolean isEnd;
    private int cost;
    private int gCost;
    private int hCost;
    private double heuristic;
    private Node next;
    private Node parent;

    public Node(int row, int col, boolean isWall, boolean isStart, boolean isEnd) {
        this.row = row;
        this.col = col;
        this.isWall = isWall;
        this.isStart = isStart;
        this.isEnd = isEnd;
        this.cost = 0;
        this.hCost = 0;
        this.heuristic = 0;
        this.next = null;
    }

    // Getters and setters

    public Node getParent(){
        return parent;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isWall() {
        return isWall;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getGCost() {
        return gCost;
    }

    public void setGCost(int cost) {
        this.gCost = cost;
    }

    public double getHCost() {
        return hCost;
    }

    public void setHCost(int cost) {
        this.hCost = cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setIsStart(boolean isStart){
        this.isStart = isStart;
    }

    public boolean getIsStart(){
        return isStart;
    }

    public void setIsEnd(boolean isEnd){
        this.isStart = isEnd;
    }

    public boolean getIsEnd(){
        return isEnd;
    }

    public void setIsPath(boolean isNext){
        this.isPath = isNext;
    }

    public boolean getIsPath(){
        return isPath;
    }

}
