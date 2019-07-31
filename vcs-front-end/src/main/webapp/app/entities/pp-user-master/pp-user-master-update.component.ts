import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPpUserMaster } from 'app/shared/model/pp-user-master.model';
import { PpUserMasterService } from './pp-user-master.service';

@Component({
    selector: 'jhi-pp-user-master-update',
    templateUrl: './pp-user-master-update.component.html'
})
export class PpUserMasterUpdateComponent implements OnInit {
    private _ppUserMaster: IPpUserMaster;
    isSaving: boolean;
    created: string;
    modifiedon: string;

    constructor(private ppUserMasterService: PpUserMasterService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ppUserMaster }) => {
            this.ppUserMaster = ppUserMaster;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.ppUserMaster.created = moment(this.created, DATE_TIME_FORMAT);
        this.ppUserMaster.modifiedon = moment(this.modifiedon, DATE_TIME_FORMAT);
        if (this.ppUserMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.ppUserMasterService.update(this.ppUserMaster));
        } else {
            this.subscribeToSaveResponse(this.ppUserMasterService.create(this.ppUserMaster));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPpUserMaster>>) {
        result.subscribe((res: HttpResponse<IPpUserMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get ppUserMaster() {
        return this._ppUserMaster;
    }

    set ppUserMaster(ppUserMaster: IPpUserMaster) {
        this._ppUserMaster = ppUserMaster;
        this.created = moment(ppUserMaster.created).format(DATE_TIME_FORMAT);
        this.modifiedon = moment(ppUserMaster.modifiedon).format(DATE_TIME_FORMAT);
    }
}
