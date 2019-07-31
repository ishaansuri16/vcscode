/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PpFrontEndTestModule } from '../../../test.module';
import { WalletHistoryDetailComponent } from 'app/entities/wallet-history/wallet-history-detail.component';
import { WalletHistory } from 'app/shared/model/wallet-history.model';

describe('Component Tests', () => {
    describe('WalletHistory Management Detail Component', () => {
        let comp: WalletHistoryDetailComponent;
        let fixture: ComponentFixture<WalletHistoryDetailComponent>;
        const route = ({ data: of({ walletHistory: new WalletHistory(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PpFrontEndTestModule],
                declarations: [WalletHistoryDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(WalletHistoryDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(WalletHistoryDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.walletHistory).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
