import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWallet } from 'app/shared/model/wallet.model';

type EntityResponseType = HttpResponse<IWallet>;
type EntityArrayResponseType = HttpResponse<IWallet[]>;

@Injectable({ providedIn: 'root' })
export class WalletService {
    private resourceUrl = SERVER_API_URL + 'api/wallets';

    constructor(private http: HttpClient) {}

    create(wallet: IWallet): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(wallet);
        return this.http
            .post<IWallet>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(wallet: IWallet): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(wallet);
        return this.http
            .put<IWallet>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IWallet>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IWallet[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(wallet: IWallet): IWallet {
        const copy: IWallet = Object.assign({}, wallet, {
            approvedOn: wallet.approvedOn != null && wallet.approvedOn.isValid() ? wallet.approvedOn.toJSON() : null,
            created: wallet.created != null && wallet.created.isValid() ? wallet.created.toJSON() : null,
            modifiedon: wallet.modifiedon != null && wallet.modifiedon.isValid() ? wallet.modifiedon.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.approvedOn = res.body.approvedOn != null ? moment(res.body.approvedOn) : null;
        res.body.created = res.body.created != null ? moment(res.body.created) : null;
        res.body.modifiedon = res.body.modifiedon != null ? moment(res.body.modifiedon) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((wallet: IWallet) => {
            wallet.approvedOn = wallet.approvedOn != null ? moment(wallet.approvedOn) : null;
            wallet.created = wallet.created != null ? moment(wallet.created) : null;
            wallet.modifiedon = wallet.modifiedon != null ? moment(wallet.modifiedon) : null;
        });
        return res;
    }
}
