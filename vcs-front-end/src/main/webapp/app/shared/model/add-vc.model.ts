import { Moment } from 'moment';

export interface IAddVCMaster {
    destinationmid?: number;
    amount?: number;
    created?: Moment;
    modifiedon?: Moment;
}

export class AddVCMaster implements IAddVCMaster {
    constructor(
        public destinationmid?: number,
        public amount?: number,
        public created?: Moment,
        public modifiedon?: Moment
    ) {}
}
