/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PpFrontEndTestModule } from '../../../test.module';
import { PpUserMasterDetailComponent } from 'app/entities/pp-user-master/pp-user-master-detail.component';
import { PpUserMaster } from 'app/shared/model/pp-user-master.model';

describe('Component Tests', () => {
    describe('PpUserMaster Management Detail Component', () => {
        let comp: PpUserMasterDetailComponent;
        let fixture: ComponentFixture<PpUserMasterDetailComponent>;
        const route = ({ data: of({ ppUserMaster: new PpUserMaster(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PpFrontEndTestModule],
                declarations: [PpUserMasterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PpUserMasterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PpUserMasterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.ppUserMaster).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
