import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PpFrontEndSharedModule } from 'app/shared';
import {
    PpUserMasterComponent,
    PpUserMasterDetailComponent,
    PpUserMasterUpdateComponent,
    PpUserMasterDeletePopupComponent,
    PpUserMasterDeleteDialogComponent,
    ppUserMasterRoute,
    ppUserMasterPopupRoute
} from './';

const ENTITY_STATES = [...ppUserMasterRoute, ...ppUserMasterPopupRoute];

@NgModule({
    imports: [PpFrontEndSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PpUserMasterComponent,
        PpUserMasterDetailComponent,
        PpUserMasterUpdateComponent,
        PpUserMasterDeleteDialogComponent,
        PpUserMasterDeletePopupComponent
    ],
    entryComponents: [
        PpUserMasterComponent,
        PpUserMasterUpdateComponent,
        PpUserMasterDeleteDialogComponent,
        PpUserMasterDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PpFrontEndPpUserMasterModule {}
