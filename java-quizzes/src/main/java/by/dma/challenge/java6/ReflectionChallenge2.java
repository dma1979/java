package by.dma.challenge.java6;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/**
 * Quiz on Reflections: how to manipulate classes with reflection.
 *
 * @author dzmitry.marudau
 * @since 2019.08
 */
public class ReflectionChallenge2 {

    public static void main(String... doYourBest) {
        System.out.println(Jedi.class.getAnnotation(Table.class).name());

        System.out.println(Jedi.class.getAnnotation(Column.class).name());
        //the line above is equal the line below
        Arrays.stream(Jedi.class.getDeclaredFields())
              .forEach(field -> {
                  if ("attackType".equals(field.getName())) {
                      System.out.println(field.getAnnotation(Column.class).name());
                  }
              });
    }

    @Table(name = "jedi")
    static class Jedi {
        @Column(name = "attack_type")
        private String attackType;

        public String getAttackType() {
            return attackType;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Table {
        String name();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Column {
        String name();
    }
}
