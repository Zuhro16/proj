package models;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Парсим CSV-файл и получаем список студентов
        CSVParser csvParser = new CSVParser();
        List<Student> students = csvParser.parseCsv("src/models/basicprogramming_2.csv");

        // Создаем мапу, в которой ключом будет имя студента, а значением - список оценок
        Map<String, List<Grade>> gradesMap = new HashMap<>();

        // Заполняем мапу оценками
        for (Student student : students) {
            List<Grade> grades = new ArrayList<>();
            grades.add(new Grade(student, new Topic("ДЗ", 1), student.getTotalGradeDz()));
            grades.add(new Grade(student, new Topic("Упражнения", 2), student.getTotalGradeUp()));
            gradesMap.put(student.getName(), grades);
        }

        Comparator<Map.Entry<String, List<Grade>>> comparator = (e1, e2) -> {
            int sum1 = 0;
            int count1 = 0;
            for (Grade grade : e1.getValue()) {
                sum1 += grade.getGrade();
                count1++;
            }
            double avg1 = sum1 / (double) count1;

            int sum2 = 0;
            int count2 = 0;
            for (Grade grade : e2.getValue()) {
                sum2 += grade.getGrade();
                count2++;
            }
            double avg2 = sum2 / (double) count2;

            return Double.compare(avg2, avg1);
        };
        List<Map.Entry<String, List<Grade>>> sortedGrades = new ArrayList<>(gradesMap.entrySet());
        sortedGrades.sort(comparator);

        // Выводим результаты
        for (Map.Entry<String, List<Grade>> entry : sortedGrades) {
            System.out.println("Студент: " + entry.getKey());
            System.out.println("Тип задания с самым высоким баллом:");
            int maxGrade = 0;
            Topic maxGradeTopic = null;
            for (Grade grade : entry.getValue()) {
                if (grade.getGrade() > maxGrade) {
                    maxGrade = grade.getGrade();
                    maxGradeTopic = grade.getTopic();
                }
            }
            System.out.println("  - " + maxGradeTopic.getName() + " (" + maxGrade + ")");

            System.out.println("Тип задания с самым низким баллом:");
            int minGrade = Integer.MAX_VALUE;
            Topic minGradeTopic = null;
            for (Grade grade : entry.getValue()) {
                if (grade.getGrade() < minGrade) {
                    minGrade = grade.getGrade();
                    minGradeTopic = grade.getTopic();
                }
            }
            System.out.println("  - " + minGradeTopic.getName() + " (" + minGrade + ")");

            System.out.println();
        }
    }
}


