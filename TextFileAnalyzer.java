package task1;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class TextFileAnalyzer 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        
        File file = new File(filePath);
        
        if (!file.exists())
        {
            System.out.println("File not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;
            Map<String, Integer> wordFrequency = new HashMap<>();
            Pattern wordPattern = Pattern.compile("\\b\\w+\\b");

            while ((line = reader.readLine()) != null)
            {
                lineCount++;
                charCount += line.length();
                Matcher matcher = wordPattern.matcher(line);
                while (matcher.find())
                {
                    String word = matcher.group().toLowerCase();
                    wordCount++;
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }

            System.out.println("Total Lines: " + lineCount);
            System.out.println("Total Words: " + wordCount);
            System.out.println("Total Characters: " + charCount);

            String mostFrequentWord = Collections.max(wordFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("Most Frequent Word: " + mostFrequentWord);
        }
        catch (IOException e) 
        {
            System.out.println("Error reading file.");
        }
    }
}
