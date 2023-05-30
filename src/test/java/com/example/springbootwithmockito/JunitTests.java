package com.example.springbootwithmockito;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;


@SpringBootTest
public class JunitTests {

    private Player player1 ;
    @Before
    public void initValues(){
        player1 = new Player("Patrick", 27);

    }
    @Test
    public void contextLoads() {
        Player player2 = new Player("Patrick", 23);

        assertEquals(player1, player1);
        assertNotEquals(player1, player2);
        assertSame(1, 1);
        //assertSame(new String("1"), new String("1"));

    }


    @Data
    @AllArgsConstructor
    class Player{
        private String name;
        private int age;

    }

}
