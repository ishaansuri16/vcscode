import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPpUserMaster } from 'app/shared/model/pp-user-master.model';

type EntityResponseType = HttpResponse<IPpUserMaster>;
type EntityArrayResponseType = HttpResponse<IPpUserMaster[]>;

@Injectable({ providedIn: 'root' })
export class PpUserMasterService {
    private resourceUrl = SERVER_API_URL + 'api/pp-user-masters';

    constructor(private http: HttpClient) {}

    create(ppUserMaster: IPpUserMaster): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(ppUserMaster);
        return this.http
            .post<IPpUserMaster>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(ppUserMaster: IPpUserMaster): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(ppUserMaster);
        return this.http
            .put<IPpUserMaster>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPpUserMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPpUserMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    queryMatching(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPpUserMaster[]>(this.resourceUrl+'/'+req.property+'/'+req.pattern, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(ppUserMaster: IPpUserMaster): IPpUserMaster {
        const copy: IPpUserMaster = Object.assign({}, ppUserMaster, {
            created: ppUserMaster.created != null && ppUserMaster.created.isValid() ? ppUserMaster.created.toJSON() : null,
            modifiedon: ppUserMaster.modifiedon != null && ppUserMaster.modifiedon.isValid() ? ppUserMaster.modifiedon.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.created = res.body.created != null ? moment(res.body.created) : null;
        res.body.modifiedon = res.body.modifiedon != null ? moment(res.body.modifiedon) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((ppUserMaster: IPpUserMaster) => {
            ppUserMaster.created = ppUserMaster.created != null ? moment(ppUserMaster.created) : null;
            ppUserMaster.modifiedon = ppUserMaster.modifiedon != null ? moment(ppUserMaster.modifiedon) : null;
        });
        return res;
    }
}
