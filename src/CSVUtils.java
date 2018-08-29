import java.io.FileWriter;
import java.io.IOException;

public class CSVUtils {

	public static String formatToCSVData(Object... obs) {

		String output = "";

		for (String string : strings) {
			output = output + string + ",";
		}

		return output.substring(0, output.length() - 1);

	}

	public static void toCSVOutput(String fileLocation, String row) {
		try (FileWriter writer = new FileWriter(fileLocation, true)) {

			writer.append(row);

		} catch (IOException ioe) {

			ioe.printStackTrace();

		}
	}

}