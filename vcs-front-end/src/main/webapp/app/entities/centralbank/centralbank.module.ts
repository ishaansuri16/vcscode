import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PpFrontEndSharedModule } from 'app/shared';
import {
    CentralbankComponent,
    centralbankRoute,
} from './';

const ENTITY_STATES = [...centralbankRoute];

@NgModule({
    imports: [PpFrontEndSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CentralbankComponent,
    ],
    entryComponents: [CentralbankComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PpFrontEndCentralBankModule {}
