import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWalletHistory } from 'app/shared/model/wallet-history.model';

type EntityResponseType = HttpResponse<IWalletHistory>;
type EntityArrayResponseType = HttpResponse<IWalletHistory[]>;

@Injectable({ providedIn: 'root' })
export class WalletHistoryService {
    private resourceUrl = SERVER_API_URL + 'api/wallet-histories';

    constructor(private http: HttpClient) {}

    create(walletHistory: IWalletHistory): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(walletHistory);
        return this.http
            .post<IWalletHistory>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(walletHistory: IWalletHistory): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(walletHistory);
        return this.http
            .put<IWalletHistory>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IWalletHistory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IWalletHistory[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(walletHistory: IWalletHistory): IWalletHistory {
        const copy: IWalletHistory = Object.assign({}, walletHistory, {
            created: walletHistory.created != null && walletHistory.created.isValid() ? walletHistory.created.toJSON() : null,
            modifiedon: walletHistory.modifiedon != null && walletHistory.modifiedon.isValid() ? walletHistory.modifiedon.toJSON() : null,
            transactionDate:
                walletHistory.transactionDate != null && walletHistory.transactionDate.isValid()
                    ? walletHistory.transactionDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.created = res.body.created != null ? moment(res.body.created) : null;
        res.body.modifiedon = res.body.modifiedon != null ? moment(res.body.modifiedon) : null;
        res.body.transactionDate = res.body.transactionDate != null ? moment(res.body.transactionDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((walletHistory: IWalletHistory) => {
            walletHistory.created = walletHistory.created != null ? moment(walletHistory.created) : null;
            walletHistory.modifiedon = walletHistory.modifiedon != null ? moment(walletHistory.modifiedon) : null;
            walletHistory.transactionDate = walletHistory.transactionDate != null ? moment(walletHistory.transactionDate) : null;
        });
        return res;
    }
}
