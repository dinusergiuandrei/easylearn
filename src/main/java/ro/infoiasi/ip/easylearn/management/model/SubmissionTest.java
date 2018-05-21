package ro.infoiasi.ip.easylearn.management.model;

public class SubmissionTest {
	private int id;
	private long submissionID;
    private long testID;
    private int runTimeMS;
    private int memoryBytes;
    private String status;

    public SubmissionTest() {}
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public long getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(long submissionID) {
        this.submissionID = submissionID;
    }

    public long getTestID() {
        return testID;
    }

    public void setTestID(long testID) {
        this.testID = testID;
    }

	public int getRunTimeMS() {
		return runTimeMS;
	}

	public void setRunTimeMS(int runTimeMS) {
		this.runTimeMS = runTimeMS;
	}

	public int getMemoryBytes() {
		return memoryBytes;
	}

	public void setMemoryBytes(int memoryBytes) {
		this.memoryBytes = memoryBytes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}

