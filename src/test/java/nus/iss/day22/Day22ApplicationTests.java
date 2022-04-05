package nus.iss.day22;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nus.iss.day22.services.GiphyService;

@SpringBootTest
class Day22ApplicationTests {

	@Autowired
	private GiphyService giphySvc;

	@Test
	void emptyInput() {
		List<String> gifUrls = giphySvc.getGifs("","10","pg");
		assertTrue(gifUrls.isEmpty());
		gifUrls = giphySvc.getGifs("");
		assertTrue(gifUrls.isEmpty());
	}
	
	void validInput() {
		List<String> gifUrls = giphySvc.getGifs("pokemon","10","pg");
		assertTrue(!gifUrls.isEmpty());
		assertEquals(10, gifUrls.size());
		gifUrls = giphySvc.getGifs("pokemon");
		assertTrue(!gifUrls.isEmpty());
		assertEquals(10, gifUrls.size());
	}

}
