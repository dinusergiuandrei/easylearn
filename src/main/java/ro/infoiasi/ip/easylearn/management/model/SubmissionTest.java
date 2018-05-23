package ro.infoiasi.ip.easylearn.management.model;

public class SubmissionTest {
	private Long id;
	private Long submissionId;
    private Long testId;
    private int runTimeMS;
    private int memoryBytes;
    private String status;

    public SubmissionTest() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
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

	@Override
	public String toString() {
		return "SubmissionTest{" +
				"id=" + id +
				", submissionId=" + submissionId +
				", testId=" + testId +
				", runTimeMS=" + runTimeMS +
				", memoryBytes=" + memoryBytes +
				", status='" + status + '\'' +
				'}';
	}
}

