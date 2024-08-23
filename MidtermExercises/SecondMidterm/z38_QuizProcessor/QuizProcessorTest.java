package z38_QuizProcessor;
import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class InvalidQuizException extends Exception{
    public InvalidQuizException(String message) {
        super(message);
    }
}

class Quiz{

    String IDX;
    List<String> correct_answers;
    List<String> student_answers;

    public Quiz(String IDX, List<String> correct_answers, List<String> student_answers) throws InvalidQuizException{
        if(correct_answers.size() != student_answers.size()){
            throw new InvalidQuizException("A quiz must have same number of correct and selected answers");
        }

        this.IDX = IDX;
        this.correct_answers = correct_answers;
        this.student_answers = student_answers;
    }

    public static Quiz createQuiz(String line) throws InvalidQuizException{

        //parts[0];parts[1];parts[2]
        //151020;A, B, C;A, C, C

        String[] parts = line.split(";");
        String index = parts[0];

        String correct_answers = parts[1];
        String selected_answers = parts[2];

        List<String> correct_answers_list = new ArrayList<>(Arrays.asList(correct_answers.split(", "))).stream().toList();
        List<String> selected_answers_list = new ArrayList<>(Arrays.asList(selected_answers.split(", "))).stream().toList();

        return new Quiz(index, correct_answers_list, selected_answers_list);
    }

    public double points_answers(){

        double points = 0;
        for (int i = 0; i < student_answers.size(); i++) {
            if(student_answers.get(i).equals(correct_answers.get(i))){
                points += 1;
            }
            else {
                points -= 0.25;
            }
        }
        return points;
    }

    public String getIDX() {
        return IDX;
    }

    @Override
    public String toString() {
        return String.format("%s --> %.2f", IDX, points_answers());
    }
}

class QuizProcessor{

    public static Map<String, Double> processAnswers(InputStream is){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        return bufferedReader.lines()
                .map(line -> {
                    try {
                        return Quiz.createQuiz(line);
                    } catch (InvalidQuizException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Quiz::getIDX,  //key(index)-value (double points)
                        Quiz::points_answers,
                        Double::sum,
                        TreeMap::new));
    }

}

class QuizProcessorTest {
    public static void main(String[] args) {
        QuizProcessor.processAnswers(System.in).forEach((k, v) -> System.out.printf("%s -> %.2f%n", k, v));
    }
}