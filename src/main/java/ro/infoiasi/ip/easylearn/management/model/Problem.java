package ro.infoiasi.ip.easylearn.management.model;

public class Problem {
    private Long id;
    private Long userId;
    private Long categoryId;
    private String title;
    private String description;
    private String requirement;
    private String input;
    private String output;
    private String restrictions;
    private int difficulty;
    private String dataType;
    private String inputExample;
    private String outputExample;
    private String inputFile;
    private String outputFile;
    private long memoryLimit;
    private double timeLimit;

    public Problem() {};

    public Problem(Long id, Long userId, Long categoryId, String title, String description, String requirement, String input, String output, String restrictions, int difficulty, String dataType, String inputExample, String outputExample, String inputFile, String outputFile, long memoryLimit, double timeLimit) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.requirement = requirement;
        this.input = input;
        this.output = output;
        this.restrictions = restrictions;
        this.difficulty = difficulty;
        this.dataType = dataType;
        this.inputExample = inputExample;
        this.outputExample = outputExample;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.memoryLimit = memoryLimit;
        this.timeLimit = timeLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getInputExample() {
        return inputExample;
    }

    public void setInputExample(String inputExample) {
        this.inputExample = inputExample;
    }

    public String getOutputExample() {
        return outputExample;
    }

    public void setOutputExample(String outputExample) {
        this.outputExample = outputExample;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public long getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String toValuesString()
    {
    	StringBuilder stringBuilder = new StringBuilder(1000);

    	stringBuilder.append(Long.toString(id)); stringBuilder.append(",");
    	stringBuilder.append(Long.toString(userId)); stringBuilder.append(",");
        stringBuilder.append(Long.toString(categoryId)); stringBuilder.append(",");
        stringBuilder.append("'"); stringBuilder.append(title); stringBuilder.append("'"); stringBuilder.append(",");
        stringBuilder.append("'"); stringBuilder.append(description); stringBuilder.append("'"); stringBuilder.append(",");
        stringBuilder.append("'"); stringBuilder.append(requirement); stringBuilder.append("'"); stringBuilder.append(",");
        stringBuilder.append("'"); stringBuilder.append(input); stringBuilder.append("'"); stringBuilder.append(",");
        stringBuilder.append("'"); stringBuilder.append(output); stringBuilder.append("'"); stringBuilder.append(",");
        stringBuilder.append("'"); stringBuilder.append(restrictions); stringBuilder.append("'"); stringBuilder.append(",");
        stringBuilder.append(Long.toString(difficulty)); stringBuilder.append(",");
    	stringBuilder.append("'"); stringBuilder.append(dataType); stringBuilder.append("'"); stringBuilder.append(",");
    	stringBuilder.append("'"); stringBuilder.append(inputExample); stringBuilder.append("'"); stringBuilder.append(",");
    	stringBuilder.append("'"); stringBuilder.append(outputExample); stringBuilder.append("'"); stringBuilder.append(",");
    	stringBuilder.append("'"); stringBuilder.append(inputFile); stringBuilder.append("'"); stringBuilder.append(",");
    	stringBuilder.append("'"); stringBuilder.append(outputExample); stringBuilder.append("'"); stringBuilder.append(",");
    	stringBuilder.append(Long.toString(memoryLimit)); stringBuilder.append(",");
    	stringBuilder.append(Double.toString(timeLimit));

    	return stringBuilder.toString();
    }
}

