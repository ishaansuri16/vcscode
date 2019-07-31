import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMerchant } from 'app/shared/model/merchant.model';

type EntityResponseType = HttpResponse<IMerchant>;
type EntityArrayResponseType = HttpResponse<IMerchant[]>;

@Injectable({ providedIn: 'root' })
export class MerchantService {
    private resourceUrl = SERVER_API_URL + 'api/merchants';

    constructor(private http: HttpClient) {}

    create(merchant: IMerchant): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(merchant);
        return this.http
            .post<IMerchant>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(merchant: IMerchant): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(merchant);
        return this.http
            .put<IMerchant>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMerchant>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

   

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMerchant[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(merchant: IMerchant): IMerchant {
        const copy: IMerchant = Object.assign({}, merchant, {
            created: merchant.created != null && merchant.created.isValid() ? merchant.created.toJSON() : null,
            modifiedon: merchant.modifiedon != null && merchant.modifiedon.isValid() ? merchant.modifiedon.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.created = res.body.created != null ? moment(res.body.created) : null;
        res.body.modifiedon = res.body.modifiedon != null ? moment(res.body.modifiedon) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((merchant: IMerchant) => {
            merchant.created = merchant.created != null ? moment(merchant.created) : null;
            merchant.modifiedon = merchant.modifiedon != null ? moment(merchant.modifiedon) : null;
        });
        return res;
    }
}
