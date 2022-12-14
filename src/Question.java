public class Question {
    private int baseID = 1;
    private int code;
    private String text;
    private String answer;
    private float score;

    public Question(String text, String answer, float score) {
        this.code = baseID++;
        this.text = text;
        this.answer = answer;
        this.score = score;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
