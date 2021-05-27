package lesson1;

public class Track extends Barrier {

    private int length;

    public Track(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean action(Participant participant) {
        System.out.println("Беговая дорожка " + "length: " + this.length);

        participant.run();

        if (getLength() <= participant.distanceRun()) {
            System.out.println("Результат: пробежал успешно");
            return true;
        } else {
            System.out.println("Результат: не смог пробежать");
            return false;
        }
    }
}