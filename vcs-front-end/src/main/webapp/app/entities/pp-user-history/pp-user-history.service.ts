import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPpUserHistory } from 'app/shared/model/pp-user-history.model';

type EntityResponseType = HttpResponse<IPpUserHistory>;
type EntityArrayResponseType = HttpResponse<IPpUserHistory[]>;

@Injectable({ providedIn: 'root' })
export class PpUserHistoryService {
    private resourceUrl = SERVER_API_URL + 'api/pp-user-histories';

    constructor(private http: HttpClient) {}

    create(ppUserHistory: IPpUserHistory): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(ppUserHistory);
        return this.http
            .post<IPpUserHistory>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(ppUserHistory: IPpUserHistory): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(ppUserHistory);
        return this.http
            .put<IPpUserHistory>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPpUserHistory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPpUserHistory[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }
    queryMatching(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPpUserHistory[]>(this.resourceUrl+'/'+req.property+'/'+req.pattern, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(ppUserHistory: IPpUserHistory): IPpUserHistory {
        const copy: IPpUserHistory = Object.assign({}, ppUserHistory, {
            transactionDate:
                ppUserHistory.transactionDate != null && ppUserHistory.transactionDate.isValid()
                    ? ppUserHistory.transactionDate.toJSON()
                    : null,
            created: ppUserHistory.created != null && ppUserHistory.created.isValid() ? ppUserHistory.created.toJSON() : null,
            modifiedon: ppUserHistory.modifiedon != null && ppUserHistory.modifiedon.isValid() ? ppUserHistory.modifiedon.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.transactionDate = res.body.transactionDate != null ? moment(res.body.transactionDate) : null;
        res.body.created = res.body.created != null ? moment(res.body.created) : null;
        res.body.modifiedon = res.body.modifiedon != null ? moment(res.body.modifiedon) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((ppUserHistory: IPpUserHistory) => {
            ppUserHistory.transactionDate = ppUserHistory.transactionDate != null ? moment(ppUserHistory.transactionDate) : null;
            ppUserHistory.created = ppUserHistory.created != null ? moment(ppUserHistory.created) : null;
            ppUserHistory.modifiedon = ppUserHistory.modifiedon != null ? moment(ppUserHistory.modifiedon) : null;
        });
        return res;
    }
}
