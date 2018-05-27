import { SourceModel } from './source-model';
import { RunModel } from './runs-model';

export class SubmissionModel {


    public date: string;
    public id: number;
    public language: string;
    public mainSource: string;
    public problemId: number;
    public runs: Array<RunModel>;
    public sources: Array<SourceModel>;
    public state: string;
    public userId: number;
}