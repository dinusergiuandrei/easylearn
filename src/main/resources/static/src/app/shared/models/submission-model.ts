import { SourceModel } from './source-model';
import { RunModel } from './run-model';

export class SubmissionModel {

    public compileOut: string;
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
