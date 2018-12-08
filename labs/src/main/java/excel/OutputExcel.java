package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import reflection.Reflection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OutputExcel {

    public void writeIntoExcel(String file, int count) {

        Reflection reflection = new Reflection();

        List<Class> classList = reflection.getSubTypes();
        List<Method> fillerMethodList = reflection.getMethods();

        List<String> annotationsValueFiller = reflection.getValueAnnotationFiller();
        List<String> annotationsValueSorter = reflection.getValueAnnotationSorter();

        Workbook book = new XSSFWorkbook();

        for (int sheetIndex = 0; sheetIndex < fillerMethodList.size(); sheetIndex++) {
            Sheet sheet = book.createSheet(annotationsValueFiller.get(sheetIndex));

            List<int[]> arrayList = new ArrayList<>();

            int value = 10;
            int[] array = null;

            for (int rowIndex = 0; rowIndex <= classList.size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex);
                if (rowIndex == 0) {
                    for (int colIndex = 1; colIndex <= count; colIndex++) {
                        value *= 2;
                        array = new int[value];
                        arrayList.add(array);
                        Cell name = row.createCell(colIndex);
                        name.setCellValue(value);
                    }
                } else {
                    for (int colIndex = 0; colIndex <= count; colIndex++) {
                        Cell name = row.createCell(colIndex);
                        if (colIndex == 0) {
                            name.setCellValue(annotationsValueSorter.get(rowIndex - 1));
                        } else {
                            name.setCellValue(getTimeSort(arrayList.get(colIndex - 1), classList.get(rowIndex - 1), fillerMethodList.get(sheetIndex)));
                        }
                    }
                }
            }
            sheet.autoSizeColumn(0);
            drawingChart(sheet, count, annotationsValueSorter);
        }

        try {
            book.write(new FileOutputStream(file));
            book.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTimeSort(int[] array, Class c, Method method) {
        Reflection reflection = new Reflection();

        reflection.doFiller(method, array);
        return reflection.doSort(c, array);

    }

    public void drawingChart(Sheet sheet, int count, List<String> annotationsValueSorter) {
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0,0,0,0, 1,annotationsValueSorter.size() + 2,11,25);

        Chart chart = drawing.createChart(anchor);
        ChartLegend chartLegend = chart.getOrCreateLegend();
        chartLegend.setPosition(LegendPosition.TOP_RIGHT);

        LineChartData data = chart.getChartDataFactory().createLineChartData();

        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, 0 , 1 , count));
        for (int i = 1; i <= annotationsValueSorter.size(); i++) {
            ChartDataSource<Number> ys = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(i, i, 1, count));
            LineChartSeries lineChartSeries = data.addSeries(xs, ys);
            lineChartSeries.setTitle(annotationsValueSorter.get(i - 1));
        }

        chart.plot(data, bottomAxis, leftAxis);
    }

}
