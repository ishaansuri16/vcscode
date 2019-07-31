import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PpFrontEndSharedModule } from 'app/shared';
import {
    PpUserHistoryComponent,
    PpUserHistoryDetailComponent,
    PpUserHistoryUpdateComponent,
    PpUserHistoryDeletePopupComponent,
    PpUserHistoryDeleteDialogComponent,
    ppUserHistoryRoute,
    ppUserHistoryPopupRoute
} from './';

const ENTITY_STATES = [...ppUserHistoryRoute, ...ppUserHistoryPopupRoute];

@NgModule({
    imports: [PpFrontEndSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PpUserHistoryComponent,
        PpUserHistoryDetailComponent,
        PpUserHistoryUpdateComponent,
        PpUserHistoryDeleteDialogComponent,
        PpUserHistoryDeletePopupComponent
    ],
    entryComponents: [
        PpUserHistoryComponent,
        PpUserHistoryUpdateComponent,
        PpUserHistoryDeleteDialogComponent,
        PpUserHistoryDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PpFrontEndPpUserHistoryModule {}
