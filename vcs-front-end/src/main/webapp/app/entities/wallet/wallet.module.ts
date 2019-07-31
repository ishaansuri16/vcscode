import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PpFrontEndSharedModule } from 'app/shared';
import {
    WalletComponent,
    WalletDetailComponent,
    WalletUpdateComponent,
    WalletDeletePopupComponent,
    WalletDeleteDialogComponent,
    walletRoute,
    walletPopupRoute
} from './';

const ENTITY_STATES = [...walletRoute, ...walletPopupRoute];

@NgModule({
    imports: [PpFrontEndSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [WalletComponent, WalletDetailComponent, WalletUpdateComponent, WalletDeleteDialogComponent, WalletDeletePopupComponent],
    entryComponents: [WalletComponent, WalletUpdateComponent, WalletDeleteDialogComponent, WalletDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PpFrontEndWalletModule {}
