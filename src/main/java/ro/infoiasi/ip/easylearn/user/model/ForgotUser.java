package ro.infoiasi.ip.easylearn.user.model;

public class ForgotUser {
    private Long id;
    private String email;
    private String secretAnswer;
    private String secretQuestion;

    public ForgotUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    @Override
    public String toString() {
        return "Forgot: "+
                id +" | "+email+" | "+" | "+secretQuestion+" | "+secretAnswer;
    }
}
