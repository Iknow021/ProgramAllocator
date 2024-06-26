package com.nkosi.programallocator.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Random;
@Component
@RequiredArgsConstructor
public class CodeGeneratorUtil {

    private static final int CURRENT_YEAR_LAST_THREE_DIGITS = Year.now().getValue() % 1000;
    private static final Random RANDOM = new Random();

    public String generateStudentID() {
        StringBuilder id = new StringBuilder("STU");
        id.append(CURRENT_YEAR_LAST_THREE_DIGITS);
        for (int i = 0; i < 4; i++) {
            id.append(RANDOM.nextInt(10));
        }
        id.append((char) ('A' + RANDOM.nextInt(26)));
        for (int i = 0; i < 2; i++) {
            id.append(RANDOM.nextInt(10));
        }
        return id.toString();
    }

    public  String generateProgramID(String name) {
        return getCode(name);
    }

    public  String generateFacultyID(String name) {
        return getCode(name);
    }

    public String generateDepartmentID(String name) {
        return getCode(name);
    }

    private static String getCode(String name) {
        StringBuilder id = new StringBuilder(name.substring(0, Math.min(3, name.length())).toUpperCase());
        for (int i = 0; i < 3; i++) {
            id.append(RANDOM.nextInt(10));
        }
        return id.toString();
    }
}
