package ipiCompareTool;

import java.io.*;

class IPIComparer {
    private String[] fileContent1;
    private String[] fileContent2;

    IPIComparer(File file1, File file2) throws IOException, NotValidIPIFormat {
        fileContent1 = readFile(file1);
        fileContent2 = readFile(file2);

    }

    public String[] getFileContent1() {
        return fileContent1;
    }

    public String[] getFileContent2() {
        return fileContent2;
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
