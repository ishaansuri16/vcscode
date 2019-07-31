import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWalletHistory } from 'app/shared/model/wallet-history.model';

@Component({
    selector: 'jhi-wallet-history-detail',
    templateUrl: './wallet-history-detail.component.html'
})
export class WalletHistoryDetailComponent implements OnInit {
    walletHistory: IWalletHistory;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ walletHistory }) => {
            this.walletHistory = walletHistory;
        });
    }

    previousState() {
        window.history.back();
    }
}
