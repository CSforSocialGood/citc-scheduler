import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface Exportable {
    /*
    Returns the number of columns in this Exportable.
     */
    public int getNumColumns();

    /*
    Returns the number of
     */
    public int getNumRows();
    /*
    Returns the titles of the individual columns in this Exportable. The length of this array must match the returned value from getNumColumns.
     */
    public String[] getColumnNames();
    /*
    Gets a String representation of the exported value for a given column.
     */
    public String getValueForCell(int row, int column);

    private static String sanitizeTsvString(String in) {
        return in.replaceAll("\t", "\\t").replaceAll("\n", "\\n");
    }

    public static String createTsv(List<? extends Exportable> objects) {
        String out = "";
        for(Exportable obj : objects) {
            if(out.equals("")) {
                // add the headers
                out += String.join("\t", Arrays.stream(obj.getColumnNames()).map(x -> sanitizeTsvString(x)).collect(Collectors.toList())) + "\n";
            }
            for(int row = 0; row < obj.getNumRows(); row++) {
                for(int i = 0; i < obj.getNumColumns(); i++) {
                    out += sanitizeTsvString(obj.getValueForCell(row, i));
                    if(i != obj.getNumColumns() - 1) out += "\t";
                }
                if(row != obj.getNumRows() - 1) out += "\n";
            }

            if(objects.indexOf(obj) != objects.size() - 1) out += "\n";
        }
        return out;
    }

    public static void createTsvFile(List<? extends Exportable> objects, String outFilePath) throws IOException {
        String contents = createTsv(objects);
        FileWriter writer = new FileWriter(outFilePath, false);
        writer.write(contents);
        writer.close();
   }
}

