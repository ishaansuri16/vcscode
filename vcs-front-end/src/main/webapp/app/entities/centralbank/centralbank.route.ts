import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CentralbankComponent } from './centralbank.component';
import { IWallet } from 'app/shared/model/wallet.model';
import { Wallet } from 'app/shared/model/wallet.model';
import { CentralbankService } from './centralbank.service';
import { Merchant } from 'app/shared/model/merchant.model';
import { IMerchant } from 'app/shared/model/merchant.model';
import { IAddVCMaster } from 'app/shared/model/add-vc.model';
import { AddVCMaster } from 'app/shared/model/add-vc.model';



@Injectable({ providedIn: 'root' })
export class CentralBankWallet implements Resolve<IWallet> {
    constructor(private service: CentralbankService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.service.find().pipe(map((wallet: HttpResponse<Wallet>) => wallet.body));
    }
}
@Injectable({ providedIn: 'root' })
export class MerchantList implements Resolve<IMerchant> {

    constructor(private service: CentralbankService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.service.findAllMerchant().pipe(map((merchant: HttpResponse<Merchant>) => merchant.body));
    }
}

@Injectable({ providedIn: 'root' })
export class addVCResolve implements Resolve<IAddVCMaster> {
    constructor(private service: CentralbankService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return of(new AddVCMaster());
    }
}

export const centralbankRoute: Routes = [

    {
        path: 'centralbank',
        component: CentralbankComponent,
        resolve: {
            wallet1: CentralBankWallet,
            merchantList:MerchantList,
            addVCMaster:addVCResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Centralbank'
        },
        canActivate: [UserRouteAccessService]
    },

    
   
];


