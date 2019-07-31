import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PpFrontEndSharedModule } from 'app/shared';
import {
    MerchantComponent,
    MerchantDetailComponent,
    MerchantUpdateComponent,
    MerchantDeletePopupComponent,
    MerchantDeleteDialogComponent,
    merchantRoute,
    merchantPopupRoute
    
} from './';

const ENTITY_STATES = [...merchantRoute, ...merchantPopupRoute];

@NgModule({
    imports: [PpFrontEndSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MerchantComponent,
        MerchantDetailComponent,
        MerchantUpdateComponent,
        MerchantDeleteDialogComponent,
        MerchantDeletePopupComponent
    ],
    entryComponents: [MerchantComponent, MerchantUpdateComponent, MerchantDeleteDialogComponent, MerchantDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PpFrontEndMerchantModule {}
