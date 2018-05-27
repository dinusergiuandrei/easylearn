export class SourceModel
{
    content: string;
    fileName: string;
    id: number;
    submissionId: number;

    constructor()
    {
        this.content = null;
        this.fileName = "main";
        this.id = 0;
        this.submissionId = 0;
    }
}