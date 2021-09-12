package su.scmb.lvl3lesson3.utils;

import su.scmb.lvl3lesson3.exceptions.FileIsPresent;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class PrepareData {
    public static void createFile(String path, String fileName) {
        Path filePath = Paths.get(path + fileName);
        if (Files.exists(filePath)) {
            try {
                throw new FileIsPresent();
            } catch (FileIsPresent fileIsPresent) {
                fileIsPresent.printStackTrace();
            }
        } else {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFile(String path, String fileName) {
        try {
            Files.deleteIfExists(Paths.get(path + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addDataInFile(String path, String fileName, String data, Boolean override) {
        Path filePath = Paths.get(path + fileName);
        if (Files.exists(filePath) && Files.isWritable(filePath)) {
            if (override) {
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
                    bufferedWriter.newLine();
                    bufferedWriter.append(data);
                    bufferedWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static byte[] getDataInFile(String path, String fileName) {
        Path filePath = Paths.get(path + fileName);
        if (Files.exists(filePath) && Files.isReadable(filePath)) {
            try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(filePath))) {
                return inputStream.readAllBytes();
            } catch (IOException e) {

            }
        }
        return null;
    }

    public static String randomData(int length) {
        final StringBuilder stringBuilder = new StringBuilder();
        String an = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        long d;
        for (int i = 0; i < length; i++) {
            d = Math.round(Math.random() * (an.length() - 1));
            stringBuilder.append(an.charAt((int) d));
        }
        return stringBuilder.toString();
    }
    public static void manyFileToOne(List<String> arrayStringPath, String targetPathFile) {
        if (arrayStringPath.size() != 0) {
            if (!Files.exists(Paths.get(targetPathFile))) {
                try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Files.createFile(Paths.get(targetPathFile)), StandardOpenOption.APPEND)) {
                    for (int i = 0; i < arrayStringPath.size(); i++) {
                        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arrayStringPath.get(i)))) {
                            Stream lines = bufferedReader.lines();
                            lines.forEach((line) -> {
                                try {
                                    bufferedWriter.write((String) line);
                                    bufferedWriter.write(System.lineSeparator());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("File allready exists");
            }
        }
    }
    public static void pagingData(String pathPlusFileName) {
        Path pathFile = Paths.get(pathPlusFileName);
        if (pathFile.toFile().exists()) {
            try(BufferedReader bufferedReader = Files.newBufferedReader(pathFile)) {
                char[] size = new char[1800];
                int i;
                while((i = bufferedReader.read(size)) != -1) {
                    System.out.print(i + " ");
                    System.out.println(size);

                }
            } catch (IOException e) {

            }
        }


    }

}
