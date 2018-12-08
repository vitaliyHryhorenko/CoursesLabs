import excel.OutputExcel;
import output.Output;
import reflection.Reflection;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        new OutputExcel().writeIntoExcel("D:\\study\\CoursesLabs\\output.xlsx", 10);

    }
}
