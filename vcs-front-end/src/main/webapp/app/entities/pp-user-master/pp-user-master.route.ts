import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PpUserMaster } from 'app/shared/model/pp-user-master.model';
import { PpUserMasterService } from './pp-user-master.service';
import { PpUserMasterComponent } from './pp-user-master.component';
import { PpUserMasterDetailComponent } from './pp-user-master-detail.component';
import { PpUserMasterUpdateComponent } from './pp-user-master-update.component';
import { PpUserMasterDeletePopupComponent } from './pp-user-master-delete-dialog.component';
import { IPpUserMaster } from 'app/shared/model/pp-user-master.model';

@Injectable({ providedIn: 'root' })
export class PpUserMasterResolve implements Resolve<IPpUserMaster> {
    constructor(private service: PpUserMasterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((ppUserMaster: HttpResponse<PpUserMaster>) => ppUserMaster.body));
        }
        return of(new PpUserMaster());
    }
}

export const ppUserMasterRoute: Routes = [
    {
        path: 'pp-user-master',
        component: PpUserMasterComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'PpUserMasters'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pp-user-master/:id/view',
        component: PpUserMasterDetailComponent,
        resolve: {
            ppUserMaster: PpUserMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PpUserMasters'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pp-user-master/new',
        component: PpUserMasterUpdateComponent,
        resolve: {
            ppUserMaster: PpUserMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PpUserMasters'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pp-user-master/:id/edit',
        component: PpUserMasterUpdateComponent,
        resolve: {
            ppUserMaster: PpUserMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PpUserMasters'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ppUserMasterPopupRoute: Routes = [
    {
        path: 'pp-user-master/:id/delete',
        component: PpUserMasterDeletePopupComponent,
        resolve: {
            ppUserMaster: PpUserMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PpUserMasters'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
