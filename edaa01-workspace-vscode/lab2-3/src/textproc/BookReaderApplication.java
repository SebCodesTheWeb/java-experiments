package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {
	public static void main(String[] args) throws FileNotFoundException {
		
        Scanner scan = new Scanner(new File("undantagsord.txt"));
        Set<String> stopwords = new HashSet<>(scan.tokens().toList());
        scan.close();


		GeneralWordCounter p = new GeneralWordCounter(stopwords);

		Scanner s = new Scanner(new File("nilsholg.txt"), "UTF-8");
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			p.process(word);
		}

		s.close();

        BookReaderController bookReaderController = new BookReaderController(p);
		System.out.println("Körs innan");

	}
}
