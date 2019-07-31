import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PpFrontEndSharedModule } from 'app/shared';
import {
    WalletHistoryComponent,
    WalletHistoryDetailComponent,
    WalletHistoryUpdateComponent,
    WalletHistoryDeletePopupComponent,
    WalletHistoryDeleteDialogComponent,
    walletHistoryRoute,
    walletHistoryPopupRoute
} from './';

const ENTITY_STATES = [...walletHistoryRoute, ...walletHistoryPopupRoute];

@NgModule({
    imports: [PpFrontEndSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        WalletHistoryComponent,
        WalletHistoryDetailComponent,
        WalletHistoryUpdateComponent,
        WalletHistoryDeleteDialogComponent,
        WalletHistoryDeletePopupComponent
    ],
    entryComponents: [
        WalletHistoryComponent,
        WalletHistoryUpdateComponent,
        WalletHistoryDeleteDialogComponent,
        WalletHistoryDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PpFrontEndWalletHistoryModule {}
