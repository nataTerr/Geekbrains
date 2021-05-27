package lesson1;

public class Wall extends Barrier {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean action(Participant participant) {
        System.out.println("Стена " + "height: " + this.height);

        participant.jump();

        if (getHeight() <= participant.heightJump()) {
            System.out.println("Результат: прыгнул успешно");
            return true;
        } else {
            System.out.println("Результат: не смог прыгнуть");
            return false;
        }
    }
}