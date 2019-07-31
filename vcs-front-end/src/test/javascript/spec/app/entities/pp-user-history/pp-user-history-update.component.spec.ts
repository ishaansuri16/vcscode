/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PpFrontEndTestModule } from '../../../test.module';
import { PpUserHistoryUpdateComponent } from 'app/entities/pp-user-history/pp-user-history-update.component';
import { PpUserHistoryService } from 'app/entities/pp-user-history/pp-user-history.service';
import { PpUserHistory } from 'app/shared/model/pp-user-history.model';

describe('Component Tests', () => {
    describe('PpUserHistory Management Update Component', () => {
        let comp: PpUserHistoryUpdateComponent;
        let fixture: ComponentFixture<PpUserHistoryUpdateComponent>;
        let service: PpUserHistoryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PpFrontEndTestModule],
                declarations: [PpUserHistoryUpdateComponent]
            })
                .overrideTemplate(PpUserHistoryUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PpUserHistoryUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PpUserHistoryService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PpUserHistory(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ppUserHistory = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PpUserHistory();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ppUserHistory = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
