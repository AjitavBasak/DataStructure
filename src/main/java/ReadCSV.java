
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ReadCSV {
    public static void main(String args[]) throws IOException {

        System.out.println("***************************************************");
        readDataLineByLineSet(System.getProperty("user.dir") + "/src/main/resources/test.csv");
        readDataLineByLineQueue(System.getProperty("user.dir") + "/src/main/resources/test.csv");

        readDataLineByLineList(System.getProperty("user.dir") + "/src/main/resources/test.csv");

        readDataLineByLineJson(System.getProperty("user.dir") + "/src/main/resources/test.csv");
        System.out.println("***************************************************");

    }
    public static void readDataLineByLineJson(String file) throws IOException {
        Path pathToFile = Paths.get(file);
        File input=new File(file);
        File output=new File(System.getProperty("user.dir") +"/src/main/resources/jsonOutput.json");
        long start = System.nanoTime();
        List<Map<?, ?>> data = readObjectsFromCsv(input);
        writeAsJson(data, output);
        long end = System.nanoTime();
        System.out.println(data.size()+" row Takes time = "+(end-start)+ " Nano seconds If we use JSON");
    }
    public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();git add .
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(file);

        return mappingIterator.readAll();
    }
    public static void writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, data);
    }
    public static void readDataLineByLineList(String file) {
        Path pathToFile = Paths.get(file);
        List<String> pocCSV = new ArrayList<>();
        long start = System.nanoTime();
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();// to remove the header
            line = br.readLine();
            while (line != null) {
                pocCSV.add(line);
                line = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println(pocCSV.size()+" row Takes time = "+(end-start)+ " Nano seconds If we use List");
    }
    public static void readDataLineByLineQueue(String file) {
        Path pathToFile = Paths.get(file);
        Queue<String> pocCSV = new PriorityQueue<>();
        long start = System.nanoTime();
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();// to remove the header
            line = br.readLine();
            while (line != null) {
                pocCSV.add(line);
                line = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println(pocCSV.size()+" row Takes time = "+(end-start)+ " Nano seconds If we use Queue");
    }

    public static void readDataLineByLineSet(String file) {
        Path pathToFile = Paths.get(file);
        Set<String> pocCSV = new HashSet<>();
        long start = System.nanoTime();
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();// to remove the header
            line = br.readLine();
            while (line != null) {
                pocCSV.add(line);
                line = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println(pocCSV.size()+" row Takes time = "+(end-start)+ " Nano seconds If we use Set");
    }

}
