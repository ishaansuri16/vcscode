import { Moment } from 'moment';

export interface IMerchant {
    id?: number;
    name?: string;
    displayName?: string;
    description?: string;
    mid?: string;
    params?: string;
    param1?: string;
    param2?: string;
    param3?: string;
    midSecret?: string;
    createdBy?: string;
    created?: Moment;
    modifiedon?: Moment;
    pincode?: string;
    address?: string;
    isenabled?: boolean;
}

export class Merchant implements IMerchant {
    constructor(
        public id?: number,
        public name?: string,
        public displayName?: string,
        public description?: string,
        public mid?: string,
        public params?: string,
        public param1?: string,
        public param2?: string,
        public param3?: string,
        public midSecret?: string,
        public createdBy?: string,
        public created?: Moment,
        public modifiedon?: Moment,
        public pincode?: string,
        public address?: string,
        public isenabled?: boolean
    ) {
        this.isenabled = false;
    }
}
