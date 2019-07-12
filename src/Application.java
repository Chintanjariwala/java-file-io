import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		try {
			List<String> allLinesICAP = Files.readAllLines(getFilePath(Consts.ICAP_FILE_PATH));
			List<String> allLinesDirecttellNumber = Files
					.readAllLines(getFilePath(Consts.DIRECT_TELL_NUMBER_FILE_PATH));

			int count = 0;

			for (String line : allLinesICAP) {
				String directtellNumber = line.substring(9, 23);

				if (allLinesDirecttellNumber.contains(directtellNumber)) {
					Files.write(getFilePath(Consts.OUTPUT_FILE_PATH),
							(line + System.lineSeparator()).getBytes(Consts.utf8), StandardOpenOption.CREATE,
							StandardOpenOption.APPEND);
					count++;
				}
			}

			System.out.println("Record found: " + count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Path getFilePath(String path) {
		return Paths.get(path);
	}

}
