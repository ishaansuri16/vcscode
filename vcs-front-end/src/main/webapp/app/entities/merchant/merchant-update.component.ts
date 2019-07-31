import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMerchant } from 'app/shared/model/merchant.model';
import { MerchantService } from './merchant.service';

@Component({
    selector: 'jhi-merchant-update',
    templateUrl: './merchant-update.component.html'
})
export class MerchantUpdateComponent implements OnInit {
    private _merchant: IMerchant;
    isSaving: boolean;
    created: string;
    modifiedon: string;

    constructor(private merchantService: MerchantService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ merchant }) => {
            this.merchant = merchant;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.merchant.created = moment(this.created, DATE_TIME_FORMAT);
        this.merchant.modifiedon = moment(this.modifiedon, DATE_TIME_FORMAT);
        if (this.merchant.id !== undefined) {
            this.subscribeToSaveResponse(this.merchantService.update(this.merchant));
        } else {
            this.subscribeToSaveResponse(this.merchantService.create(this.merchant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMerchant>>) {
        result.subscribe((res: HttpResponse<IMerchant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get merchant() {
        return this._merchant;
    }

    set merchant(merchant: IMerchant) {
        this._merchant = merchant;
        this.created = moment(merchant.created).format(DATE_TIME_FORMAT);
        this.modifiedon = moment(merchant.modifiedon).format(DATE_TIME_FORMAT);
    }
}
