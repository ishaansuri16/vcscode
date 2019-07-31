import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IWallet } from 'app/shared/model/wallet.model';
import { IMerchant } from 'app/shared/model/merchant.model';
import { IAddVCMaster } from 'app/shared/model/add-vc.model';
import { CentralbankService } from './centralbank.service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'jhi-centralbank',
  templateUrl: './centralbank.component.html',
  styles: []
})
export class CentralbankComponent implements OnInit {

    wallet:IWallet;
    addVCMaster:IAddVCMaster;
    merchantList:IMerchant;

    constructor(private activatedRoute: ActivatedRoute,private centralBankService: CentralbankService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ wallet1 }) => {
             this.wallet = wallet1;
        });

        this.activatedRoute.data.subscribe(({ merchantList }) => {
            this.merchantList = merchantList;
        });

       this.activatedRoute.data.subscribe(({ addVCMaster }) => {
            this.addVCMaster = addVCMaster;
        });
    }

    addVirtualCurrencyToMerchant() {
        this.subscribeToSaveResponse(this.centralBankService.addVirtualCurrencyToMerchant(this.addVCMaster));
    }

    
    private subscribeToSaveResponse(result: Observable<HttpResponse<IWallet>>) {
        result.subscribe((res: HttpResponse<IWallet>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.previousState();
    }
    previousState() {
        window.history.back();
    }
    private onSaveError() {
       
    }
}
