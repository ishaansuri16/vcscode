import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IPpUserHistory } from 'app/shared/model/pp-user-history.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { PpUserHistoryService } from './pp-user-history.service';

@Component({
    selector: 'jhi-pp-user-history',
    templateUrl: './pp-user-history.component.html',
    styleUrls: ['./pp-user-history.component.css']
})
export class PpUserHistoryComponent implements OnInit, OnDestroy {
    currentAccount: any;
    ppUserHistories: IPpUserHistory[];
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
    /*properties:string[]=["user_id","description", "transaction_type","transaction_status", "order_id", 
                            "param1", "param2","param3", "source","transaction_date","order_id","mid"]
*/
    properties:string[]=["user_id","description", "order_id","param1", "param2","param3", "source","order_id","mid",
                         "transaction_type","transaction_status"]


    constructor(
        private ppUserHistoryService: PpUserHistoryService,
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
        this.ppUserHistoryService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IPpUserHistory[]>) => this.paginatePpUserHistories(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadAllMatching() {
        if(this.matchString == '')
            this.loadAll();
        else{
            this.ppUserHistoryService
                .queryMatching({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    sort: this.sort(),
                    property:this.prop,
                    pattern:this.matchString
                })
                .subscribe(
                    (res: HttpResponse<IPpUserHistory[]>) => this.paginatePpUserHistories(res.body, res.headers),
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
        if(this.matchString != ""){
            this.router.navigate(['/pp-user-history/'+this.prop+'/'+this.matchString], {
                queryParams: {
                    page: this.page,
                    size: this.itemsPerPage,
                    sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc'),
                    property:this.prop,
                    pattern:this.matchString
                }
            });
            this.loadAllMatching();
        }else{
            this.router.navigate(['/pp-user-history'], {
                queryParams: {
                    page: this.page,
                    size: this.itemsPerPage,
                    sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
                }
            });
            this.loadAll();
        }
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/pp-user-history',
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
        this.registerChangeInPpUserHistories();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPpUserHistory) {
        return item.id;
    }

    registerChangeInPpUserHistories() {
        this.eventSubscriber = this.eventManager.subscribe('ppUserHistoryListModification', response => this.loadAll());
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

    private paginatePpUserHistories(data: IPpUserHistory[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.ppUserHistories = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
