package ipiCompareTool;

import java.io.*;
import java.util.Arrays;

class IPIComparer {
    private String output;
    private String drawCode;
    private String[] fileContent1;
    private String[] fileContent2;

    IPIComparer(File file1, File file2) throws IOException, NotValidIPIFormat {
        StringBuilder differences = new StringBuilder();
        StringBuilder codeBuilder = new StringBuilder();
        fileContent1 = readFile(file1);
        fileContent2 = readFile(file2);
        int line = 1;

        for (int i=0 ; i<fileContent1.length-1 ; i+=4) {
            if (!compare(fileContent1[i], fileContent2[i])) {
                differences.append(String.format("Files don't match at line %03d:\nDifference in column 1, IPI: code\n", line));
                codeBuilder.append(1);
            } else if (!compare(fileContent1[i+1], fileContent2[i+1])) {
                differences.append(String.format("Files don't match at line %03d:\nDifference in column 2, code\n", line));
                codeBuilder.append(2);
            } else if (!compare(fileContent1[i+2], fileContent2[i+2])) {
                differences.append(String.format("Files don't match at line %03d:\nDifference in column 3, description\n", line));
                codeBuilder.append(3);
            } else if (!compare(fileContent1[i+3], fileContent2[i+3])) {
                differences.append(String.format("Files don't match at line %-3d:\nDifference in column 4, size\n", line));
                codeBuilder.append(4);
            } else {
                codeBuilder.append(0);
            }
        line++;
        }

        drawCode = codeBuilder.toString();

        if (differences.toString().equals("")) {
            output = "The files match each other at every line";
        } else {
            output = differences.toString();
        }
    }

    String getOutput() {
        return output;
    }

    String getDrawCode() {
        return drawCode;
    }

    private boolean compare(String item1, String item2) {
        return (item1.equals(item2));
    }

    private String[] readFile(File file) throws IOException, NotValidIPIFormat {
        BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            correctFormat(line.split("\\t"));
            line = br.readLine();
        }

        br.close();
        String fullFile = sb.toString();

        return fullFile.split("\\t");
    }

    private void correctFormat(String[] line) throws NotValidIPIFormat {
        if (!line[0].matches("IPI.+")
                || !line[1].equals(line[0].substring(4, line[0].indexOf('.')))
                || line[2].equals("") || !line[3].matches("[0-9]+")) {
            throw new NotValidIPIFormat("The file is corrupt");
        }
    }
}
