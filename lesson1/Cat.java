package lesson1;

public class Cat implements Participant {

    private String name;
    private int distanceRun;
    private int heightJump;

    public Cat(String name, int distance, int heightJump) {
        this.name = name;
        this.distanceRun = distance;
        this.heightJump = heightJump;
    }

    public void run() {
        System.out.println("Кот " + this.name + " может пробежать " + this.distanceRun());
    }

    public void jump() {
        System.out.println("Кот " + this.name + " может прыгнуть " + this.heightJump());
    }

    public int distanceRun() {
        return this.distanceRun;
    }

    public int heightJump() {
        return this.heightJump;
    }
}
