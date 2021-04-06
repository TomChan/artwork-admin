package com.example.artworkadmin.exporter;

import com.example.artworkadmin.model.Artwork;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArtworkExcelExporter {
    private static final List<String> HEADERS = new ArrayList<>();
    static {
        HEADERS.add("ID");
        HEADERS.add("ArtName");
        HEADERS.add("ArtCat");
        HEADERS.add("ArtCatList");
        HEADERS.add("ArtistName");
        HEADERS.add("ArtistInfo");
        HEADERS.add("CreationDate");
        HEADERS.add("CreationMonth");
        HEADERS.add("CreationYear");
        HEADERS.add("AcquistionDate");
        HEADERS.add("AcquistionMonth");
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

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Artwork> listArtworks;

    public ArtworkExcelExporter(List<Artwork> listArtworks) {
        this.listArtworks = listArtworks;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Artworks");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        for(int i = 0; i < HEADERS.size(); ++i) {
            createCell(row, i, HEADERS.get(i), style);
        }

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof List){
            List<Object> list = (List) value;
            String result = list.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(", ", "[", "]"));
            cell.setCellValue(result);
        } else{
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Artwork artwork : listArtworks) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, artwork.getId(), style);
            createCell(row, columnCount++, artwork.getArtName(), style);
            createCell(row, columnCount++, artwork.getArtCat(), style);
            createCell(row, columnCount++, artwork.getArtCat(), style);
            createCell(row, columnCount++, artwork.getArtistName(), style);
            createCell(row, columnCount++, artwork.getArtistInfo(), style);
            createCell(row, columnCount++, artwork.getCreationDate(), style);
            createCell(row, columnCount++, artwork.getCreationMonth(), style);
            createCell(row, columnCount++, artwork.getCreationYear(), style);
            createCell(row, columnCount++, artwork.getAcquistionDate(), style);
            createCell(row, columnCount++, artwork.getAcquistionMonth(), style);
            createCell(row, columnCount++, artwork.getAcquistionYear(), style);
            createCell(row, columnCount++, artwork.getColor(), style);
            createCell(row, columnCount++, artwork.getColorList(), style);
            createCell(row, columnCount++, artwork.getMedium(), style);
            createCell(row, columnCount++, artwork.getMediumList(), style);
            createCell(row, columnCount++, artwork.getCulture(), style);
            createCell(row, columnCount++, artwork.getCultureList(), style);
            createCell(row, columnCount++, artwork.getBrg(), style);
            createCell(row, columnCount++, artwork.getCategory(), style);
            createCell(row, columnCount++, artwork.getCategorylist(), style);
            createCell(row, columnCount++, artwork.getDisplayImage(), style);
            createCell(row, columnCount++, artwork.getImgLoc(), style);
            createCell(row, columnCount++, artwork.getSoundLoc(), style);
            createCell(row, columnCount++, artwork.getDescription(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

}
