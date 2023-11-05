import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OnlineExam extends JFrame implements ActionListener {
    private String name;
    private int count;
    private int currentQuestion;
    private int choice;
    private JLabel titleLabel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton;
    private ButtonGroup buttonGroup;
    private int[] answers;

    private String[][] questions = {
        // Questions for Basic Computer
        { "What does CPU stand for?", "1. Central Processing Unit", "2. Computer Personal Unit", "3. Central Process Unit", "4. Central Processor Unit" },
        { "Which of the following is a storage device?", "1. Hard Disk", "2. Monitor", "3. Keyboard", "4. Mouse" },
        { "What is the full form of RAM?", "1. Random Access Memory", "2. Read-Only Memory", "3. Random Area Memory", "4. Read Area Memory" },
        { "What does HTML stand for?", "1. Hyper Text Markup Language", "2. High Tech Multi Language", "3. Hyper Transfer Markup Language", "4. Hard Text Multi Language" },
        { "Which of the following is a programming language?", "1. Python", "2. Android", "3. Pytho", "4. Google" }
    };

    public OnlineExam(String name) {
        this.name = name;
        this.count = 0;
        this.currentQuestion = 0;
        this.choice = 0;
        this.answers = new int[5];

        setTitle("Oasis Online Examination");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        titleLabel = new JLabel("Welcome " + name + " to Oasis Online Examination");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        options = new JRadioButton[4];
        buttonGroup = new ButtonGroup();

        JPanel questionsPanel = new JPanel(new GridLayout(6, 1));
        questionLabel = new JLabel();
        questionsPanel.add(questionLabel);

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            buttonGroup.add(options[i]);
            questionsPanel.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        questionsPanel.add(nextButton);

        add(questionsPanel, BorderLayout.CENTER);

        showQuestion();
    }
    public void showQuestion() {
        if (currentQuestion >= 5) {
            displayResult();
            return;
        }
    
        titleLabel.setText("Welcome " + name + " to Oasis Online Examination");
        setTitle("Online Exam - Question " + (currentQuestion + 1));
        questionLabel.setText("Question " + (currentQuestion + 1) + ": " + questions[currentQuestion][0]);
    
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[currentQuestion][i + 1]);
            options[i].setSelected(false); // Unselect all options
        }
    }
    

    public void displayResult() {
        setTitle("Online Exam - Result");
        titleLabel.setText("<html><p style='font-size:16px;'>Congrats " + name + " You scored " + count + " marks out of 5!</p></html>");
    
        // Check if it's not question 5 before hiding it
        if (currentQuestion != 4) {
            questionLabel.setText("");
            for (int i = 0; i < 4; i++) {
                options[i].setVisible(false);
            }
        }
        nextButton.setText("Exit");
    }
      

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (currentQuestion < 5) {
                for (int i = 0; i < 4; i++) {
                    if (options[i].isSelected()) {
                        choice = i + 1;
                        break;
                    }
                }
                answers[currentQuestion] = choice;
                count += (choice == getCorrectAnswer()) ? 1 : 0;
                currentQuestion++;
                showQuestion();
            } else {
                System.exit(0);
            }
        }
    }

    public int getCorrectAnswer() {
        // The correct answers are stored in the original questions array.
        // 1st answer is always correct.
        return 1;
    }

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter user name:");
        OnlineExam frame = new OnlineExam(name);
        frame.setVisible(true);
    }
}
