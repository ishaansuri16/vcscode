import { Moment } from 'moment';

export interface IPpUserMaster {
    id?: number;
    name?: string;
    email?: string;
    mobile?: string;
    pricePointValue?: number;
    param1?: string;
    param2?: string;
    param3?: string;
    created?: Moment;
    modifiedon?: Moment;
}

export class PpUserMaster implements IPpUserMaster {
    constructor(
        public id?: number,
        public name?: string,
        public email?: string,
        public mobile?: string,
        public pricePointValue?: number,
        public param1?: string,
        public param2?: string,
        public param3?: string,
        public created?: Moment,
        public modifiedon?: Moment
    ) {}
}
