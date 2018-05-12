package ro.infoiasi.ip.easylearn.user.model;

public class Problem {

    private long problemID;
    private long authorID;
    private String titlu;
    private String descriere;
    private String cerinta;
    private String date_intrare;
    private String date_iesire;
    private String restrictii;
    private int dificultate;
    private int categorie;
    private String tip_date;
    private String exemplu_intrare;
    private String exemplu_iesire;
    private String input_file;
    private String output_file;
    private long max_memory;
    private double max_time;

    public Problem(long problemID, long authorID, String titlu, String descriere, String cerinta, String date_intrare,
                   String date_iesire, String restrictii, int dificultate, int categorie, String tip_date,
                   String exemplu_intrare, String exemplu_iesire, String input_file, String output_file, long max_memory,
                   double max_time) {

        super();
        this.problemID = problemID;
        this.authorID = authorID;
        this.titlu = titlu;
        this.descriere = descriere;
        this.cerinta = cerinta;
        this.date_intrare = date_intrare;
        this.date_iesire = date_iesire;
        this.restrictii = restrictii;
        this.dificultate = dificultate;
        this.categorie = categorie;
        this.tip_date = tip_date;
        this.exemplu_intrare = exemplu_intrare;
        this.exemplu_iesire = exemplu_iesire;
        this.input_file = input_file;
        this.output_file = output_file;
        this.max_memory = max_memory;
        this.max_time = max_time;
    }

    public long getProblemID() {
        return problemID;
    }

    public void setProblemID(long problemID) {
        this.problemID = problemID;
    }

    public long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(long authorID) {
        this.authorID = authorID;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getCerinta() {
        return cerinta;
    }

    public void setCerinta(String cerinta) {
        this.cerinta = cerinta;
    }

    public String getDate_intrare() {
        return date_intrare;
    }

    public void setDate_intrare(String date_intrare) {
        this.date_intrare = date_intrare;
    }

    public String getDate_iesire() {
        return date_iesire;
    }

    public void setDate_iesire(String date_iesire) {
        this.date_iesire = date_iesire;
    }

    public String getRestrictii() {
        return restrictii;
    }

    public void setRestrictii(String restrictii) {
        this.restrictii = restrictii;
    }

    public int getDificultate() {
        return dificultate;
    }

    public void setDificultate(int dificultate) {
        this.dificultate = dificultate;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public String getTip_date() {
        return tip_date;
    }

    public void setTip_date(String tip_date) {
        this.tip_date = tip_date;
    }

    public String getExemplu_intrare() {
        return exemplu_intrare;
    }

    public void setExemplu_intrare(String exemplu_intrare) {
        this.exemplu_intrare = exemplu_intrare;
    }

    public String getExemplu_iesire() {
        return exemplu_iesire;
    }

    public void setExemplu_iesire(String exemplu_iesire) {
        this.exemplu_iesire = exemplu_iesire;
    }

    public String getInput_file() {
        return input_file;
    }

    public void setInput_file(String input_file) {
        this.input_file = input_file;
    }

    public String getOutput_file() {
        return output_file;
    }

    public void setOutput_file(String output_file) {
        this.output_file = output_file;
    }

    public long getMax_memory() {
        return max_memory;
    }

    public void setMax_memory(long max_memory) {
        this.max_memory = max_memory;
    }

    public double getMax_time() {
        return max_time;
    }

    public void setMax_time(double max_time) {
        this.max_time = max_time;
    }

}

