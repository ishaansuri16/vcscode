import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPpUserHistory } from 'app/shared/model/pp-user-history.model';
import { PpUserHistoryService } from './pp-user-history.service';

@Component({
    selector: 'jhi-pp-user-history-update',
    templateUrl: './pp-user-history-update.component.html'
})
export class PpUserHistoryUpdateComponent implements OnInit {
    private _ppUserHistory: IPpUserHistory;
    isSaving: boolean;
    transactionDate: string;
    created: string;
    modifiedon: string;

    constructor(private ppUserHistoryService: PpUserHistoryService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ppUserHistory }) => {
            this.ppUserHistory = ppUserHistory;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.ppUserHistory.transactionDate = moment(this.transactionDate, DATE_TIME_FORMAT);
        this.ppUserHistory.created = moment(this.created, DATE_TIME_FORMAT);
        this.ppUserHistory.modifiedon = moment(this.modifiedon, DATE_TIME_FORMAT);
        if (this.ppUserHistory.id !== undefined) {
            this.subscribeToSaveResponse(this.ppUserHistoryService.update(this.ppUserHistory));
        } else {
            this.subscribeToSaveResponse(this.ppUserHistoryService.create(this.ppUserHistory));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPpUserHistory>>) {
        result.subscribe((res: HttpResponse<IPpUserHistory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get ppUserHistory() {
        return this._ppUserHistory;
    }

    set ppUserHistory(ppUserHistory: IPpUserHistory) {
        this._ppUserHistory = ppUserHistory;
        this.transactionDate = moment(ppUserHistory.transactionDate).format(DATE_TIME_FORMAT);
        this.created = moment(ppUserHistory.created).format(DATE_TIME_FORMAT);
        this.modifiedon = moment(ppUserHistory.modifiedon).format(DATE_TIME_FORMAT);
    }
}
