import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IPpUserMaster } from 'app/shared/model/pp-user-master.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { PpUserMasterService } from './pp-user-master.service';

@Component({
    selector: 'jhi-pp-user-master',
    templateUrl: './pp-user-master.component.html',
    styleUrls: ['./pp-user-master.component.css']
})
export class PpUserMasterComponent implements OnInit, OnDestroy {
    currentAccount: any;
    ppUserMasters: IPpUserMaster[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    prop:string="";
    matchString:string
    properties:string[]=["name", "email","mobile","param1", "param2","param3"]

    constructor(
        private ppUserMasterService: PpUserMasterService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.ppUserMasterService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IPpUserMaster[]>) => this.paginatePpUserMasters(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadAllMatching() {
        if(this.matchString == '')
            this.loadAll();
        else{
            this.ppUserMasterService
                .queryMatching({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    sort: this.sort(),
                    property:this.prop,
                    pattern:this.matchString
                })
                .subscribe(
                    (res: HttpResponse<IPpUserMaster[]>) => this.paginatePpUserMasters(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/pp-user-master'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/pp-user-master',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPpUserMasters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPpUserMaster) {
        return item.id;
    }

    registerChangeInPpUserMasters() {
        this.eventSubscriber = this.eventManager.subscribe('ppUserMasterListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    assignProperty(event:any){
        this.prop = event.target.value;
    }

    search(){
        this.loadAllMatching();
    }

    onKey(event:any){
        if(event.target.value === ""){
            this.loadAll();
        }
    }

    private paginatePpUserMasters(data: IPpUserMaster[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.ppUserMasters = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
