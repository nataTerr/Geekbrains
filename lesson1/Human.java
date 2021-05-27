package lesson1;

public class Human implements Participant {

    private String name;
    private int distanceRun;
    private int heightJump;

    public Human(String name, int distance, int heightJump) {
        this.name = name;
        this.distanceRun = distance;
        this.heightJump = heightJump;
    }

    public void run() {
        System.out.println("Человек " + this.name + " может пробежать " + this.distanceRun);
    }

    public void jump() {
        System.out.println("Человек " + this.name + " может прыгнуть " + this.heightJump);
    }

    public int distanceRun() {
        return this.distanceRun;
    }

    public int heightJump() {
        return this.heightJump;
    }
}
