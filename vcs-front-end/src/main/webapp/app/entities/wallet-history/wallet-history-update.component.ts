import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWalletHistory } from 'app/shared/model/wallet-history.model';
import { WalletHistoryService } from './wallet-history.service';

@Component({
    selector: 'jhi-wallet-history-update',
    templateUrl: './wallet-history-update.component.html'
})
export class WalletHistoryUpdateComponent implements OnInit {
    private _walletHistory: IWalletHistory;
    isSaving: boolean;
    created: string;
    modifiedon: string;
    transactionDate: string;

    constructor(private walletHistoryService: WalletHistoryService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ walletHistory }) => {
            this.walletHistory = walletHistory;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.walletHistory.created = moment(this.created, DATE_TIME_FORMAT);
        this.walletHistory.modifiedon = moment(this.modifiedon, DATE_TIME_FORMAT);
        this.walletHistory.transactionDate = moment(this.transactionDate, DATE_TIME_FORMAT);
        if (this.walletHistory.id !== undefined) {
            this.subscribeToSaveResponse(this.walletHistoryService.update(this.walletHistory));
        } else {
            this.subscribeToSaveResponse(this.walletHistoryService.create(this.walletHistory));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IWalletHistory>>) {
        result.subscribe((res: HttpResponse<IWalletHistory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get walletHistory() {
        return this._walletHistory;
    }

    set walletHistory(walletHistory: IWalletHistory) {
        this._walletHistory = walletHistory;
        this.created = moment(walletHistory.created).format(DATE_TIME_FORMAT);
        this.modifiedon = moment(walletHistory.modifiedon).format(DATE_TIME_FORMAT);
        this.transactionDate = moment(walletHistory.transactionDate).format(DATE_TIME_FORMAT);
    }
}
