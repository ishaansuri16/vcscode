import { Moment } from 'moment';

export const enum TransactionType {
    CREDIT = 'CREDIT',
    DEBIT = 'DEBIT'
}

export const enum TransactionStatus {
    SUCCESS = 'SUCCESS',
    FAIL = 'FAIL'
}

export interface IPpUserHistory {
    id?: number;
    userId?: string;
    lastUpdatedPlaypoint?: number;
    description?: string;
    transactionType?: TransactionType;
    pricePointValue?: number;
    source?: string;
    transactionStatus?: TransactionStatus;
    transactionDate?: Moment;
    orderId?: string;
    userAction?: string;
    productId?: string;
    productType?: string;
    producDetail?: string;
    walletId?: string;
    mid?: string;
    param1?: string;
    param2?: string;
    param3?: string;
    params?: string;
    created?: Moment;
    modifiedon?: Moment;
}

export class PpUserHistory implements IPpUserHistory {
    constructor(
        public id?: number,
        public userId?: string,
        public lastUpdatedPlaypoint?: number,
        public description?: string,
        public transactionType?: TransactionType,
        public pricePointValue?: number,
        public source?: string,
        public transactionStatus?: TransactionStatus,
        public transactionDate?: Moment,
        public orderId?: string,
        public userAction?: string,
        public productId?: string,
        public productType?: string,
        public producDetail?: string,
        public walletId?: string,
        public mid?: string,
        public param1?: string,
        public param2?: string,
        public param3?: string,
        public params?: string,
        public created?: Moment,
        public modifiedon?: Moment
    ) {}
}
