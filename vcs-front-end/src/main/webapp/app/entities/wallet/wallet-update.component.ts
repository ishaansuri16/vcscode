import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWallet } from 'app/shared/model/wallet.model';
import { WalletService } from './wallet.service';

@Component({
    selector: 'jhi-wallet-update',
    templateUrl: './wallet-update.component.html'
})
export class WalletUpdateComponent implements OnInit {
    private _wallet: IWallet;
    isSaving: boolean;
    approvedOn: string;
    created: string;
    modifiedon: string;

    constructor(private walletService: WalletService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ wallet }) => {
            this.wallet = wallet;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.wallet.approvedOn = moment(this.approvedOn, DATE_TIME_FORMAT);
        this.wallet.created = moment(this.created, DATE_TIME_FORMAT);
        this.wallet.modifiedon = moment(this.modifiedon, DATE_TIME_FORMAT);
        if (this.wallet.id !== undefined) {
            this.subscribeToSaveResponse(this.walletService.update(this.wallet));
        } else {
            this.subscribeToSaveResponse(this.walletService.create(this.wallet));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IWallet>>) {
        result.subscribe((res: HttpResponse<IWallet>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get wallet() {
        return this._wallet;
    }

    set wallet(wallet: IWallet) {
        this._wallet = wallet;
        this.approvedOn = moment(wallet.approvedOn).format(DATE_TIME_FORMAT);
        this.created = moment(wallet.created).format(DATE_TIME_FORMAT);
        this.modifiedon = moment(wallet.modifiedon).format(DATE_TIME_FORMAT);
    }
}
