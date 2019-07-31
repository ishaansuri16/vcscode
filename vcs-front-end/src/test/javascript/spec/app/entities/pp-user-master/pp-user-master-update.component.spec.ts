/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PpFrontEndTestModule } from '../../../test.module';
import { PpUserMasterUpdateComponent } from 'app/entities/pp-user-master/pp-user-master-update.component';
import { PpUserMasterService } from 'app/entities/pp-user-master/pp-user-master.service';
import { PpUserMaster } from 'app/shared/model/pp-user-master.model';

describe('Component Tests', () => {
    describe('PpUserMaster Management Update Component', () => {
        let comp: PpUserMasterUpdateComponent;
        let fixture: ComponentFixture<PpUserMasterUpdateComponent>;
        let service: PpUserMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PpFrontEndTestModule],
                declarations: [PpUserMasterUpdateComponent]
            })
                .overrideTemplate(PpUserMasterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PpUserMasterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PpUserMasterService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PpUserMaster(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ppUserMaster = entity;
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
                    const entity = new PpUserMaster();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ppUserMaster = entity;
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
