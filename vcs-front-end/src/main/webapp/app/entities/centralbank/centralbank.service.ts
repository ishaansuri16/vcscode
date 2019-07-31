import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWallet } from 'app/shared/model/wallet.model';
import { IMerchant } from 'app/shared/model/merchant.model';
import { IAddVCMaster } from 'app/shared/model/add-vc.model';

type EntityResponseType = HttpResponse<IWallet>;
type EntityResponseTypeAddVC = HttpResponse<IAddVCMaster>;

@Injectable({ providedIn: 'root' })
export class CentralbankService {

  private resourceUrl = SERVER_API_URL + 'api';

  constructor(private http: HttpClient) {}

  find(): Observable<EntityResponseType> {
    return this.http
        .get<IWallet>(`${this.resourceUrl}/cb/wallets`, { observe: 'response' })
        .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
}

findAllMerchant(): Observable<EntityResponseType> {
  return this.http  
      .get<IMerchant>(`${this.resourceUrl}/merchants`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
}

  
addVirtualCurrencyToMerchant(addVCMaster:IAddVCMaster): Observable<EntityResponseType> { 
  const copy = this.convertDateFromClient(addVCMaster);  
  //console.log(addVCMaster.amount+"-"+addVCMaster.destinationmid+"-"+JSON.stringify(copy)); 
  return this.http
      .post<IAddVCMaster>(`${this.resourceUrl}/cb/wallets/transfer`, copy,{ observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServeraddVC(res)));
}


private convertDateFromClient(addVCMaster:IAddVCMaster): IAddVCMaster {
  const copy: IAddVCMaster = Object.assign({}, addVCMaster, {
    //created: addVCMaster.created != null && addVCMaster.created.isValid() ? addVCMaster.created.toJSON() : null,
      //modifiedon: addVCMaster.modifiedon != null && addVCMaster.modifiedon.isValid() ? addVCMaster.modifiedon.toJSON() : null
  });
  return copy;
}

private convertDateFromServer(res: EntityResponseType): EntityResponseType {
  res.body.approvedOn = res.body.approvedOn != null ? moment(res.body.approvedOn) : null;
  res.body.created = res.body.created != null ? moment(res.body.created) : null;
  res.body.modifiedon = res.body.modifiedon != null ? moment(res.body.modifiedon) : null;
  return res;
}

private convertDateFromServeraddVC(res: EntityResponseTypeAddVC): EntityResponseTypeAddVC {
  //res.body.created = res.body.created != null ? moment(res.body.created) : null;
  //res.body.modifiedon = res.body.modifiedon != null ? moment(res.body.modifiedon) : null;
  return res;
}

}
