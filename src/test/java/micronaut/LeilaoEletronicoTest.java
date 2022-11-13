<<<<<<<< HEAD:src/test/java/example/micronaut/LeilaoEletronicoTest.java
package example.micronaut;
========
package micronaut;
>>>>>>>> Alexia:src/test/java/micronaut/LeilaoEletronicoTest.java

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class LeilaoEletronicoTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

}