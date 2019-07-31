import { Moment } from 'moment';

export const enum WalletCategory {
    PROMOTION = 'PROMOTION',
    LOYALITY = 'LOYALITY',
    ADVERTISEMENT = 'ADVERTISEMENT'
}

export const enum WalletSubcategory {
    PROMOTION1 = 'PROMOTION1',
    PROMOTION2 = 'PROMOTION2',
    PROMOTION3 = 'PROMOTION3'
}

export const enum WalletType {
    REVENUE = 'REVENUE',
    ESCROW = 'ESCROW',
    BUDGET = 'BUDGET'
}

export const enum AmountAlertThreshholdtype {
    FLAT = 'FLAT',
    PERCENTAGE = 'PERCENTAGE'
}

export interface IWallet {
    id?: number;
    mid?: string;
    walletId?: string;
    walletCategory?: WalletCategory;
    walletSubcategory?: WalletSubcategory;
    walletType?: WalletType;
    isShow?: boolean;
    isActive?: boolean;
    approved?: boolean;
    approvedOn?: Moment;
    pricePointValue?: number;
    totalPricePointValue?: number;
    amountAlertThreshold?: number;
    amountAlertThreshholdtype?: AmountAlertThreshholdtype;
    maxDebitPerRequest?: number;
    param1?: string;
    param2?: string;
    param3?: string;
    params?: string;
    created?: Moment;
    modifiedon?: Moment;
}

export class Wallet implements IWallet {
    constructor(
        public id?: number,
        public mid?: string,
        public walletId?: string,
        public walletCategory?: WalletCategory,
        public walletSubcategory?: WalletSubcategory,
        public walletType?: WalletType,
        public isShow?: boolean,
        public isActive?: boolean,
        public approved?: boolean,
        public approvedOn?: Moment,
        public pricePointValue?: number,
        public totalPricePointValue?: number,
        public amountAlertThreshold?: number,
        public amountAlertThreshholdtype?: AmountAlertThreshholdtype,
        public maxDebitPerRequest?: number,
        public param1?: string,
        public param2?: string,
        public param3?: string,
        public params?: string,
        public created?: Moment,
        public modifiedon?: Moment
    ) {
        this.isShow = false;
        this.isActive = false;
        this.approved = false;
    }
}
