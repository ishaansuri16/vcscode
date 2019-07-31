import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { WalletHistory } from 'app/shared/model/wallet-history.model';
import { WalletHistoryService } from './wallet-history.service';
import { WalletHistoryComponent } from './wallet-history.component';
import { WalletHistoryDetailComponent } from './wallet-history-detail.component';
import { WalletHistoryUpdateComponent } from './wallet-history-update.component';
import { WalletHistoryDeletePopupComponent } from './wallet-history-delete-dialog.component';
import { IWalletHistory } from 'app/shared/model/wallet-history.model';

@Injectable({ providedIn: 'root' })
export class WalletHistoryResolve implements Resolve<IWalletHistory> {
    constructor(private service: WalletHistoryService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((walletHistory: HttpResponse<WalletHistory>) => walletHistory.body));
        }
        return of(new WalletHistory());
    }
}

export const walletHistoryRoute: Routes = [
    {
        path: 'wallet-history',
        component: WalletHistoryComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'WalletHistories'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'wallet-history/:id/view',
        component: WalletHistoryDetailComponent,
        resolve: {
            walletHistory: WalletHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WalletHistories'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'wallet-history/new',
        component: WalletHistoryUpdateComponent,
        resolve: {
            walletHistory: WalletHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WalletHistories'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'wallet-history/:id/edit',
        component: WalletHistoryUpdateComponent,
        resolve: {
            walletHistory: WalletHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WalletHistories'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const walletHistoryPopupRoute: Routes = [
    {
        path: 'wallet-history/:id/delete',
        component: WalletHistoryDeletePopupComponent,
        resolve: {
            walletHistory: WalletHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WalletHistories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
