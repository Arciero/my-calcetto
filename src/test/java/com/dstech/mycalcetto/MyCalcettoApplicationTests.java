package com.dstech.mycalcetto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MyCalcettoApplicationTests {

	@Test
	void contextLoads() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(5);
		list.add(8);
		list.add(9);

		Iterator<Integer> it = list.iterator();
		while( it.hasNext() ) {
			if( it.next() > 2 ) {
				it.remove();
			}
		}

		System.out.println( list );

	}

}
