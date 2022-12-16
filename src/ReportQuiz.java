public class ReportQuiz {
    private int codeQuiz;
    private float score;

    public ReportQuiz(int codeQuiz, float score) {
        this.codeQuiz = codeQuiz;
        this.score = score;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getCodeQuiz() {
        return codeQuiz;
    }

    public void setCodeQuiz(int codeQuiz) {
        this.codeQuiz = codeQuiz;
    }
}
