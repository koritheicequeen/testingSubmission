package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class TestDemoJUnitTest {
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			  assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);}

		assertThatThrownBy(() -> testDemo.addPositive(a, b))
	        .isInstanceOf(IllegalArgumentException.class);
			
	}
	static Stream<Arguments> argumentsForAddPositive() {
	    return Stream.of(
	        arguments(2, 4, 6, false), 
	        arguments(3, 5, 8, false),   
	        arguments(-1, 5, 4, true),  
	        arguments(0, 5, 5, true),   
	        arguments(7, -3, 4, true),  
	        arguments(0, 0, 0, true)   
	    );
	}
	@Test
 void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(4,5)).isEqualTo(9);

		assertThat(testDemo.addPositive(40,50)).isEqualTo(90);
	}
	 void assertMultiplesAreDoneCorrectly() {
		 
			assertThat(testDemo.addPositive(4,5)).isEqualTo(9);

			assertThat(testDemo.addPositive(40,50)).isEqualTo(90);
		}
	 @Test
	 void assertThatNumberSquaredIsCorrect() {
		 TestDemo mockDemo = spy(testDemo);
		 doReturn(5).when(mockDemo).getRandomInt();
		 int fiveSquared = mockDemo.randomNumberSquared();
		 assertThat(fiveSquared).isEqualTo(25);
		/*
		 TestDemo mockDemo2 = spy(testDemo);
		 doReturn(5).when(mockDemo).getRandomInt();
		 assertThat(mockDemo.randomNumberSquared()).isEqualTo(25);*/
	 }
	 
	 @Test
	 void findDescriptionAndBonuses() {
		 assertThat(testDemo.fetchEffectAndDescription("Witch").equals("```"
        				+ "Description:  Practitioners of magic with a deep connection to mystical forces. They harness the natural and supernatural energies around them, using their knowledge to cast spells and brew potions." 
        		+ "Magical Affinity: Gain Advantage on Magic-based resistances checks.\r\n"
        		+ "Arcane Knowledge: All resistances have 1dmagic/2 added to them"+ "```"+"\n"
        		+ "Help: Alignment and Level"));
		 assertThat(testDemo.fetchEffectAndDescription("Human").equals("```"
        		+ "Description: A versatile and resilient race known for their adaptability and determination. Humans can thrive in a variety of environments and possess a unique ability to learn and master different skills quickly"+"\n"
        		+ "Versatile Growth: Start with 30 points instead of 20.\r\n"
        		+ "Adaptive Learner: Gain 1.25x more EXP for leveling up."+ "```" +"\n"
        		+ "Help: Level"));
	 }
}
