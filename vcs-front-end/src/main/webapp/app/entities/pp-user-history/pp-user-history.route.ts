import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PpUserHistory } from 'app/shared/model/pp-user-history.model';
import { PpUserHistoryService } from './pp-user-history.service';
import { PpUserHistoryComponent } from './pp-user-history.component';
import { PpUserHistoryDetailComponent } from './pp-user-history-detail.component';
import { PpUserHistoryUpdateComponent } from './pp-user-history-update.component';
import { PpUserHistoryDeletePopupComponent } from './pp-user-history-delete-dialog.component';
import { IPpUserHistory } from 'app/shared/model/pp-user-history.model';

@Injectable({ providedIn: 'root' })
export class PpUserHistoryResolve implements Resolve<IPpUserHistory> {
    constructor(private service: PpUserHistoryService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((ppUserHistory: HttpResponse<PpUserHistory>) => ppUserHistory.body));
        }
        return of(new PpUserHistory());
    }
}

export const ppUserHistoryRoute: Routes = [
    {
        path: 'pp-user-history',
        component: PpUserHistoryComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'PpUserHistories'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pp-user-history/:id/view',
        component: PpUserHistoryDetailComponent,
        resolve: {
            ppUserHistory: PpUserHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PpUserHistories'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pp-user-history/new',
        component: PpUserHistoryUpdateComponent,
        resolve: {
            ppUserHistory: PpUserHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PpUserHistories'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pp-user-history/:id/edit',
        component: PpUserHistoryUpdateComponent,
        resolve: {
            ppUserHistory: PpUserHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PpUserHistories'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ppUserHistoryPopupRoute: Routes = [
    {
        path: 'pp-user-history/:id/delete',
        component: PpUserHistoryDeletePopupComponent,
        resolve: {
            ppUserHistory: PpUserHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PpUserHistories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
