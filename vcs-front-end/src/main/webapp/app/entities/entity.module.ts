import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PpFrontEndMerchantModule } from './merchant/merchant.module';
import { PpFrontEndPpUserHistoryModule } from './pp-user-history/pp-user-history.module';
import { PpFrontEndPpUserMasterModule } from './pp-user-master/pp-user-master.module';
import { PpFrontEndWalletModule } from './wallet/wallet.module';
import { PpFrontEndWalletHistoryModule } from './wallet-history/wallet-history.module';
import { PpFrontEndCentralBankModule } from './centralbank/centralbank.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        PpFrontEndMerchantModule,
        PpFrontEndPpUserHistoryModule,
        PpFrontEndPpUserMasterModule,
        PpFrontEndWalletModule,
        PpFrontEndWalletHistoryModule,
        PpFrontEndCentralBankModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PpFrontEndEntityModule {}
