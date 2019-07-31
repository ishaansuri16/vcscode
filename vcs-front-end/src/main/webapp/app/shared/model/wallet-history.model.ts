import { Moment } from 'moment';

export const enum TransactionType {
    CREDIT = 'CREDIT',
    DEBIT = 'DEBIT'
}

export const enum TransactionStatus {
    SUCCESS = 'SUCCESS',
    FAIL = 'FAIL'
}

export interface IWalletHistory {
    id?: number;
    orderId?: string;
    userId?: string;
    lastUpdatedPlaypoint?: number;
    pricePointValue?: number;
    transactionType?: TransactionType;
    description?: string;
    source?: string;
    userAction?: string;
    param1?: string;
    param2?: string;
    param3?: string;
    created?: Moment;
    modifiedon?: Moment;
    params?: string;
    transactionStatus?: TransactionStatus;
    transactionDate?: Moment;
    walletId?: string;
    mid?: string;
}

export class WalletHistory implements IWalletHistory {
    constructor(
        public id?: number,
        public orderId?: string,
        public userId?: string,
        public lastUpdatedPlaypoint?: number,
        public pricePointValue?: number,
        public transactionType?: TransactionType,
        public description?: string,
        public source?: string,
        public userAction?: string,
        public param1?: string,
        public param2?: string,
        public param3?: string,
        public created?: Moment,
        public modifiedon?: Moment,
        public params?: string,
        public transactionStatus?: TransactionStatus,
        public transactionDate?: Moment,
        public walletId?: string,
        public mid?: string
    ) {}
}
