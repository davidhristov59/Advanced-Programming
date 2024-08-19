package z23_Quiz;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

abstract class Question implements Comparable<Question>{

    String nameQuestion;
    int points;

    public Question(String nameQuestion, int points) {
        this.nameQuestion = nameQuestion;
        this.points = points;
    }

    abstract double answer(String studentAnswer);

    @Override
    public String toString() {
        return String.format("%s %d", nameQuestion, points);
    }

    @Override
    public int compareTo(Question o) {
        return Integer.compare(this.points, o.points);
    }

    public String getNameQuestion() {
        return nameQuestion;
    }

    public int getPoints() {
        return points;
    }
}

class TrueFalse extends Question{

    private final boolean answer;

    public TrueFalse(String nameQuestion, int points, boolean answer) {
        super(nameQuestion, points);
        this.answer = answer;
    }

    @Override
    double answer(String studentAnswer) {
        return (answer == Boolean.parseBoolean(studentAnswer)) ? points : 0.0;
        //ako e tocen odgovorot vrati gi points
    }

    @Override
    public String toString() {
//        True/False Question: Question3 Points: 2 Answer: false
        return String.format("True/False Question: %s Points: %d Answer: %s", nameQuestion, points, answer);
    }
}

class MultipleChoice extends Question{

    String answer;

    public MultipleChoice(String nameQuestion, int points, String answer) {
        super(nameQuestion, points);
        this.answer = answer;
    }

    @Override
    double answer(String studentAnswer) {
//        Multiple Choice Question: Question1 Points 3 Answer: E

        return answer.equals(studentAnswer) ? points : (points * -0.2f); //vrati gi points ako ne vrati negativni poeni
    }

    @Override
    public String toString() {
        return String.format("Multiple Choice Question: %s Points %d Answer: %s",nameQuestion,points,answer);
    }
}

class InvalidOperationException extends Exception{

    public InvalidOperationException(String message) {
        super(message);
    }
}

class QuestionFactory{

    static List<String > ALLOWED_ANSWERS = Arrays.asList("A", "B", "C", "D", "E");

    static Question createQuestion(String line) throws InvalidOperationException{

        //      TF;text;points;answer (answer може да биде true или false)
//        MC;text;points;answer A/B/C/D/E

        String[] parts = line.split(";");

        String typeQuestion = parts[0];
        String textQuestion = parts[1];
        int points = Integer.parseInt(parts[2]);
        String answer = parts[3];

        if(typeQuestion.equals("MC")){

            if(!ALLOWED_ANSWERS.contains(answer)){
                throw new InvalidOperationException(String.format("%s is not allowed option for this question", answer)); //NIKAKO TRY/CATCH KAJ STO GI FRLAME EXCEPTIONS. SE SPRAVUVAME VO METODOT
            }

            return new MultipleChoice(textQuestion, points, answer);
        }
        else { //vraka TF

            return new TrueFalse(textQuestion, points, Boolean.parseBoolean(answer));
        }
    }
}

class Quiz{

    List<Question> questions;

    public Quiz(){
        questions = new ArrayList<>();
    }

    void addQuestion(String questionData){

        try {
            questions.add(QuestionFactory.createQuestion(questionData));
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    void printQuiz(OutputStream os){

        PrintWriter printWriter = new PrintWriter(os);

        questions.stream()
                .sorted(Comparator.comparing(Question::getPoints).reversed())
                .forEach(line -> printWriter.println(line));

        printWriter.flush();

    }

    public void answerQuiz(List<String> answers, OutputStream os){
        PrintWriter printWriter = new PrintWriter(os);

        double sum = 0;
        for (int i = 0; i < answers.size(); i++) {
            double points = questions.get(i).answer(answers.get(i)); //i-to prasanje odgovori so i-ti odgovor
            printWriter.println(String.format("%d. %.2f", i+1, points));
            sum += points; //vo sumata gi dodavame poenite za odgovorenoto prasanje
        }

        printWriter.println(String.format("Total points: %.2f", sum));

        printWriter.flush();
    }

}

public class QuizTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Quiz quiz = new Quiz();

        int questions = Integer.parseInt(sc.nextLine());

        for (int i=0;i<questions;i++) {
            quiz.addQuestion(sc.nextLine());
        }

        List<String> answers = new ArrayList<>();

        int answersCount =  Integer.parseInt(sc.nextLine());

        for (int i=0;i<answersCount;i++) {
            answers.add(sc.nextLine());
        }

        int testCase = Integer.parseInt(sc.nextLine());

        if (testCase==1) {
            quiz.printQuiz(System.out);
        } else if (testCase==2) {
            quiz.answerQuiz(answers, System.out);
        } else {
            System.out.println("Invalid test case");
        }
    }
}
