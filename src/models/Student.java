package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Student {
    private String name;
    private String group;
    private int totalGradeDz;
    private int totalGradeUp;

    public Student(String name, String group, int totalGradeDz, int totalGradeUp) {
        this.name = name;
        this.group = group;
        this.totalGradeDz = totalGradeDz;
        this.totalGradeUp = totalGradeUp;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getTotalGradeDz() {
        return totalGradeDz;
    }

    public int getTotalGradeUp() {
        return totalGradeUp;
    }
}

