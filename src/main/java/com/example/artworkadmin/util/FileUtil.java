package com.example.artworkadmin.util;

import com.example.artworkadmin.model.Artwork;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {
    private static String csvExtension = "csv";
    private static final List<String> HEADERS = new ArrayList<>();

    static {
        HEADERS.add("ID");
        HEADERS.add("ArtTitle");
        HEADERS.add("ArtLoc");
        HEADERS.add("ArtistName");
        HEADERS.add("ArtistInfo");
        HEADERS.add("CreationYear");
        HEADERS.add("AcquistionYear");
        HEADERS.add("Color");
        HEADERS.add("ColorList");
        HEADERS.add("Medium");
        HEADERS.add("MediumList");
        HEADERS.add("Culture");
        HEADERS.add("CultureList");
        HEADERS.add("BRG");
        HEADERS.add("Category");
        HEADERS.add("CategoryList");
        HEADERS.add("DisplayImage");
        HEADERS.add("ImgLoc");
        HEADERS.add("SoundLoc");
        HEADERS.add("Description");
    }

    private static String convertList(List<String> list) {
        return list.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(","));
    }

    private static List<String> parseList(String data) {
        return Arrays.asList(data.split(","));
    }

    public static void exportToCsv(Writer writer, List<Artwork> customers) throws IOException {

        try (CSVPrinter csvPrinter = new CSVPrinter(writer,
                CSVFormat.DEFAULT.withHeader(HEADERS.toArray(new String[]{})));) {
            for (Artwork artwork : customers) {
                List<String> data = Arrays.asList(
                        String.valueOf(artwork.getId()),
                        artwork.getArtTitle(),
                        artwork.getArtLoc(),
                        artwork.getArtistName(),
                        artwork.getArtistInfo(),
                        artwork.getCreationYear(),
                        artwork.getAcquistionYear(),
                        artwork.getColor(),
                        convertList(artwork.getColorList()),
                        artwork.getMedium(),
                        convertList(artwork.getMediumList()),
                        artwork.getCulture(),
                        convertList(artwork.getCultureList()),
                        artwork.getBrg(),
                        artwork.getCategory(),
                        convertList(artwork.getCategorylist()),
                        artwork.getDisplayImage(),
                        artwork.getImgLoc(),
                        artwork.getSoundLoc(),
                        artwork.getDescription());

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Artwork> parseCsvFile(InputStream is) {
        BufferedReader fileReader = null;
        CSVParser csvParser = null;

        List<Artwork> artworks = new ArrayList<>();

        try {
            fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Artwork artwork = new Artwork(
                        Long.parseLong(csvRecord.get("ID")),
                        csvRecord.get("ArtTitle"),
                        csvRecord.get("ArtLoc"),
                        csvRecord.get("ArtistName"),
                        csvRecord.get("ArtistInfo"),
                        csvRecord.get("CreationYear"),
                        csvRecord.get("AcquistionYear"),
                        csvRecord.get("Color"),
                        parseList(csvRecord.get("ColorList")),
                        csvRecord.get("Medium"),
                        parseList(csvRecord.get("MediumList")),
                        csvRecord.get("Culture"),
                        parseList(csvRecord.get("CultureList")),
                        csvRecord.get("BRG"),
                        csvRecord.get("Category"),
                        parseList(csvRecord.get("CategoryList")),
                        csvRecord.get("DisplayImage"),
                        csvRecord.get("ImgLoc"),
                        csvRecord.get("SoundLoc"),
                        csvRecord.get("Description"));

                artworks.add(artwork);
            }



        } catch (Exception e) {
            System.out.println("Reading CSV Error!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvParser.close();
            } catch (IOException e) {
                System.out.println("Closing fileReader/csvParser Error!");
                e.printStackTrace();
            }
        }

        return artworks;
    }

    public static boolean isCSVFile(MultipartFile file) {
        String extension = file.getOriginalFilename().split("\\.")[1];

        if (!extension.equals(csvExtension)) {
            return false;
        }

        return true;
    }
}
