package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
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

        Workbook book = new HSSFWorkbook();

        for (int i = 0; i < fillerMethodList.size(); i++) {
            Sheet sheet = book.createSheet(annotationsValueFiller.get(i));

            List<int[]> arrayList = new ArrayList<>();

            int value = 1;
            int[] array = null;

            for (int k = 0; k <= classList.size(); k++) {
                Row row = sheet.createRow(k);
                if (k == 0) {
                    for (int j = 1; j <= count; j++) {
                        value *= 10;
                        array = new int[value];
                        arrayList.add(array);
                        Cell name = row.createCell(j);
                        name.setCellValue(value);
                    }
                } else {
                    for (int j = 0; j <= count; j++) {
                        Cell name = row.createCell(j);
                        if (j == 0) {
                            name.setCellValue(annotationsValueSorter.get(k - 1));
                        } else {
                            name.setCellValue(getTimeSort(arrayList.get(j - 1), classList.get(k - 1), fillerMethodList.get(i)));
                        }
                    }
                }

            }

            sheet.autoSizeColumn(0);

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


}
