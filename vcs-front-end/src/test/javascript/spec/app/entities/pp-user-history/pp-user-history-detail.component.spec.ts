/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PpFrontEndTestModule } from '../../../test.module';
import { PpUserHistoryDetailComponent } from 'app/entities/pp-user-history/pp-user-history-detail.component';
import { PpUserHistory } from 'app/shared/model/pp-user-history.model';

describe('Component Tests', () => {
    describe('PpUserHistory Management Detail Component', () => {
        let comp: PpUserHistoryDetailComponent;
        let fixture: ComponentFixture<PpUserHistoryDetailComponent>;
        const route = ({ data: of({ ppUserHistory: new PpUserHistory(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PpFrontEndTestModule],
                declarations: [PpUserHistoryDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PpUserHistoryDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PpUserHistoryDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.ppUserHistory).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
