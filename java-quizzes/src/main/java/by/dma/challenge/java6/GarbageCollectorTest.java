package by.dma.challenge.java6;

/**
 * How many objects will be eligible for the garbage collector after the executeAction() invocation?
 * The JVM garbage collector identifies objects that are no longer needed and deletes them from memory.
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class GarbageCollectorTest {
    public static void main(String[] args) {

        HowManyObjectsWillBeCleanedByTheGarbageCollector gc = new GarbageCollectorTest()
            .new HowManyObjectsWillBeCleanedByTheGarbageCollector();

        gc.executeAction();

        System.out.println("How many objects are eligible be collected by "
                           + "the Garbage Collector after "
                           + "the executeAction() method invokation?" + gc);

    }

    private class HowManyObjectsWillBeCleanedByTheGarbageCollector {

        private Character homer = new Character("Dooh");

        private Character misterBurns = new Character("you are fired");

        private Character bart = new Character("eat my shorts");

        private Character elBarto;

        public void executeAction() {
            homer.talk();
            misterBurns.talk();
            makeTwoCharactersTalk(misterBurns, homer);
            misterBurns = null;
            elBarto = bart;
            bart = null;
            elBarto.talk();
        }

        private void makeTwoCharactersTalk(Character misterBurns, Character homer) {
            misterBurns.talk();
            homer.talk();
            misterBurns = null;
            homer = null;
        }
    }

    class Character {

        private String talk;

        public Character(String talk) {
            this.talk = talk;
        }

        public void talk() {
            System.out.println(this.talk);
        }
    }
}
